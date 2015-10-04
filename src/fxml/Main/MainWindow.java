package fxml.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import web.interfaces.Session;

/**
 * The Main window logic.
 * 
 * User is now logged in and have a session loaded.
 * 
 * @author Vasco
 *
 */
public class MainWindow extends Application {
	/**
	 * The User session
	 */
	private Session session;
	
	private FXMLLoader loader;

	/**
	 * The Constructor, requiring a valid loaded session to carry on.
	 * 
	 * @param session
	 */
	public MainWindow(Session session) {
		if ( session == null )                     throw new IllegalArgumentException("Session cannot be null.");
		if ( !session.isLoggedIn() )               throw new IllegalArgumentException("Not logged in.");
		if ( session.getUser() == null )           throw new IllegalArgumentException("User not loaded.");
		if ( session.getUserPrivileges() == null ) throw new IllegalArgumentException("User has no privileges.");
		this.session = session;
	}

	/**
	 * Start-up the Main GUI.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		MainController mainController = loader.getController();
		mainController.setSession(session);
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.show();
	}
}
