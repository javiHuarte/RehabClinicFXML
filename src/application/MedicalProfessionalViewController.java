package application;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pojos.Department;
import pojos.MedicalProfessional;
import pojos.Pacient;




public class MedicalProfessionalViewController implements Initializable {


	private ObservableList<MedicalProfessional> medicalProfessionals = FXCollections.observableArrayList();
	FilteredList filter = new FilteredList(medicalProfessionals, e->true);

	//We open access to the DataBase
	private DBConnection dbConnection = new DBConnection();

	@FXML  private TableView<MedicalProfessional> medicalProfessionalTable = null;

	@FXML private TableColumn<MedicalProfessional, Integer> idColumn;
	@FXML private TableColumn<MedicalProfessional, String> nameColumn;
	@FXML private TableColumn<MedicalProfessional, LocalDate> dobColumn;
	@FXML private TableColumn<MedicalProfessional, String> sexColumn;
	@FXML private TableColumn<MedicalProfessional, String> specialtyColumn;
	@FXML private TableColumn<MedicalProfessional, Integer> phoneColumn;
	@FXML private TableColumn<MedicalProfessional, String> adressColumn;
	@FXML private TableColumn<MedicalProfessional, String> emailColumn;
	@FXML private TableColumn<MedicalProfessional, Integer> contractColumn;
	@FXML private TableColumn<MedicalProfessional, String> departmentColumn;
	@FXML private TableColumn<MedicalProfessional, String> nifColumn;

	@FXML private Button backButton;
	@FXML private Button deleteButton;
	@FXML private Button addNew;
	@FXML private Button edit;
	@FXML TextField txtSearch;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	//disable the edit button until the patient has been selected
	this.edit.setDisable(true);

		//configure the table columns
		idColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("name"));
		dobColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, LocalDate>("dob"));
		sexColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("sex"));
		specialtyColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("profession"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("phoneNumber"));
		adressColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("adress"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("email"));
		//contractColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("employee_contractId"));
		departmentColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("department"));
		nifColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("nif"));

	//set  the table editable in order to update it
	medicalProfessionalTable.setEditable(true);

	medicalProfessionalTable.setItems(loadMedicalProfessional());

	}

	//Method to load/ list the medical professionals into the the TableView
	/*public void listMediacalProfessionals(){

		ObservableList<MedicalProfessional> medicalProfessionals = FXCollections.observableArrayList();
		medicalProfessionals.addAll(dbConnection.listAllMedicalProfessionals());

		medicalProfessionaTable.getItems().addAll(medicalProfessionals);

		this.medicalProfessionals.add(medicalProfessional);
	}*/

	public ObservableList<MedicalProfessional> loadMedicalProfessional(){
		

		List<MedicalProfessional> list = dbConnection.listAllMedicalProfessionals();

		medicalProfessionals.addAll(list);

		return medicalProfessionals;
}

	public void search(KeyEvent event){

		txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{

			filter.setPredicate((Predicate<? super MedicalProfessional>) (MedicalProfessional mp) -> {

					if(newValue.isEmpty() || newValue==null){
						return true;
					}
					else if(mp.getName().contains(newValue)){
						return true;
					}

			return false;
			});

			SortedList sort = new SortedList(filter);
			sort.comparatorProperty().bind(medicalProfessionalTable.comparatorProperty());
			medicalProfessionalTable.setItems(sort);

		});
	}

	//method to delete a medical professional

	public void deleteMedicalProfessional(ActionEvent event){


		int id = 7;
		int contract_id = 5;
		dbConnection.deleteMedicalProfessional(id, contract_id);

	}

	//if the edit button is pushed pass the selected mp to the new medical professional vie
	//and preload it with the data

	public void editButtonPushed(ActionEvent event) {

		SceneChanger sc = new SceneChanger();
		MedicalProfessional mp = this.medicalProfessionalTable.getSelectionModel().getSelectedItem(); //return the selected medical professional in the table
		NewMedicalProfessionalController nmpc = new NewMedicalProfessionalController();
		sc.changeScenesWithDataMedicalProfessional(event, "newMedicalProfessional.fxml", "Edit Medical Profesional", mp, nmpc);
	}

	public void medicalprofessionalSelected(){

		edit.setDisable(false);
	}

	//Method to go back to the log in set scene

		public void backToLoginButton(ActionEvent event) {

			SceneChanger sceneChanger = new SceneChanger();
			sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

		}

	//Method to add a new medical professional if selected in the scene
	//this will open the scence for a new medical professional

		public void newMedicalProffesional(ActionEvent event){

			SceneChanger sc = new SceneChanger();
			sc.changeScenes(event, "newMedicalProfessional.fxml", "New Medical Professional");
		}



}
