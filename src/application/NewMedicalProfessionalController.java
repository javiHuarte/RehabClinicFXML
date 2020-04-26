package application;

import java.awt.List;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import java.util.*;
import pojos.*;

public class NewMedicalProfessionalController implements Initializable {

	private DBConnection dbConnection = new DBConnection();

	@FXML private TextField txtName;
	@FXML private TextField txtNif;
	@FXML private TextField txtPhoneNumber;
	@FXML private TextField txtEmail;
	@FXML private TextField txtAdress;
	@FXML private TextField txtSpecialist  ;

	@FXML private ChoiceBox<String> sexCoiceBox;
	@FXML private DatePicker dobPicker;

	@FXML private Button backButton;
	@FXML private Button clearButton;
	@FXML private Button addButton;

	//this method saves the medical professional
	public void addMedicalProfessional(ActionEvent event){

	}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub



	//departmentList.addAll(dbConnection.listAllDepartments().getClass().getName());
	ObservableList<String> departmentList1 = FXCollections.observableArrayList();


Department dep;

	//sexChoiceBox.setValue("");

	//departmentsChoiceBox.setItems(departmentList);

}

public void addNewMedicalProfessional(){


}

}
