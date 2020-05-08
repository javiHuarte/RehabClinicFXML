package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pojos.Treatment;

public class TreatmentUpdateController implements Initializable{


	@FXML
	private Button backButton,updateButton;
	@FXML
	private TextField typeTextField;
	@FXML
	private DatePicker startDataPicker,finishDataPicker;
	@FXML
	private TextArea extraInfoTextArea;

	private Integer patientId;
	private Integer treatmentId;

	DBConnection dbConnection = new DBConnection();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void preloadData (Treatment treatment, Integer patientId){
		this.patientId = patientId;
		this.treatmentId = treatment.getId();
		this.typeTextField.setText(treatment.getType());
		this.startDataPicker.setValue(treatment.getStarting_date());
		this.finishDataPicker.setValue(treatment.getFinishing_date());
		this.extraInfoTextArea.setText(treatment.getPatient_extra_info());

	}

	public void backButton (ActionEvent event){
		SceneChanger sceneChanger = new SceneChanger();
		TreatmentPatientViewController controller = new TreatmentPatientViewController();
		sceneChanger.changeSceneFromTreatmentUpdateToTreatmnetPatientView(event, "treatmentPatientView.fxml", patientId, controller);
	}

	public void update(){
		Treatment treatment = dbConnection.searchTreatmentById(this.treatmentId);
		treatment.setType(typeTextField.getText());
		treatment.setStarting_date(startDataPicker.getValue());
		treatment.setFinishing_date(finishDataPicker.getValue());
		treatment.setPatient_extra_info(extraInfoTextArea.getText());
		dbConnection.updateTreatment(treatment);
		preloadData(treatment,this.patientId);
	}


}
