 package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import pojos.Pacient;
import pojos.Patientfxml;

public class PatientViewController implements Initializable {

	@FXML private TableView<Pacient> patientTable = null;

	@FXML
	private TableColumn<Pacient, Integer> idColumn;
	@FXML
	private TableColumn<Pacient, String> nameColumn;
	@FXML
	private TableColumn<Pacient, String> sexColumn;
	@FXML
	private TableColumn<Pacient, String> nifColumn;
	@FXML
	private TableColumn<Pacient, String> emailColumn;
	@FXML
	private TableColumn<Pacient, String> adressColumn;
	@FXML
	private TableColumn<Pacient, Boolean> internColumn; /// antes era un string
	@FXML
	private TableColumn<Pacient, Integer> phoneNumberColumn;
	@FXML
	private TableColumn<Pacient, LocalDate> dobColumn;
	@FXML
	private TableColumn<Pacient, Boolean> activeColumn;

	private DBConnection dbConnection = new DBConnection();

	@FXML private Button editPatientButton;
	@FXML TextField txtSearch;

	private ObservableList<Pacient> patients = FXCollections.observableArrayList();
	FilteredList filter = new FilteredList(patients, e->true);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	//disable the edit button until the patient has been selected
	this.editPatientButton.setDisable(true);

	//patientTable.setEditable(true);

	idColumn.setCellValueFactory(new PropertyValueFactory<Pacient, Integer>("id"));
	nameColumn.setCellValueFactory(new PropertyValueFactory<Pacient, String>("name"));
	sexColumn.setCellValueFactory(new PropertyValueFactory<Pacient, String>("sex"));
	nifColumn.setCellValueFactory(new PropertyValueFactory<Pacient, String>("nie"));
	emailColumn.setCellValueFactory(new PropertyValueFactory<Pacient, String>("email"));
	adressColumn.setCellValueFactory(new PropertyValueFactory<Pacient, String>("adress"));
	phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Pacient, Integer>("phoneNumber"));
	internColumn.setCellValueFactory(new PropertyValueFactory<Pacient, Boolean>("intern"));
	dobColumn.setCellValueFactory(new PropertyValueFactory<Pacient, LocalDate>("dob"));
	activeColumn.setCellValueFactory(new PropertyValueFactory<Pacient, Boolean>("active"));


	//set  the table editable in order to update it
	patientTable.setEditable(true);

	//load data

	patientTable.setItems(loadPatients());

	}

//if the editbutton es pushed, pass the selected patient to the newPatientView and preload it with data

	public void editButtonPushed(ActionEvent event){

		SceneChanger sc = new SceneChanger();
		Pacient pacient = this.patientTable.getSelectionModel().getSelectedItem(); //return the selected patient in the table
		NewPatientController npc = new NewPatientController();
		sc.changeScenesWithData(event, "newPatient.fxml", "Edit Patient", pacient, npc);
	}

//if a patient has been selected in the table, enable editPatientButon
public void patientSelected(){

		editPatientButton.setDisable(false);
	}

public void backButton(ActionEvent event){

	SceneChanger sceneChanger = new SceneChanger();
	sceneChanger.changeScenes(event, "receptionistLogin.fxml", "Receptionist Login");
}

public void changeNameCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
		patientSelected.setName(edditedCell.getNewValue().toString());

	}


public void changeNifCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
		patientSelected.setNie(edditedCell.getNewValue().toString());

	}

public void changeEmailCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setEmail(edditedCell.getNewValue().toString());

}

public void changePhoneNumberCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
	//patientSelected.setPhoneNumber(edditedCell.getNewValue().toString();

}

public void changeAdressCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setAdress(edditedCell.getNewValue().toString());

}

public void changeInternCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
	//patientSelected.setIntern(edditedCell.getNewValue().toString());

}

public void changeSexCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setSex(edditedCell.getNewValue().toString());

}

public void changeDobCellEvent (CellEditEvent edditedCell){

	Pacient patientSelected = patientTable.getSelectionModel().getSelectedItem();
	//patientSelected.setSex(edditedCell.getNewValue());

}



public ObservableList<Pacient>loadPatients(){

	List<Pacient> pacientList = null;
	pacientList = dbConnection.listPacients();

	patients.addAll(pacientList);

		return patients;

	}

public void newPatientButton(ActionEvent event) throws IOException{

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "newPatient.fxml", "New Patient");
	}

public void search(KeyEvent event){

	txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{

		filter.setPredicate((Predicate<? super Pacient>) (Pacient patient) -> {

				if(newValue.isEmpty() || newValue==null){
					return true;
				}
				else if(patient.getName().contains(newValue)){
					return true;
				}

		return false;
		});

		SortedList sort = new SortedList(filter);
		sort.comparatorProperty().bind(patientTable.comparatorProperty());
		patientTable.setItems(sort);

	});
}



}
