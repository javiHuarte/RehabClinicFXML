package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import pojos.Patientfxml;

public class ReceptionistLoginController implements Initializable{


	private File imageFile;
	@FXML private ImageView logo, userImage;
	@FXML private ChoiceBox<String> searchByChoiceBox;
	@FXML private TextField txtSearchBy;
	@FXML private Button searchByButton;


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



	}

	public void searchByName(ActionEvent event){

		String name = this.txtSearchBy.getText();


		Patientfxml patient1 = new Patientfxml(1, "juan", "54448314T", "male",LocalDate.of(1995,Month.APRIL,9), "paseo del parque 4", "jjhua@gmail.com", "662223636", "YES");
		Patientfxml patient2 = new Patientfxml(2, "yoan", "54448314T", "male", LocalDate.of(1998,Month.APRIL,23), "pase castellana", "jjhua@gmail.com", "662223636", "YES");

		if(name.equals("juan")){

			SceneChanger sc = new SceneChanger();
			NewPatientController npc = new NewPatientController();
			sc.changeScenesWithData(event, "newPatient.fxml", "Edit Patient", patient1, npc);
		}else{
			System.out.println("nada d enada");
		}

		}



	/*public boolean comprobarId(String id){

		try{

			Integer id1 = Integer.parseInt(id);
		}catch(NumberFormatException e){
			return false;
		}

		return true;
	}*/

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
