package application;




import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojos.Pacient;
import pojos.Treatment;


public class TreatmentPatientViewController implements Initializable {

	private Integer pacientId;

	@FXML
	private Button addTreatmentButton, deleteTreatmentButton,editTreatmentButton;
	@FXML
	private DatePicker startDataPicker, finishDataPicker;
	@FXML
	private TextArea patientExtraInfoTextArea;

	@FXML
	private TextField textFieldName;
	@FXML
	private TextField textFieldSex;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button backButton;


	@FXML
	private TableView<Treatment> patientTreatmentsTable = null;
	@FXML
	private TableColumn<Treatment, Integer> idColumn;
	@FXML
	private TableColumn<Treatment, String> typeColumn;
	@FXML
	private TableColumn<Treatment, LocalDate> startColumn;
	@FXML
	private TableColumn<Treatment, LocalDate> finishColumn;
	@FXML
	private TableColumn<Treatment, String> patientExtraInfoColumn;
	@FXML
	private TableColumn<Treatment, String> extraInfoColumn;


	@FXML
	private TableView<Treatment> genericTreatmentsTable = null;
	@FXML
	private TableColumn<Treatment, Integer> idGenericColumn;
	@FXML
	private TableColumn<Treatment, String> typeGenericColumn;
	@FXML
	private TableColumn<Treatment, String> extraInfoGenericColumn;





	private DBConnection dbConnection = new DBConnection();

	private ObservableList<Treatment> genericTreatments = FXCollections.observableArrayList();
	private ObservableList<Treatment> patientTreatments = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idGenericColumn.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("id"));
		typeGenericColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("type"));
		extraInfoGenericColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("extra_info"));

		idColumn.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("id"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("type"));
		startColumn.setCellValueFactory(new PropertyValueFactory<Treatment, LocalDate>("starting_date"));
		finishColumn.setCellValueFactory(new PropertyValueFactory<Treatment, LocalDate>("finishing_date"));
		patientExtraInfoColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("patient_extra_info"));
		extraInfoColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("extra_info"));


		startDataPicker.setDisable(true);
		finishDataPicker.setDisable(true);
		patientExtraInfoTextArea.setDisable(true);
		addTreatmentButton.setDisable(true);
		deleteTreatmentButton.setDisable(true);
		editTreatmentButton.setDisable(true);

		genericTreatmentsTable.setItems(loadGenericTreatments());
		//patientTreatmentsTable.setItems(loadPatientTreatments());

	}

	public void preloadData (Pacient patient, Integer patientId){

		this.pacientId = patientId;
		this.textFieldName.setEditable(false);
		this.textFieldName.setText(patient.getName());
		this.textFieldSex.setEditable(false);
		this.textFieldSex.setText(patient.getSex());
		this.datePicker.setEditable(false);
		this.datePicker.setValue(patient.getDob());


	}

	public void back (ActionEvent event){

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "listAllPatientsView.fxml", "Receptionist Login");

	}

	public ObservableList<Treatment> loadGenericTreatments(){
		List<Treatment> treatmentList = null;
		treatmentList = dbConnection.listTreatments();

		for(int i = 0; i<treatmentList.size(); i++){
			if(treatmentList.get(i).getPatient_extra_info() == null){
				genericTreatments.add(treatmentList.get(i));
			}
		}
		return genericTreatments;


	}

	public void  loadPatientTreatments(){
		List<Treatment> treatmentList = null;
		treatmentList = dbConnection.listPatientTreatments(this.pacientId);
		patientTreatments.addAll(treatmentList);
		patientTreatmentsTable.setItems(patientTreatments);



	}

	public void treatmentSelected() {

		startDataPicker.setDisable(false);
		finishDataPicker.setDisable(false);
		patientExtraInfoTextArea.setDisable(false);
		addTreatmentButton.setDisable(false);
	}

	public void addTreatment(){

		String treatmentType = genericTreatmentsTable.getSelectionModel().getSelectedItem().getType();
		String extraInfo = genericTreatmentsTable.getSelectionModel().getSelectedItem().getType();
		String patientExtraInfo = patientExtraInfoTextArea.getText();
		LocalDate startDate = startDataPicker.getValue();
		LocalDate finishDate = finishDataPicker.getValue();

		Treatment newTreatment = new Treatment (treatmentType,finishDate,startDate,extraInfo,patientExtraInfo);
		dbConnection.addTreatment(newTreatment);

		List<Treatment> listaTreatments = dbConnection.listTreatments();
		Integer rango = listaTreatments.size();
		dbConnection.addTreatmentToPatient(rango, pacientId);
		patientTreatmentsTable.getItems().clear();
		loadPatientTreatments();

	}

	public void patientTreatmentSelected(){
		deleteTreatmentButton.setDisable(false);
		editTreatmentButton.setDisable(false);
	}

	public void deleteTreatment(){


		dbConnection.deleteTreatment(patientTreatmentsTable.getSelectionModel().getSelectedItem().getId());
		patientTreatmentsTable.getItems().clear();
		loadPatientTreatments();
	}

	public void editButtonPushed (ActionEvent event){
		SceneChanger sc = new SceneChanger();
		Treatment treatment = patientTreatmentsTable.getSelectionModel().getSelectedItem();
		TreatmentUpdateController treatmentUpdateController = new TreatmentUpdateController();
		sc.changeSceneFromTreatmentPatientViewToTreatmentUpdate(event, "treatmentUpdate.fxml", treatment,this.pacientId, treatmentUpdateController);
	}
}
