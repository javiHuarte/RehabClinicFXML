package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class MenuController {

	private Button goButton;
	private PasswordField passwordField;
	private TextField userTextField;

	/*public void addPatient(ActionEvent event) throws IOException{

		Parent addPatientRoot = FXMLLoader.load(getClass().getResource("newPatient.fxml"));

		Scene newPatientScene = new Scene(addPatientRoot);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(newPatientScene);
		window.show();

	}*/
	
	public void goButton (ActionEvent event){
		
		String user = userTextField.getText();
		String password = passwordField.getText();
		SceneChanger sceneChanger = new SceneChanger();
		//Metodo para logear al usuario
		
		
		sceneChanger.changeScenes(event, "medicalProfessionalLogin.fxml", "MedicalProfessional");
		
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director");
		
		sceneChanger.changeScenes(event, "receptionistLogin.fxml", "MedicalProfessional");
	}

}
