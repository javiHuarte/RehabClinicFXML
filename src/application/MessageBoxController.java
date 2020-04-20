package application;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MessageBoxController implements Initializable {

	@FXML private ImageView alertImage;
	@FXML private Label messageLabel, detailsLabel;

	private File imageFile;
	private Stage window;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		try{
			imageFile = new File("./images/alertImage.png");
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			alertImage.setImage(image);

		}catch(IOException e){
			System.err.println(e.getMessage());
		}

		this.messageLabel.setText("hhola");
		this.detailsLabel.setText("adios");

	}

	public void show(String title, String details){

		//this.messageLabel.setText("message");
		//this.detailsLabel.setText("details");


		try {

			Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene= new Scene(root);
			window.setScene(scene);
			window.setTitle("Message Box");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("hola");

		//Scene scene= new Scene(root);
		//window = new Stage();



		/* sc = new SceneChanger();
		sc.showMessageBox();*/

	}

	public void closeButton(ActionEvent event){

		this.window.close();
	}



}
