package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import pojos.Department;
import pojos.MedicalProfessional;
import pojos.Pacient;
import pojos.Patientfxml;
import pojos.Treatment;


public class SceneChanger {

	//this method allows to change scenes

	public void changeScenes(ActionEvent event, String viewName, String title){

		Parent addPatientRoot = null;
		try {
			addPatientRoot = FXMLLoader.load(getClass().getResource(viewName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene newPatientScene = new Scene(addPatientRoot);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(newPatientScene);
		window.setTitle(title);
		window.show();
	}



	//this method change scenes and preload the next scene with a patient object

	public void changeScenesWithData(ActionEvent event, String viewName, String title, Pacient patient, ControllerClass controllerClass){

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

		//access the controller class and preload the patient data
		controllerClass = loader.getController();
		controllerClass.preloadData(patient);

		//get the stage from the event that was passed in
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setTitle(title);
		window.setScene(scene);
		window.show();
}

	//access the class controler and loads the medical professional information
	public void changeScenesWithMedicalProfessional(ActionEvent event, String viewName, String title, MedicalProfessional mp, NewMedicalProfessionalController controllerClass){

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(viewName));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		Scene scene = new Scene(parent);

		//access the controller class and preload the patient data
		controllerClass = loader.getController();
		controllerClass.preloadMedicalProfessional(mp);

		//get the stage from the event that was passed in
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setTitle(title);
		window.setScene(scene);
		window.show();
}
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


