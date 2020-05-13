package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.scene.control.Alert;

public class Funciones {

	public boolean validarInteger(String string) {

		try {

			Integer.parseInt(string);

		} catch (Exception e) {
			return true;
		}
		// return false si está bien
		return false;

	}

	public void crearWarningBox(String header, String message) {

		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Box");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();

	}

	public byte[] createPassword() {
		byte [] hash = null;
		String pswd = "";
		String key = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzñÑ";
		int length = 15;
		for (int i = 0; i < length; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		try{
			String pswd1 = "hola";
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pswd1.getBytes());
			hash = md.digest();
		} catch (Exception e){
			e.printStackTrace();
		}
		return hash;
	}
}