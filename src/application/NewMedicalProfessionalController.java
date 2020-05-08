package application;

import java.awt.List;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;
import java.util.*;
import pojos.*;

public class NewMedicalProfessionalController implements Initializable {

	@FXML private TextField txtName;
	@FXML private TextField txtNif;
	@FXML private DatePicker dobPicker;
	@FXML private TextField txtAdress;
	@FXML private TextField txtPhoneNumber;
	@FXML private TextField txtEmail;
	@FXML private ChoiceBox<String> sexChoiceBox;
	@FXML private ChoiceBox<String> departmentChoicebox;
	@FXML private TextField txtSpecialty;
	@FXML private TextField txtDepartment;
	//contract 
	@FXML private TextField txtHolidays;
	@FXML private TextField txtSalary;
	@FXML private DatePicker startingDatePicker;
	@FXML private DatePicker finisingDatePicker;
	


	@FXML private ImageView imageView;
	@FXML private Button backButton;
	@FXML private Button clearButton;
	@FXML private Button Add;
	@FXML private Button updateButton;

	private MedicalProfessional medicalProfessional;

	private DBConnection dbConnection = new DBConnection();

	//method that introduces a new medical professional

	public void addButton(ActionEvent event){

		//Data for the new medical Professional
		String name = txtName.getText();
		String nie = txtNif.getText();
		LocalDate dob = dobPicker.getValue();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String email = txtEmail.getText();
		String sex = sexChoiceBox.getValue();
		String specialty = txtSpecialty.getText();
		String department = txtDepartment.getText();
		String dep = departmentChoicebox.getValue();
		

		if ( dep == null){

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Such Department");
			alert.setContentText("There are no departments created"
								 + "Create a new department first");
			ButtonType newDepartementButton = new ButtonType("Create new department");

			alert.getButtonTypes().setAll(newDepartementButton);
			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == newDepartementButton){

				SceneChanger sc = new SceneChanger();
				sc.changeScenes(event, "newDepartment.fxml", "New Departement");

			}

		}else{

		MedicalProfessional newMedicalProfessional = new MedicalProfessional(name, dob, "female", specialty , email, adress, Integer.parseInt(phoneNumber), nie, department);
		System.out.println(newMedicalProfessional);
		dbConnection.addMedicalProfessional(newMedicalProfessional);

		}

	}
	
	public void employeeContract(){
		String holidays = txtHolidays.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		LocalDate finishingDate = finisingDatePicker.getValue();
		String sal = txtHolidays.getText();
		
		Employee_Contract newcontract = new Employee_Contract();

	}

	//Method to go back to the log in set scene

	public void backToLoginButton(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

	}

	//method to edit a patient
	public void updateMedicalProffesional(ActionEvent event){

		//New data
		String name = txtName.getText();
		String nie = txtNif.getText();
		LocalDate dob = dobPicker.getValue();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String email = txtEmail.getText();
		String sex = sexChoiceBox.getValue();
		String specialty = txtSpecialty.getText();
		String department = txtDepartment.getText();
		String dep = departmentChoicebox.getValue();

		if ( dep == null){

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Such Department");
			alert.setContentText("There are no departments created"
								 + "Create a new department first");
			ButtonType newDepartementButton = new ButtonType("Create new department");

			alert.getButtonTypes().setAll(newDepartementButton);
			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == newDepartementButton){

				SceneChanger sc = new SceneChanger();
				sc.changeScenes(event, "newDepartment.fxml", "New Departement");

			}

		}else{

		MedicalProfessional md = new MedicalProfessional(name, dob, "female", specialty , email, adress, Integer.parseInt(phoneNumber), nie, department);
		dbConnection.updateMedicalProfessional(md);

		}
	}

	//method to load the medical Professional information
	public void preloadMedicalProfessional(MedicalProfessional medicalProfessional) {
		// TODO Auto-generated method stub

		this.medicalProfessional = medicalProfessional;

		this.updateButton.setDisable(false);
		this.Add.setDisable(true);

		int phoneNumber = medicalProfessional.getPhoneNumber();
		String phone = String.valueOf(phoneNumber);

		this.txtName.setText(medicalProfessional.getName());
		this.txtNif.setText(medicalProfessional.getNif());
		this.dobPicker.setValue(medicalProfessional.getDob());
		this.txtAdress.setText(medicalProfessional.getAdress());
		this.txtEmail.setText(medicalProfessional.getEmail());
		this.txtSpecialty.setText(medicalProfessional.getProfession());
		this.sexChoiceBox.setValue(medicalProfessional.getSex());
		this.txtDepartment.setText(medicalProfessional.getDepartment());
		//this.departmentChoicebox.setValue(medicalProfessional.getDep_id().name);

	}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

	//We create the ChoiceBox for departments

			ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female", "Other");
			sexChoiceBox.setItems(sexList);
			sexChoiceBox.setValue("Male");

	//departmentList.addAll(dbConnection.listAllDepartments().getClass().getName());
	ObservableList<String> departmentList = FXCollections.observableArrayList();
	ArrayList<Department> dep = new ArrayList();
	dep.addAll(dbConnection.listAllDepartments());

	for(Department department: dep){

		departmentList.add(department.getName());

		}
	departmentChoicebox.setItems(departmentList);


	//we set the update button disabled

	this.updateButton.setDisable(false);

	//we set the update add button not disabled

		this.Add.setDisable(false);
	}


}
