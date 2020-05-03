package application;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DirectorLoginController implements Initializable{

	@FXML private Button logOutButton;
	@FXML private Button newMedicalProfessionalButton;
	@FXML private Button newStafftButton;
	@FXML private Button listMdButton;
	@FXML private Button listStaffButton;

	@FXML private ImageView logo, userImage;
	@FXML private ChoiceBox<String> SearchChoiceBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		//Search by choice button

		ObservableList<String> choices  = FXCollections.observableArrayList("Medical Professional ID", "Medical Professional Name", "Staff Id", "Staff Name");
		SearchChoiceBox.setItems(choices);
		SearchChoiceBox.setValue("Medical Professional ID");
	}

	//Method that allows to go from the director login scene
	//to the New Medical Professional Scene

	public void newMedicalProffesional(ActionEvent event){

		SceneChanger sc = new SceneChanger();
		sc.changeScenes(event, "newMedicalProfessional.fxml", "New Medical Professional");
	}

	//Method that allows to go from the director login scene
	//to list the medical professionals

	public void ListMedicalProffesional(ActionEvent event){

		SceneChanger sc = new SceneChanger();
		sc.changeScenes(event, "mdView.fxml", "List Medical Professionals");
	}
	//

}
