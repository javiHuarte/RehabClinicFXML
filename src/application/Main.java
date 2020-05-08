 package application;


import java.util.ArrayList;
import java.util.List;

import application.*;
import db.interfaces.DBManager;
import db.interfaces.PacientManager;
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


public class Main extends Application {

	Stage stage;

	private static DBManager dbManager = new SQLiteManager();
	private static PacientManager pacientManager;



	@Override
	public void start(Stage stage) {
		try {
			//BorderPane root = new BorderPane();
		//	Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			//primaryStage.show();


		//Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("listAllPatientsView.fxml"));

		Parent menuSceneRoot = FXMLLoader.load(getClass().getResource("medicalProfessionalLogin.fxml"));
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



public static void main(String[] args) {
	
	dbManager.connect();
		launch(args);
	dbManager.disconnect();


	}
}
