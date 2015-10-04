package startup;

import javafx.application.Application;
import javafx.stage.Stage;
import fxml.LogIn.LogInMain;

/**
 * The startup point for the Recipe Factory.
 * After the login is finished and successful,
 * passes the loaded session to the MainWindown
 * and exists this Main.
 * 
 * @author Vasco
 *
 */
public class StartApp extends Application {
	
	/**
	 * The Main static method where all begins...
	 * 
	 * @param args
	 */
	public static void main(String [] args ) {
		launch(args);
	}
	
	/**
	 * The Application start.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		LogInMain logInMain = new LogInMain();
		logInMain.start(primaryStage);
	}
}
