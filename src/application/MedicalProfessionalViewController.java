package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojos.MedicalProfessional;


public class MedicalProfessionalViewController implements Initializable {

	//We open access to the DataBase
	private DBConnection dbConnection = new DBConnection();

	@FXML  private TableView<MedicalProfessional> medicalProfessionaTable = null;

	@FXML private TableColumn<MedicalProfessional, Integer> idColumn;
	@FXML private TableColumn<MedicalProfessional, String> nameColumn;
	@FXML private TableColumn<MedicalProfessional, Date> dobColumn;
	@FXML private TableColumn<MedicalProfessional, String> sexColumn;
	@FXML private TableColumn<MedicalProfessional, String> specialtyColumn;
	@FXML private TableColumn<MedicalProfessional, Integer> phoneColumn;
	@FXML private TableColumn<MedicalProfessional, String> adressColumn;
	@FXML private TableColumn<MedicalProfessional, String> emailColumn;
	@FXML private TableColumn<MedicalProfessional, String> contractColumn;
	@FXML private TableColumn<MedicalProfessional, Integer> departmentColumn;
	@FXML private TableColumn<MedicalProfessional, String> nifColumn;

	@FXML private Button backButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		//configure the table columns
		idColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("name"));
		dobColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Date>("dob"));
		sexColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("sex"));
		specialtyColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("profession"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("phoneNumber"));
		adressColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("adress"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("email"));
		contractColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("employee_contractId"));
		departmentColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, Integer>("dep_id"));
		nifColumn.setCellValueFactory(new PropertyValueFactory<MedicalProfessional, String>("dep_id"));

	}

	//Method to load/ list the medical professionals into the the TableView
	public void listMediacalProfessionals(){

		ObservableList<MedicalProfessional> medicalProfessionals = FXCollections.observableArrayList();
		medicalProfessionals.addAll(dbConnection.listAllMedicalProfessionals());

		medicalProfessionaTable.getItems().addAll(medicalProfessionals);
	}
}
