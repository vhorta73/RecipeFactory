package fxml.LogIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The LogIn screen.
 * 
 * @author Vasco
 *
 */
public class LogInMain extends Application {
	/**
	 * The FXML Loader.
	 */
	private FXMLLoader loader;
	
	/**
	 * The start point.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		primaryStage.show();
	}
}
