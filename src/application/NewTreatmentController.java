package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pojos.Treatment;


public class NewTreatmentController implements Initializable {

	@FXML
	private TextField typeTextField;
	@FXML
	private TextArea extraInfoTextArea;
	@FXML
	private DatePicker startingDatePicker, finishingDatePicker;

	private DBConnection dbConnection = new DBConnection();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {




	}


	public void introduceTreatment (){
		String type = typeTextField.getText();
		String extraInfo = extraInfoTextArea.getText();

		Treatment treatment = new Treatment(type, extraInfo);
		dbConnection.addTreatment(treatment);
	}

	public void backToAllTreatmentsViewController (ActionEvent event){

		SceneChanger sceneChanger = new SceneChanger();
		sceneChanger.changeScenes(event, "allTreatmentsView.fxml", "Treatments");
	}


}
