package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import pojos.Department;
import pojos.Employee_Contract;
import pojos.Staff;

public class NewStaffControllerPrueba  implements Initializable {
	@FXML
	private TitledPane titlePane;
	@FXML
	private Button  addButton;
	
	// Staff
		@FXML
		private TextField txtName, txtNif, txtPhoneNumber, txtAdress, txtEmail, txtProfession;
		@FXML
		private ChoiceBox<String> sexChoiceBox;
		@FXML
		private DatePicker dobPicker;

		private DBConnection dbConnection = new DBConnection();


	public void introduceStaff(ActionEvent event) {

		// Pedir datos del Staff
		String name = txtName.getText();
		String sex = sexChoiceBox.getValue();
		LocalDate dob = dobPicker.getValue();
		String nif = txtNif.getText();
		String email = txtEmail.getText();
		String adress = txtAdress.getText();
		int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
		String profession = txtProfession.getText();
		
		int depId = 0;
		int contractId = 0;
		
		Staff newStaff = new Staff( name, dob,  sex,  profession, email,  adress, phoneNumber,nif, depId,contractId);
		dbConnection.addStaff(newStaff);
			}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

			// set addButton not not disable
			this.addButton.setDisable(false);

			// creamos el sexChoiceBox
			ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female", "Other");
			sexChoiceBox.setItems(sexList);
			sexChoiceBox.setValue("Male");

		}
	



	}

