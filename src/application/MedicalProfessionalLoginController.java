package application;

import java.net.URL;
import java.util.ResourceBundle;

import db.interfaces.DBManager;
import db.sqlite.SQLiteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MedicalProfessionalLoginController implements Initializable {


	@FXML
	private Button patientsButton, treatmentsButton;

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	

	}

	public void goPatients (ActionEvent event){

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "listAllPatientsView.fxml", "Patients");
	}

	public void goTreatments (ActionEvent event){

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "allTreatmentsView.fxml", "Patients");
	}

}
