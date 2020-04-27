package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pojos.Department;



public class NewDepartmentController implements Initializable {

	private DBConnection dbConnection = new DBConnection();
@FXML private TextField txtName, txtFloor, txtBudget, txtBoss;

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

}



public void addDepartment(ActionEvent event){

	String floorString = txtFloor.getText();
	Integer floor = Integer.parseInt(floorString);
	String budgetString  = txtBudget.getText();
	Float budget = Float.parseFloat(budgetString);
	String name = txtName.getText();



	Department department = new Department(name, floor, budget);
	//System.out.println(department);

	this.dbConnection.addDepartment(department);


}

public void clear(ActionEvent event){

}

}
