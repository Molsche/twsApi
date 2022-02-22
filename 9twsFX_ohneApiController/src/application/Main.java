package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


/*
 * 
 * Hier will ich fxml und Api kombinieren!!!
 * 
 */

public class Main extends Application {
	
//	public static String host = "127.0.0.1";
//	public static int port = 7497;
//	public static int clientId = 0;
//			
	
	@Override
	public void start(Stage primaryStage) {

		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Sample.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        	launch(args);
	}
	
}