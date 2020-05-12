package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import pojos.Department;
import pojos.Employee_Contract;
import pojos.MedicalProfessional;
import pojos.Pacient;
import pojos.Staff;

public class NewStaffController implements Initializable {

	// Staff
	@FXML
	private TextField txtName, txtNif, txtPhoneNumber, txtAdress, txtEmail, txtProfession;
	@FXML
	private ChoiceBox<String> sexChoiceBox, departmentChoiceBox;
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


	// Contract
	@FXML
	private TextField txtHolidays, txtStartingDate, txtFinishDate, txtWorkingHours, txtSalary;

	@FXML
	private DatePicker dStartingDate, dFinishDate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub


		// set update button disable
		this.updateButton.setDisable(true);

		// set addButton not not disable
		this.addButton.setDisable(false);

		// creamos el sexChoiceBox

		ObservableList<String> sexList = FXCollections.observableArrayList("Male", "Female", "Other");
		sexChoiceBox.setItems(sexList);
		sexChoiceBox.setValue("Male");

		//Creamos el choicebox de departmet
		ObservableList<String> departmentList = FXCollections.observableArrayList();

		//bucle for each para sacar el nombre de todos los departamentos
		ArrayList<Department> dep = new ArrayList();
		dep.addAll(dbConnection.listAllDepartments());
		for (Department department: dep){
			departmentList.add(department.getName());
		}

		departmentChoiceBox.setItems(departmentList);
		//departmentList.addAll(dbConnection.listAllDepartments().getClass().getName());

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

		//obtener el id del departemnt seleccionado
		String department = departmentChoiceBox.getValue();
		Integer dep_id = dbConnection.getDepartmentId(department);

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


		//comprobar que se ha introducido un departamento
		if ( department == null){

			Alert alertDep = new Alert(Alert.AlertType.ERROR);
			alertDep.setTitle("No Such Department");
			alertDep.setContentText("There are no departments created"
								 + "Create a new department first");
			ButtonType newDepartementButton = new ButtonType("Create new department");

			alertDep.getButtonTypes().setAll(newDepartementButton);
			Optional<ButtonType> resultDep = alertDep.showAndWait();

			if(resultDep.get() == newDepartementButton){

				SceneChanger sc = new SceneChanger();
				sc.changeScenes(event, "newDepartment.fxml", "New Departement");
			}
		}

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

				Staff newStaff = new Staff( name, dob,  sex,  profession, email,  adress, phoneNumber,nif, dep_id,contract_id);
				dbConnection.addStaff(newStaff);
			}
			}

	}

	public void updateStaff(ActionEvent event) {

		// Pedir datos del Staff
		String name = txtName.getText();
		String sex = sexChoiceBox.getValue();
		LocalDate dob = dobPicker.getValue();
		String nif = txtNif.getText();
		String email = txtEmail.getText();
		String adress = txtAdress.getText();
		int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
		String profession = txtProfession.getText();
		int department = 0;

		Staff newStaff = new Staff(name, dob, sex, profession, email, adress, phoneNumber, nif, department);
		dbConnection.//A parte de añadir el dbconction recordar que hay que cambiar department y que no pille un entero

	}

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
//Aqui en limpiar faltan añadir la que limpia el department y contract.
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

		// load the image

	}

}
