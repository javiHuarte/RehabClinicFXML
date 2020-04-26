package application;

import java.awt.List;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import pojos.Pacient;

public class listAllPatientsViewController implements Initializable {


	@FXML private ListView listViewAllPatients;

	private DBConnection dbConnection = new DBConnection();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Create a variable listAllPatients and introduce in it all the patients in order to
		//Call once the dataBase
		ArrayList<Pacient> listAllPatients = (ArrayList<Pacient>) dbConnection.listAllPacients();
		ArrayList<String> listNamesPatients = new ArrayList<String>();


		//Store only the names of the patients
		for(int i = 0; i < listAllPatients.size() ; i++){
			listNamesPatients.add(listAllPatients.get(i).getName());

		}

		Pacient pepe = new Pacient("pepe", "nie",LocalDate.of(1995, Month.APRIL, 9),"correo",94342,"casa","male",true,true);
		//Show the name of the patients in the listView
		listViewAllPatients.getItems().addAll(dbConnection.listAllPacients());
		listViewAllPatients.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}


}
