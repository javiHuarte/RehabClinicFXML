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

import db.interfaces.UserManager;
import db.jpa.JPAUserManager;
import pojos.*;
import pojos.users.Role;
import pojos.users.User;

public class NewMedicalProfessionalController implements Initializable, ControllerClass {

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

	@FXML private TextField txtHolidays;
	@FXML private TextField txtWeekh;
	@FXML private TextField txtSalary;
	@FXML private DatePicker startingDatePicker;
	@FXML private DatePicker finishingDatePicker;

	@FXML Label lblNameError, lblDobError, lblNifError, lblAdressError, lblPhoneError, lblDepartmet, lblSpecialtyError, lblEmailError;
	@FXML Label lblStartingDateError, lblFinishingDateError, lblWeekhError;
	@FXML Label lblSalaryError;
	@FXML Label lblFreeDaysError;

	@FXML private ImageView imageView;
	@FXML private Button backButton;
	@FXML private Button clearButton;
	@FXML private Button updateButton;
	@FXML private Button Add;

	private MedicalProfessional medicalProfessional;
	private Funciones funciones = new Funciones();
	private DBConnection dbConnection = new DBConnection();
	private static UserManager userManager = new JPAUserManager();
	Funciones functions;

	//method that introduces a new medical professional

	public void addButton(ActionEvent event){

		//Pedir datos
		String name = txtName.getText();
		String nie = txtNif.getText();
		LocalDate dob = dobPicker.getValue();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String email = txtEmail.getText();
		String sex = sexChoiceBox.getValue();
		String specialty = txtSpecialty.getText();
		String dep = departmentChoicebox.getValue();


		if ( dep == null){

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Such Department");
			alert.setContentText("Please select a department or "
								 + "Create a new department first");

			ButtonType newDepartementButton = new ButtonType("Create new department");

			alert.getButtonTypes().setAll(newDepartementButton);
			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == newDepartementButton){

				SceneChanger sc = new SceneChanger();
				sc.changeScenes(event, "newDepartment.fxml", "New Departement");
				}
			}else{

				Boolean validData = comprobarData();

				if (validData == true) {

						//we create the contract when we add the medical professional
						employeeContract();

						//we take the last id introduced into the data base which will be the id from the contract associated to this
						//medical professional
						int contract_id = dbConnection.lastId();
						System.out.println(contract_id);

						//get the id associated with the department name chosen
						int dep_id = dbConnection.getDepId(dep);

						MedicalProfessional newMedicalProfessional = new MedicalProfessional(name, dob, sex, specialty , email, adress, Integer.parseInt(phoneNumber), nie, dep_id, contract_id);
						System.out.println(newMedicalProfessional);
						dbConnection.addMedicalProfessional(newMedicalProfessional);

					}
				}
			}


	public void employeeContract(){
		String holidays = txtHolidays.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		LocalDate finishingDate = finishingDatePicker.getValue();
		String weeklyHours = txtWeekh.getText();
		String salary = txtSalary.getText();

		Employee_Contract newContract = new Employee_Contract(Integer.parseInt(holidays), startingDate, finishingDate, Float.parseFloat(weeklyHours),Float.parseFloat(salary));
		System.out.println(newContract);
		dbConnection.addContract(newContract);
	}

	public boolean comprobarData(){

		Boolean validData = true;

		this.lblAdressError.setText("");
		this.lblNameError.setText("");
		this.lblPhoneError.setText("");
		this.lblDobError.setText("");
		this.lblEmailError.setText("");
		this.lblNifError.setText("");
		this.lblSpecialtyError.setText("");
		this.lblSalaryError.setText("");
		this.lblFinishingDateError.setText("");
		this.lblSpecialtyError.setText("");
		this.lblStartingDateError.setText("");
		this.lblWeekhError.setText("");

		if (this.txtName.getText().equals("")) {

			validData = false;
			this.lblNameError.setText(" *");
		}

		if (this.txtNif.getText().equals("")) {

			validData = false;
			this.lblNifError.setText(" *");
		}

		if (this.dobPicker.getValue() == null) {

			validData = false;
			this.lblDobError.setText(" *");

		}

		if (this.txtAdress.getText().equals("")) {

			validData = false;
			this.lblAdressError.setText(" *");
		}

		if (this.txtPhoneNumber.getText().equals("") || funciones.validarInteger(this.txtPhoneNumber.getText()) ) {

			validData = false;
			this.lblPhoneError.setText(" *");
		}

		if (this.txtEmail.getText().equals("")) {

			validData = false;
			this.lblEmailError.setText(" *");
		}


		if (this.txtSpecialty.getText().equals("")){

			validData = false;
			this.lblSpecialtyError.setText(" *");
		}

		if (this.startingDatePicker.getValue() == null) {

			validData = false;
			this.lblStartingDateError.setText(" *");

		}
//
		if (this.finishingDatePicker.getValue() == null) {

			validData = false;
			this.lblFinishingDateError.setText(" *");

		}

		if (this.txtSalary.getText().equals("") || funciones.validarInteger(this.txtSalary.getText())){

			validData = false;
			this.lblSalaryError.setText(" *");
		}

		if (this.txtWeekh.getText().equals("") || funciones.validarInteger(this.txtWeekh.getText())){

			validData = false;
			this.lblWeekhError.setText(" *");
		}

		if (this.txtHolidays.getText().equals("") || funciones.validarInteger(this.txtHolidays.getText())){

			validData = false;
			this.lblFreeDaysError.setText(" *");
		}

		if (validData == false) {

			Alert warning = new Alert(Alert.AlertType.WARNING);
			warning.setTitle("Warning message");
			warning.setHeaderText("Check the introduced Data");
			warning.setContentText("Please make sure that all the flieds are properly intoduced");
			warning.showAndWait();
		}


		return validData;

	}

	public void clear() {

		txtName.clear();
		txtNif.clear();
		txtAdress.clear();
		txtEmail.clear();
		txtPhoneNumber.clear();
		txtSpecialty.clear();
		sexChoiceBox.setValue("Male");
		this.dobPicker.setValue(LocalDate.now());
		this.startingDatePicker.setValue(LocalDate.now());
		this.finishingDatePicker.setValue(LocalDate.now());
		txtSalary.clear();
		txtHolidays.clear();
		txtWeekh.clear();

	}

	//this method allows to load the information of a medical professional
	public void preloadDataMedicalProfessional(MedicalProfessional medicalProfessional) {
		// TODO Auto-generated method stub

		this.medicalProfessional = medicalProfessional;

		this.updateButton.setDisable(false);
		this.Add.setDisable(true);

		//get the medical professional information
		int phoneNumber = medicalProfessional.getPhoneNumber();
		String phone = String.valueOf(phoneNumber);

		this.txtName.setText(medicalProfessional.getName());
		this.txtNif.setText(medicalProfessional.getNif());
		this.txtAdress.setText(medicalProfessional.getAdress());
		this.txtEmail.setText(medicalProfessional.getEmail());
		this.txtPhoneNumber.setText(phone);
		this.dobPicker.setValue(medicalProfessional.getDob());
		this.sexChoiceBox.setValue(medicalProfessional.getSex());
		this.txtSpecialty.setText(medicalProfessional.getProfession());

		//get the contact information
		int id= medicalProfessional.getEmployee_contractId();
		Employee_Contract contract = dbConnection.searchContractById(id);

		this.txtHolidays.setText(String.valueOf(contract.getFree_days()));
		this.finishingDatePicker.setValue(contract.getFinishing_date());
		this.startingDatePicker.setValue(contract.getStarting_date());
		this.txtSalary.setText(String.valueOf(contract.getSalary()));
		this.txtSalary.setText(String.valueOf(contract.getWeek_hours()));


		// load the image

	}

	public void updateMedicalProfessional(ActionEvent event){


		String name = txtName.getText();
		String sex = sexChoiceBox.getValue();
		LocalDate dob = dobPicker.getValue();
		String nif = txtNif.getText();
		String email = txtEmail.getText();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String specialty = txtSpecialty.getText();
		String dep = departmentChoicebox.getValue();

		String holidays = txtHolidays.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		LocalDate finishingDate = finishingDatePicker.getValue();
		String weeklyHours = txtWeekh.getText();
		String salary = txtSalary.getText();

		Employee_Contract newContract = new Employee_Contract(Integer.parseInt(holidays), startingDate, finishingDate, Float.parseFloat(weeklyHours),Float.parseFloat(salary));
		dbConnection.updateContract(newContract);

		int contract_id = dbConnection.lastId();
		int dep_id = dbConnection.getDepId(dep);

		MedicalProfessional newMedicalProfessional = new MedicalProfessional(name, dob, sex, specialty , email, adress, Integer.parseInt(phoneNumber), nif, dep_id, contract_id);
		dbConnection.updateMedicalProfessional(newMedicalProfessional);


	}


	//Method to go back to the log in set scene
	public void backToLoginButton(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

	}

	//method to edit a patient
	/*public void updateMedicalProffesional(ActionEvent event){

		//New data

		String name = txtName.getText();
		String nie = txtNif.getText();
		LocalDate dob = dobPicker.getValue();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String email = txtEmail.getText();
		String sex = sexChoiceBox.getValue();
		String specialty = txtSpecialty.getText();
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


		MedicalProfessional md = new MedicalProfessional(name, dob, sex, specialty , email, adress, Integer.parseInt(phoneNumber), nie, dep_id, contract_id);
		//dbConnection.updateMedicalProfessional(md);

		MedicalProfessional newMedicalProfessional = new MedicalProfessional(name, dob, "female", specialty , email, adress, Integer.parseInt(phoneNumber), nie, department);
		System.out.println(newMedicalProfessional);

		/*byte[] password = functions.createPassword();
		System.out.println("Password: " +password);

		Role role = userManager.getRoleByName("MedicalProfessional");
		System.out.println("Role: " +role);

		User user = new User(name,password,role);
		userManager.createUser(user);
		System.out.println("Usuario creado");
		dbConnection.addMedicalProfessional(newMedicalProfessional);

		}

	}*/

	//Method to go back to the log in set scene
/*
	public void backToLoginButton(ActionEvent event) {


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
		//this.txtDepartment.setText(medicalProfessional.getDepartment());
		//this.departmentChoicebox.setValue(medicalProfessional.getDep_id().name);

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

	}*/

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

	//We create the ChoiceBox

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

	}


	@Override
	public void preloadData(Pacient patient) {
		// TODO Auto-generated method stub

	}


	@Override
	public void preloadDepartment(Department department) {
		// TODO Auto-generated method stub

	}

}
