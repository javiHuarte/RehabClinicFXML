package application;

import javafx.scene.control.Alert;

public class funciones {

	public boolean validarInteger (String string){

		try{

			Integer.parseInt(string);


		}catch(Exception e){
			return true;
		}
		//return false si está bien
		return false;

	}


public void crearWarningBox(String header, String message){

	Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Box");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();

}

}