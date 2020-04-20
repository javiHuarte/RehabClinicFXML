package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pojos.Patient;

public class NewPatientController implements Initializable, ControllerClass {

@FXML private TextField txtName, txtNif, txtPhoneNumber, txtAdress, txtEmail, txtIntern;
@FXML private ChoiceBox<String> sexChoiceBox;
@FXML private RadioButton radioButtonYes, radioButtonNo;
@FXML private ToggleGroup internGroup;
@FXML private DatePicker dobPicker;
@FXML private ImageView imageView;
@FXML private TitledPane titledPane;



private File imageFile;
private Patient patient;


@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub


	ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female","Other");
	sexChoiceBox.setItems(sexList);
	sexChoiceBox.setValue("Male");

	//load de default image
	try{
		imageFile = new File("./images/defaultImage.png");
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		imageView.setImage(image);

	}catch(IOException e){
		System.err.println(e.getMessage());
	}

}

public void introducePatient(){

	System.out.println("hola");

	String name = txtName.getText();
	String nif = txtNif.getText();
	String phoneNumber = txtPhoneNumber.getText();
	String adress = txtAdress.getText();
	String email = txtEmail.getText();
	String sex = sexChoiceBox.getValue();
	LocalDate dob = dobPicker.getValue();
	String intern = "yes"; //miraaaar esto
	Integer id = 1; //ojo


	//create and send the information message

	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Information Message");
	alert.setHeaderText("Please check the data");
	alert.setContentText("Click details to see the new patient");

	ButtonType okButton = new ButtonType("Ok");
	ButtonType cancelButton = new ButtonType("Cancel");

	TextArea textArea = new TextArea();
	textArea.setDisable(true);
	textArea.setText("Name: "+ name + ", Dob: " + dob + ", Nif: " + nif + ", Phone Number: "+ phoneNumber);
	alert.getDialogPane().setExpandableContent(textArea);

	alert.getButtonTypes().setAll(okButton, cancelButton);
	Optional<ButtonType> result = alert.showAndWait();

	//alert.showAndWait();

	if(result.get() ==okButton){

		Patient patient = new Patient(id, name, nif, sex, dob, adress, email, phoneNumber, intern);
		System.out.println(patient);

}
}

public void backToLogin(ActionEvent event){

	SceneChanger sceneChanger = new SceneChanger();
	sceneChanger.changeScenes(event, "receptionistLogin.fxml", "Receptionist Login");

	}

public void comprobarData(){


	Boolean validData = true;
	String message="the following fields cannot be empty: ";

	if(this.txtName.getText().equals("")){
		message+="Name, ";
		validData = false;
	}

	if(this.txtNif.getText().equals("")){
		message+="Nif, ";
		validData = false;
	}

	if(this.dobPicker.getValue()==null){
		message+= "Date of birth, ";
		validData = false;
	}

	if(this.txtAdress.getText().equals("")){
		message+="Adress, ";
		validData = false;
	}

	if(this.txtPhoneNumber.getText().equals("")){
		message+="Phone Number, ";
		validData = false;
	}

	if(this.txtEmail.getText().equals("")){
		message+="Email";
		validData = false;
	}

	if(validData==false){

		Alert warning = new Alert(Alert.AlertType.WARNING);


	}

}

public void changeDefaultImage(ActionEvent event){

	System.out.print("hola");
	//get the stage to open a new window (stage in Java fx)

	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	//Instantiate a FileChooser object

	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Open Image");

	//filter for .jpg or .png

	FileChooser.ExtensionFilter jpgFilter = new ExtensionFilter("Image File (*.jpg)", "*.jpg");
	FileChooser.ExtensionFilter pngFilter = new ExtensionFilter("Image File (*.png)", "*.png");

	fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);

	//set to the user´s picture directory or user directory if not available

	String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
	File userDirectory = new File(userDirectoryString);

	//if you cannot navigate to the pictures go to the user home

	if(!userDirectory.canRead())
		userDirectory = new File(System.getProperty("user.home"));


	fileChooser.setInitialDirectory(userDirectory);

	//open the file dialog window

	imageFile = fileChooser.showOpenDialog(stage);

	//update the imageView with the new image

	if(imageFile.isFile()){

		try{

			BufferedImage bufferedImage = ImageIO.read(imageFile);
			Image newImage = SwingFXUtils.toFXImage(bufferedImage, null);
			imageView.setImage(newImage);

		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}




}

public void clear(){

	txtName.clear();
	txtNif.clear();
	txtAdress.clear();
	txtEmail.clear();
	txtPhoneNumber.clear();
	sexChoiceBox.setValue("Male");
}

@Override
public void preloadData(Patient patient) {
	// TODO Auto-generated method stub

	this.patient = patient;

	//this.titledPane.setText("Edit Patient");

	this.txtName.setText(patient.getName());
	this.txtNif.setText(patient.getNif());
	this.txtAdress.setText(patient.getAdress());
	this.txtEmail.setText(patient.getEmail());
	this.txtPhoneNumber.setText(patient.getPhoneNumber());
	this.dobPicker.setValue(patient.getDob());
	this.sexChoiceBox.setValue(patient.getSex());
	//falta hacer el intern

	//load the image



}



}
