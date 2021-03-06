package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import pojos.MedicalProfessional;
import pojos.Pacient;
import pojos.Staff;

public class NewStaffController implements Initializable {

	@FXML
	private TextField txtName, txtNif, txtPhoneNumber, txtAdress, txtEmail, txtProfession;
	@FXML
	private ChoiceBox<String> sexChoiceBox, departmentChoiceBox, contractChoiceBox;
	/*
	 * @FXML private RadioButton radioButtonYes, radioButtonNo;
	 *
	 * @FXML private ToggleGroup internGroup;
	 */
	@FXML
	private DatePicker dobPicker;

	@FXML
	private ImageView imageView;

	@FXML
	private TitledPane titlePane;

	@FXML
	private Label lblProfession, lblNameError, lblAdressError, lblDobError, lblPhoneError, lblEmailError, lblNifError;

	@FXML
	private Button clearButton, addButton, backButton, updateButton;

	private File imageFile;
	private Staff staff;
	private Funciones funciones = new Funciones();
	private DBConnection dbConnection = new DBConnection();

<<<<<<< HEAD

	// Contract
	@FXML
	private TextField txtHolidays, txtWorkingHours, txtSalary;

	@FXML
	private DatePicker dStartingDate, dFinishDate;

=======
>>>>>>> branch 'master' of https://github.com/javiHuarte/RehabClinicFXML.git
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		//Para que es esto?
		// set update button disable
		//this.updateButton.setDisable(true);

		// set addButton not not disable
		this.addButton.setDisable(false);

		// creamos el sexChoiceBox

		ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female", "Other");
		sexChoiceBox.setItems(sexList);
		sexChoiceBox.setValue("Male");

		//falta crear el choicebox de departmet y contract a ver como lo hacemos

	}

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

		//como cojer el department y contract

		int department = 0;


		Staff newStaff = new Staff(name, dob, sex, profession, email, adress, phoneNumber, nif, department);
		dbConnection.//A parte de a�adir el dbconction recordar que hay que cambiar department y que no pille un entero

		Boolean validData = comprobarData();

		// create and send the information message
		if (validData == true) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText("Please check the data");
			alert.setContentText("Click details to see the new staff");

			ButtonType okButton = new ButtonType("Ok");
			ButtonType cancelButton = new ButtonType("Cancel");

			TextArea textArea = new TextArea();
			textArea.setText("Name: " + name + ", Dob: " + dob + ", Nif: " + nif + ", Phone Number: " + phoneNumber +", Profession: " + profession);
			alert.getDialogPane().setExpandableContent(textArea);

			alert.getButtonTypes().setAll(okButton, cancelButton);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == okButton) {

				Staff staff = new Staff(name, dob, sex, profession, email, adress, phoneNumber, nif, department);
				dbConnection.addStaff(staff);

			}
		}
	}

/*	public void updateStaff(ActionEvent event) {

		// Pedir datos del Staff
		String name = txtName.getText();
		String sex = sexChoiceBox.getValue();
		LocalDate dob = dobPicker.getValue();
		String nif = txtNif.getText();
		String email = txtEmail.getText();
		String adress = txtAdress.getText();
		int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
		String profession = txtProfession.getText();
		Integer dep_id = dbConnection.getDepartmentId(departmentChoiceBox.getValue());

		//contract

		int holidays = Integer.parseInt(txtHolidays.getText());
		LocalDate startingDate = dStartingDate.getValue();
		LocalDate finishDate = dFinishDate.getValue();
		int workingHours = Integer.parseInt(txtWorkingHours.getText());
		float salary = Float.parseFloat(txtSalary.getText());

				//Guardamos el contract en la tabla y recuperamos su id
				Employee_Contract newContract = new Employee_Contract(holidays, startingDate, finishDate,  workingHours, salary);
				dbConnection.addContract(newContract);
				int contract_id = dbConnection.getLastId();

		Staff newStaff = new Staff(name, dob, sex, profession, email, adress, phoneNumber, nif, dep_id);
		dbConnection.//A parte de a�adir el dbconction recordar que hay que cambiar department y que no pille un entero

	}*/

	public void backToLogin(ActionEvent event) {

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "directorLogin.fxml", "Director Login");

	}

	public boolean comprobarData() {

		Boolean validData = true;

		// ponemos todas las etiquetas en blanco

		this.lblProfession.setText("");
		this.lblAdressError.setText("");
		this.lblNameError.setText("");
		this.lblPhoneError.setText("");
		this.lblDobError.setText("");
		this.lblEmailError.setText("");
		this.lblNifError.setText("");

		if(this.txtProfession.getText().equals("")){

			validData = false;
			this.lblProfession.setText(" *");
		}

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

		if (this.txtPhoneNumber.getText().equals("") || funciones.validarInteger(this.txtPhoneNumber.getText())) {

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

		// set to the user�s picture directory or user directory if not
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

		//departmentChoiceBox
		//contractChoiceBox
		txtName.clear();
		txtNif.clear();
		txtAdress.clear();
		txtEmail.clear();
		txtPhoneNumber.clear();
		sexChoiceBox.setValue("Male");
		txtProfession.clear();
		this.dobPicker.setValue(LocalDate.now());

		departmentChoiceBox.setValue(null);

		txtHolidays.clear();
		this.dStartingDate.setValue(LocalDate.now());
		this.dFinishDate.setValue(LocalDate.now());
		txtWorkingHours.clear();
		txtSalary.clear();

	}
// falta cargar imagen y departamento y contract
	public void preloadData(Staff staff) {
		// TODO Auto-generated method stub

		this.staff = staff;

		this.updateButton.setDisable(false);
		this.addButton.setDisable(true);

		int phoneNumber = staff.getPhoneNumber();
		String phone = String.valueOf(phoneNumber);

		this.txtProfession.setText(staff.getProfession());
		this.txtName.setText(staff.getName());
		this.txtNif.setText(staff.getNif());
		this.txtAdress.setText(staff.getAdress());
		this.txtEmail.setText(staff.getEmail());
		this.txtPhoneNumber.setText(phone);
		this.dobPicker.setValue(staff.getDob());
		this.sexChoiceBox.setValue(staff.getSex());


		Employee_Contract newContract = dbConnection.searchContractById(staff.getContract_id());

		int freedays = newContract.getFree_days();
		String holiday = String.valueOf(freedays);
		this.txtHolidays.setText(holiday);
		this.dStartingDate.setValue(newContract.getStarting_date());
		this.dFinishDate.setValue(newContract.getFinishing_date());
		this.txtWorkingHours.setText(String.valueOf(newContract.getWeek_working_hours()));
		this.txtSalary.setText(String.valueOf(newContract.getSalary()));


		// load the image

	}

}
