package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pojos.Department;
import pojos.MedicalProfessional;
import pojos.Pacient;

public class NewDepartmentController implements Initializable, ControllerClass {

	private DBConnection dbConnection = new DBConnection();
	@FXML
	private TextField txtName, txtFloor, txtBudget, txtBoss;
	@FXML
	private Button addButton, updateButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub


		updateButton.setDisable(true);
	}

	public void addDepartment(ActionEvent event) {

		String floorString = txtFloor.getText();
		Integer floor = Integer.parseInt(floorString);
		String budgetString = txtBudget.getText();
		Float budget = Float.parseFloat(budgetString);
		String name = txtName.getText();

		Department department = new Department(name, floor, budget);

		this.dbConnection.addDepartment(department);

	}

	public void clear(ActionEvent event) {

		this.txtBudget.clear();
		this.txtFloor.clear();
		this.txtName.clear();

	}

	@Override
	public void preloadData(Pacient patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preloadDepartment(Department dep) {

		//activamos el updateButton y desactivamos el add

		this.addButton.setDisable(true);
		this.updateButton.setDisable(false);

		this.txtName.setText(dep.getName());
		this.txtBudget.setText(String.valueOf(dep.getBudget()));
		this.txtFloor.setText(String.valueOf(dep.getFloor()));

	}

	@Override
	public void preloadMedicalProfessional(MedicalProfessional medicalProfessional) {
		// TODO Auto-generated method stub

	}

}
