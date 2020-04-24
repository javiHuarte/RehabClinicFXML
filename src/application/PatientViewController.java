package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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

	@FXML private TableView<Patientfxml> patientTable = null;

	@FXML private TableColumn<Patientfxml, Integer> idColumn;
	@FXML private TableColumn<Patientfxml, String> nameColumn;
	@FXML private TableColumn<Patientfxml, String> sexColumn;
	@FXML private TableColumn<Patientfxml, String> nifColumn;
	@FXML private TableColumn<Patientfxml, String> emailColumn;
	@FXML private TableColumn<Patientfxml, String> adressColumn;
	@FXML private TableColumn<Patientfxml, Boolean> internColumn; ///antes era un string
	@FXML private TableColumn<Patientfxml, Integer> phoneNumberColumn;
	@FXML private TableColumn<Patientfxml, LocalDate> dobColumn;


	@FXML private Button editPatientButton;
	@FXML TextField txtSearch;

	private ObservableList<Patientfxml> patients = FXCollections.observableArrayList();
	FilteredList filter = new FilteredList(patients, e->true);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	//disable the edit button until the patient has been selected
	this.editPatientButton.setDisable(true);

	//patientTable.setEditable(true);

	idColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, Integer>("id"));
	nameColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, String>("name"));
	sexColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, String>("sex"));
	nifColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, String>("nif"));
	emailColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, String>("email"));
	adressColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, String>("adress"));
	phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, Integer>("phoneNumber"));
	internColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, Boolean>("intern"));
	dobColumn.setCellValueFactory(new PropertyValueFactory<Patientfxml, LocalDate>("dob"));

	//set  the table editable in order to update it
	patientTable.setEditable(true);

	//lo silencio para desactivar la posibilidad de editar directamente desde la tabla
	/*nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	nifColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	sexColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	adressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	internColumn.setCellFactory(TextFieldTableCell.forTableColumn());
*/
	//load data
	patientTable.setItems(loadPatients());

	}

//if the editbutton es pushed, pass the selected patient to the newPatientView and preload it with data

	public void editButtonPushed(ActionEvent event){

		SceneChanger sc = new SceneChanger();
		//Pacient patient = this.patientTable.getSelectionModel().getSelectedItem(); //return the selected patient in the table
		NewPatientController npc = new NewPatientController();
		//sc.changeScenesWithData(event, "newPatient.fxml", "Edit Patient", patient, npc);
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

		Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
		patientSelected.setName(edditedCell.getNewValue().toString());

	}


public void changeNifCellEvent (CellEditEvent edditedCell){

		Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
		patientSelected.setNif(edditedCell.getNewValue().toString());

	}

public void changeEmailCellEvent (CellEditEvent edditedCell){

	Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setEmail(edditedCell.getNewValue().toString());

}

public void changePhoneNumberCellEvent (CellEditEvent edditedCell){

	Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setPhoneNumber(edditedCell.getNewValue().toString());

}

public void changeAdressCellEvent (CellEditEvent edditedCell){

	Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setAdress(edditedCell.getNewValue().toString());

}

public void changeInternCellEvent (CellEditEvent edditedCell){

	Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setIntern(edditedCell.getNewValue().toString());

}

public void changeSexCellEvent (CellEditEvent edditedCell){

	Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
	patientSelected.setSex(edditedCell.getNewValue().toString());

}

public void changeDobCellEvent (CellEditEvent edditedCell){

	Patientfxml patientSelected = patientTable.getSelectionModel().getSelectedItem();
	//patientSelected.setSex(edditedCell.getNewValue());

}

public ObservableList<Patientfxml>loadPatients(){



		Patientfxml patient1 = new Patientfxml(1, "juan", "54448314T", "male",LocalDate.of(1995,Month.APRIL,9), "paseo del parque 4", "jjhua@gmail.com", "662223636", "YES");
		Patientfxml patient2 = new Patientfxml(2, "yoan", "54448314T", "male", LocalDate.of(1998,Month.APRIL,23), "pase castellana", "jjhua@gmail.com", "662223636", "YES");


		patients.add(new Patientfxml(3, "javi", "55555", "male",LocalDate.of(2019, Month.AUGUST, 30), "madrid", "jjhua@gmail.com", "662223636", "YES" ));
		patients.add(patient2);
		patients.add(patient1);


		patients.add(new Patientfxml(4, "javi", "55555", "male", LocalDate.of(2019, Month.AUGUST, 30), "paseo del barcelona 2", "jjhua@gmail.com", "662223636", "YES"));


		return patients;

	}

public void newPatientButton(ActionEvent event) throws IOException{

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "newPatient.fxml", "New Patient");
	}

public void search(KeyEvent event){

	txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{

		filter.setPredicate((Predicate<? super Patientfxml>) (Patientfxml patient) -> {

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
