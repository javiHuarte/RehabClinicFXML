package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

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
import pojos.Pacient;
import pojos.Patientfxml;

public class ReceptionistLoginController implements Initializable{


	private File imageFile;
	@FXML private ImageView logo, userImage;
	@FXML private ChoiceBox<String> searchByChoiceBox;
	@FXML private TextField txtSearchBy;
	@FXML private Button searchByButton;
	private DBConnection dbConnection = new DBConnection();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//searchByButton.setDisable(true);

		ObservableList<String> searchByList = FXCollections.observableArrayList("ID", "Name");
		searchByChoiceBox.setItems(searchByList);
		searchByChoiceBox.setValue("ID");

		//load de default image
		try{
			imageFile = new File("./images/logo.png");
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			logo.setImage(image);

		}catch(IOException e){
			System.err.println(e.getMessage());
		}

		try{
			imageFile = new File("./images/defaultImage.png");
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			userImage.setImage(image);

		}catch(IOException e){
			System.err.println(e.getMessage());
		}
	}

	public void newPatientButton(ActionEvent event){

		SceneChanger sc = new SceneChanger();
		sc.changeScenes(event, "newPatient.fxml", "New Patient");
	}

	public void listAll(ActionEvent event){

		System.out.print("hola");
		SceneChanger sc = new SceneChanger();
		sc.changeScenes(event, "patientView.fxml", "List All Patients");
	}

	public void searchBy(ActionEvent event){

	String choice =	this.searchByChoiceBox.getValue();


	if(choice.equals("Name")){
		searchByName(event);
		}else{
		searchById(event);
		}

	}

	public void searchById(ActionEvent event){

		String stringId = txtSearchBy.getText();

		try{
		Integer id = Integer.parseInt(stringId);



		}catch (NumberFormatException e) {

			Alert warning = new Alert(Alert.AlertType.ERROR);
			warning.setTitle("Error Message");
			warning.setHeaderText("Wrong Introduced Data");
			warning.setContentText("ID has to be an integer");
			warning.showAndWait();
		}

	}

	public void searchByName(ActionEvent event){

		String name = this.txtSearchBy.getText();



			SceneChanger sc = new SceneChanger();
			NewPatientController npc = new NewPatientController();
			List<Pacient> listaPacient = dbConnection.searchPacientByName(name);
			sc.changeScenesWithData(event, "newPatient.fxml", "Edit Patient", listaPacient.get(0), npc);
		/*}else{
			Alert info = new Alert(Alert.AlertType.INFORMATION);
			info.setTitle("Information Message");
			info.setHeaderText("Check the introduced Data");
			info.setContentText("The patient does not exist");
			info.showAndWait();


		}*/

		}


	public void signOut(ActionEvent event){

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Information Message");
		alert.setContentText("Are you sure that you want to SignOut?");
		alert.setHeaderText("Confirmation Required");

		ButtonType yesButton = new ButtonType("Yes");
		ButtonType noButton = new ButtonType("No");

		alert.getButtonTypes().setAll(yesButton, noButton);
		Optional<ButtonType> result = alert.showAndWait();

		if(result.get() ==yesButton){

			SceneChanger sc = new SceneChanger();
			sc.changeScenes(event, "menu.fxml", "Initial Menu");
		}

	}

	public void pushedButton(){

		searchByButton.setDisable(false);
	}


}
