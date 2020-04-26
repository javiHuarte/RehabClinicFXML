package application;

import java.awt.List;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 @FXML private Button addButton;
 @FXML private ChoiceBox departmentsChoiceBox;



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

	 String name = txtName.getText();
	 System.out.println(name);

}

}
