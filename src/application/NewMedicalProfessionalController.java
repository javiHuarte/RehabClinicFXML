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

		/*byte[] password = functions.createPassword();
		System.out.println("Password: " +password);
		
		Role role = userManager.getRoleByName("MedicalProfessional");
		System.out.println("Role: " +role);
		
		User user = new User(name,password,role);
		userManager.createUser(user);
		System.out.println("Usuario creado");*/
		dbConnection.addMedicalProfessional(newMedicalProfessional);

		}

	}

	//Method to go back to the log in set scene

	public void backToLoginButton(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

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
