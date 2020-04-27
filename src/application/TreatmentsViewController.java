package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojos.Pacient;
import pojos.Treatment;

public class TreatmentsViewController implements Initializable {

	@FXML private TableView<Treatment> treatmentTable = null;

	@FXML private TableColumn<Treatment, Integer> idColumn;
	@FXML private TableColumn<Pacient, String> nameColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub








	}

}
