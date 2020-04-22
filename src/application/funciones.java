package application;

import javafx.scene.control.Alert;

public class funciones {

	public void validarInteger(String string){

		try{

			Integer.parseInt(string);
		}catch(NumberFormatException e){

			this.crearWarningBox("Wrong introduced Data", "The data requierd is an Intger");

		}
	}



public void crearWarningBox(String header, String message){

	Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Box");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();

}

}