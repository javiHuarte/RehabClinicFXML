package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pojos.Pacient;
import pojos.Treatment;

public class AllTreatmentsViewController implements Initializable  {

	@FXML
	private Button addButton;

	@FXML
	private TextField searchNameTextField, searchIdTextField;

	@FXML
	private TableView<Treatment> treatmentsTable = null;

	@FXML
	private TableColumn<Treatment, Integer> idColumn;
	@FXML
	private TableColumn<Treatment, String> typeColumn;
	@FXML
	private TableColumn<Treatment, String> infoColumn;

	private DBConnection dbConnection = new DBConnection();

	private ObservableList<Treatment> treatments = FXCollections.observableArrayList();
	FilteredList filter = new FilteredList(treatments, e -> true);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idColumn.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("id"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("type"));
		infoColumn.setCellValueFactory(new PropertyValueFactory<Treatment, String>("extra_info"));

		treatmentsTable.setEditable(true);

		treatmentsTable.setItems(loadTreatments());
	}

	public ObservableList<Treatment> loadTreatments(){

		List<Treatment> treatmentList = null;
		treatmentList = dbConnection.listTreatments();

		for(int i = 0; i<treatmentList.size(); i++){
			if(treatmentList.get(i).getPatient_extra_info() == null){
				treatments.add(treatmentList.get(i));
			}
		}
		return treatments;

	}

	public void newTreatmentButton (ActionEvent event) throws Exception{
		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "newTreatment.fxml", "New Treatment");
	}


	public void search(KeyEvent event) {

		searchNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			filter.setPredicate((Predicate<? super Treatment>) (Treatment treatment) -> {

				if (newValue.isEmpty() || newValue == null) {
					return true;
				} else if (treatment.getType().contains(newValue)) {
					return true;
				}

				return false;
			});

			SortedList sort = new SortedList(filter);
			sort.comparatorProperty().bind(treatmentsTable.comparatorProperty());
			treatmentsTable.setItems(sort);

		});
	}

	public void searchById(KeyEvent event) {

		searchIdTextField.textProperty().addListener((observable, oldValue, newValue) -> {

			filter.setPredicate((Predicate<? super Treatment>) (Treatment treatment) -> {

				if (newValue.isEmpty() || newValue == null) {
					return true;
				} else if (String.valueOf(treatment.getId()).contains(newValue)) {
					return true;
				}

				return false;
			});

			SortedList sort = new SortedList(filter);
			sort.comparatorProperty().bind(treatmentsTable.comparatorProperty());
			treatmentsTable.setItems(sort);

		});
	}

	public void backButton(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "medicalProfessionalLogin.fxml", "Receptionist Login");
	}

}
