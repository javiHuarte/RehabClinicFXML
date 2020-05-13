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

	@FXML private ImageView imageView;
	@FXML private Button backButton;
	@FXML private Button clearButton;
	@FXML private Button Add;

	private DBConnection dbConnection = new DBConnection();
	private static UserManager userManager = new JPAUserManager();
	Funciones functions;

	//method that introduces a new medical professional

	public void addButton(ActionEvent event){

		//Pedir datos
<<<<<<< HEAD
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

		//we create the contract when we add the medical professional
		employeeContract();
		
		//we take the last id introduced into the data base which will be the id from the contract associated to this
		//medical professional
		int contract_id = dbConnection.getLastId();
		//get the id associated with the departement name chosen
		int dep_id = dbConnection.getDepartmentId(dep);
		
		MedicalProfessional newMedicalProfessional = new MedicalProfessional(name, dob, sex, specialty , email, adress, Integer.parseInt(phoneNumber), nie, dep_id, contract_id);
		System.out.println(newMedicalProfessional);
		dbConnection.addMedicalProfessional(newMedicalProfessional);

		}

	}

	public void employeeContract(){
		String holidays = txtHolidays.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		LocalDate finishingDate = finisingDatePicker.getValue();
		String weeklyHours = txtWeekh.getText();
		String salary = txtSalary.getText();

		Employee_Contract newContract = new Employee_Contract(Integer.parseInt(holidays), startingDate, finishingDate, Integer.parseInt(weeklyHours),Float.parseFloat(salary));
		dbConnection.addContract(newContract);
	}

	//Method to go back to the log in set scene

	public void backToLoginButton(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

	}

	//method to edit a patient
	public void updateMedicalProffesional(ActionEvent event){

		//New data
=======
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML
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

<<<<<<< HEAD
		MedicalProfessional md = new MedicalProfessional(name, dob, sex, specialty , email, adress, Integer.parseInt(phoneNumber), nie, dep_id, contract_id);
		//dbConnection.updateMedicalProfessional(md);
=======
		MedicalProfessional newMedicalProfessional = new MedicalProfessional(name, dob, "female", specialty , email, adress, Integer.parseInt(phoneNumber), nie, department);
		System.out.println(newMedicalProfessional);

		/*byte[] password = functions.createPassword();
		System.out.println("Password: " +password);
		
		Role role = userManager.getRoleByName("MedicalProfessional");
		System.out.println("Role: " +role);
		
		User user = new User(name,password,role);
		userManager.createUser(user);
		System.out.println("Usuario creado");*/
		dbConnection.addMedicalProfessional(newMedicalProfessional);
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML

		}

	}

	//Method to go back to the log in set scene

	public void backToLoginButton(ActionEvent event) {

<<<<<<< HEAD
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
=======
		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML

	}

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

}
