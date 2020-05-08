package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import pojos.Pacient;
import pojos.Patientfxml;
import pojos.Treatment;


public class SceneChanger {

	// this method allows to change scenes

	public void changeScenes(ActionEvent event, String viewName, String title) {

		Parent addPatientRoot = null;
		try {
			addPatientRoot = FXMLLoader.load(getClass().getResource(viewName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene newPatientScene = new Scene(addPatientRoot);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(newPatientScene);
		window.setTitle(title);
		window.show();
	}

	// this method change scenes and preload the next scene with a patient
	// object

	public void changeScenesWithData(ActionEvent event, String viewName, String title, Pacient patient,
			ControllerClass controllerClass) {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(parent);

		// access the controller class and preload the volunteer data
		controllerClass = loader.getController();
		controllerClass.preloadData(patient);

		// get the stage from the event that was passed in
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setTitle(title);
		window.setScene(scene);
		window.show();
	}

	public void changeSceneFromlistAllPatientsViewTotreatmentPatientView(ActionEvent event, String viewName,
			Pacient pacient, Integer patientId,TreatmentPatientViewController controllerClass) {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(parent);


		controllerClass = loader.getController();

		controllerClass.preloadData(pacient,patientId);
		controllerClass.loadPatientTreatments();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();
	}

	public void changeSceneFromTreatmentPatientViewToTreatmentUpdate(ActionEvent event, String viewName,
			Treatment treatment,Integer patientId, TreatmentUpdateController controllerClass){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);


		controllerClass = loader.getController();
		controllerClass.preloadData(treatment,patientId);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();

	}

	public void changeSceneFromTreatmentUpdateToTreatmnetPatientView (ActionEvent event, String viewName,
			Integer patientId,TreatmentPatientViewController controllerClass){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);

		controllerClass = loader.getController();

		DBConnection dbConnection = new DBConnection();
		Pacient patient = dbConnection.searchPatientById(patientId);
		controllerClass.preloadData(patient,patientId);
		controllerClass.loadPatientTreatments();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(scene);
		window.show();

	}
	
	public void loadDepartmentWithData(ActionEvent event, String viewName, String title, Department department, ControllerClass controllerClass){

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(parent);

		//access the controller class and preload the department data
		controllerClass = loader.getController();
		controllerClass.preloadDepartment(department);

		//get the stage from the event that was passed in
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setTitle(title);
		window.setScene(scene);
		window.show();
}

}
