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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import pojos.Department;
import pojos.Pacient;
import pojos.Patientfxml;

public class NewPatientController implements Initializable, ControllerClass {

	@FXML
	private TextField txtName, txtNif, txtPhoneNumber, txtAdress, txtEmail, txtIntern;
	@FXML
	private ChoiceBox<String> sexChoiceBox, internChoiceBox;
	@FXML
	private RadioButton radioButtonYes, radioButtonNo;
	@FXML
	private ToggleGroup internGroup;
	@FXML
	private DatePicker dobPicker;
	@FXML
	private ImageView imageView;
	@FXML
	private TitledPane titledPane;
	@FXML
	private Label lblNameError, lblAdressError, lblDobError, lblPhoneError, lblEmailError, lblNifError;
@FXML Button updateButton, addButton;

	private File imageFile;
	private Pacient patient;
	private Funciones funciones = new Funciones();
	private DBConnection dbConnection = new DBConnection();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//set update button disable

		this.updateButton.setDisable(true);

		//set addButton not not disable

		this.addButton.setDisable(false);

		//creamos el sexChoiceBox

		ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female", "Other");
		sexChoiceBox.setItems(sexList);
		sexChoiceBox.setValue("Male");

		//creamos el internChoiceBox

		ObservableList<String> internList = FXCollections.observableArrayList("No", "Yes");
		internChoiceBox.setItems(internList);
		internChoiceBox.setValue("No");



		// load de default image
		try {
			imageFile = new File("./images/defaultImage.png");
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageView.setImage(image);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	public void introducePatient() {

		//Pedir datos del paciente
		String name = txtName.getText();
		String sex = sexChoiceBox.getValue();
		LocalDate dob = dobPicker.getValue();
		String nif = txtNif.getText();
		String email = txtEmail.getText();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String internString = internChoiceBox.getValue();
		Boolean intern;

		if(internString.equals("Yes")){
		intern = true;
		}else intern = false;

		Boolean active = true;

		Boolean validData = comprobarData();

		// create and send the information message

		if (validData == true) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText("Please check the data");
			alert.setContentText("Click details to see the new patient");

			ButtonType okButton = new ButtonType("Ok");
			ButtonType cancelButton = new ButtonType("Cancel");

			TextArea textArea = new TextArea();
			textArea.setText("Name: " + name + ", Dob: " + dob + ", Nif: " + nif + ", Phone Number: " + phoneNumber);
			alert.getDialogPane().setExpandableContent(textArea);

			alert.getButtonTypes().setAll(okButton, cancelButton);
			Optional<ButtonType> result = alert.showAndWait();



			if (result.get() == okButton) {

				Pacient pacient = new Pacient(name, nif, dob, email, Integer.parseInt(phoneNumber), adress,sex, intern, active);
				dbConnection.addPacient(pacient);

			}
		}
	}

	public void updatePatient(ActionEvent event){

		//Pedir datos del paciente
		String name = txtName.getText();
		String sex = sexChoiceBox.getValue();
		LocalDate dob = dobPicker.getValue();
		String nif = txtNif.getText();
		String email = txtEmail.getText();
		String adress = txtAdress.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String internString = internChoiceBox.getValue();
		Boolean intern;

		if(internString.equals("Yes")){
		intern = true;
		}else intern = false;

		Boolean active = true;

		Pacient pacient = new Pacient(name, nif, dob, email, Integer.parseInt(phoneNumber), adress,sex, intern, active);
		dbConnection.updatePacient(pacient);


	}


	public void backToLogin(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "receptionistLogin.fxml", "Receptionist Login");

	}

	public boolean comprobarData() {

		Boolean validData = true;

		//ponemos todas las etiquetas en blanco

		this.lblAdressError.setText("");
		this.lblNameError.setText("");
		this.lblPhoneError.setText("");
		this.lblDobError.setText("");
		this.lblEmailError.setText("");
		this.lblNifError.setText("");

		if (this.txtName.getText().equals("")) {

			validData = false;
			this.lblNameError.setText(" *");
		}

		if (this.txtNif.getText().equals("")) {

			validData = false;
			this.lblNifError.setText(" *");
		}

		if (this.dobPicker.getValue() == null) {

			validData = false;
			this.lblDobError.setText(" *");

		}

		if (this.txtAdress.getText().equals("")) {

			validData = false;
			this.lblAdressError.setText(" *");
		}

		if (this.txtPhoneNumber.getText().equals("") || funciones.validarInteger(this.txtPhoneNumber.getText()) ) {

			validData = false;
			this.lblPhoneError.setText(" *");
		}

		if (this.txtEmail.getText().equals("")) {

			validData = false;
			this.lblEmailError.setText(" *");
		}

		if (validData == false) {

			Alert warning = new Alert(Alert.AlertType.WARNING);
			warning.setTitle("Warning message");
			warning.setHeaderText("Check the introduced Data");
			warning.setContentText("Please, fill all the mandatory fields");
			warning.showAndWait();
		}

		return validData;

	}

	public void changeDefaultImage(ActionEvent event) {

		System.out.print("hola");
		// get the stage to open a new window (stage in Java fx)

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Instantiate a FileChooser object

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image");

		// filter for .jpg or .png

		FileChooser.ExtensionFilter jpgFilter = new ExtensionFilter("Image File (*.jpg)", "*.jpg");
		FileChooser.ExtensionFilter pngFilter = new ExtensionFilter("Image File (*.png)", "*.png");

		fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);

		// set to the user´s picture directory or user directory if not
		// available

		String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
		File userDirectory = new File(userDirectoryString);

		// if you cannot navigate to the pictures go to the user home

		if (!userDirectory.canRead())
			userDirectory = new File(System.getProperty("user.home"));

		fileChooser.setInitialDirectory(userDirectory);

		// open the file dialog window

		imageFile = fileChooser.showOpenDialog(stage);

		// update the imageView with the new image

		if (imageFile.isFile()) {

			try {

				BufferedImage bufferedImage = ImageIO.read(imageFile);
				Image newImage = SwingFXUtils.toFXImage(bufferedImage, null);
				imageView.setImage(newImage);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void clear() {

		txtName.clear();
		txtNif.clear();
		txtAdress.clear();
		txtEmail.clear();
		txtPhoneNumber.clear();
		sexChoiceBox.setValue("Male");
		this.dobPicker.setValue(LocalDate.now());
		this.internChoiceBox.setValue("No");

	}

	@Override
	public void preloadData(Pacient patient) {
		// TODO Auto-generated method stub

		this.patient = patient;

		this.updateButton.setDisable(false);
		this.addButton.setDisable(true);

		int phoneNumber = patient.getPhoneNumber();
		String phone = String.valueOf(phoneNumber);

		this.txtName.setText(patient.getName());
		this.txtNif.setText(patient.getNie());
		this.txtAdress.setText(patient.getAdress());
		this.txtEmail.setText(patient.getEmail());
		this.txtPhoneNumber.setText(phone);
		this.dobPicker.setValue(patient.getDob());
		this.sexChoiceBox.setValue(patient.getSex());

		Boolean intern = patient.getIntern();
		if(intern == true){
		this.internChoiceBox.setValue("Yes");
		}else this.internChoiceBox.setValue("No");

		// load the image

	}

	@Override
	public void preloadDepartment(Department department) {
		// TODO Auto-generated method stub

	}

}
