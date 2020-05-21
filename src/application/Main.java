 package application;


import java.util.ArrayList;
import java.util.List;

import application.*;
import db.interfaces.DBManager;
import db.interfaces.PacientManager;

import db.interfaces.PacientManager;

import db.interfaces.UserManager;
import db.jpa.JPAUserManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.*;


import db.interfaces.DBManager;
import db.interfaces.DepartmentManager;
import db.interfaces.PacientManager;
import db.sqlite.SQLiteManager;
import pojos.*;
import pojos.users.Role;


public class Main extends Application {

	Stage stage;

	private static DBManager dbManager = new SQLiteManager();
	private static UserManager userManager = new JPAUserManager();
	private static PacientManager pacientManager;
	private static Funciones functions;


	@Override
	public void start(Stage stage) {
		try {
			//dbManager.createTables();
			//BorderPane root = new BorderPane();
		//	Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			//primaryStage.show();


		//Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("listAllPatientsView.fxml"));

		//Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("newStaff.fxml"));
		//Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("departmentView.fxml"));


		Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("medicalProfessionalView.fxml"));

		Scene newMenu = new Scene(menuSceneRoot);
		stage.setScene(newMenu);
		stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public  Stage getStage(){
		return stage;
	}


public static void main(String[] args) throws Exception {

	dbManager.connect();
	userManager.connect();
	//System.out.println(functions.createPassword());
	//System.out.println(userManager.getRoleByName("MedicalProfessional"));
	//Comprobar si están creados los roles
	//List<Role> roles = userManager.getRoles();
	/*if (roles.size() == 0){
		userManager.createRoles();
	}*/
		launch(args);
	dbManager.disconnect();
	userManager.disconnect();


	}
}
