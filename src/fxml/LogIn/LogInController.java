package fxml.LogIn;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import secure.LoginCredentials;
import startup.MainWindow;


public class LogInController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="password"
    private TextField password; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private Button login; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TextField username; // Value injected by FXMLLoader

    @FXML
    void logIn(ActionEvent event) {
    	if ( isValid(username.getText()) && isValid(password.getText()) ) {
        	LoginCredentials credentials = new LoginCredentials();
        	try {
            	credentials.logIn(username.getText(), password.getText());
            	if ( credentials.getSession().isLoggedIn() ) {
            		// Initialise a new Main Windows with loaded session and exit.
            		MainWindow main = new MainWindow();
                	main.setSession(credentials.getSession());
                	main.start();
                	Stage stage = (Stage) login.getScene().getWindow();
                	stage.close();
            	}
            	else {
            		// Alert wrong username or password and keep trying
            		JOptionPane.showMessageDialog(null, "Username or password wrong.");
            		this.password.setText("");
            		this.username.setText("");
            	}
        	} catch ( Exception e ) {
        		this.password.setText("");
        		this.username.setText("");
        		JOptionPane.showMessageDialog(null, "Exception: "+e.getMessage());
        	}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Username or password wrong.");
    		this.password.setText("");
    		this.username.setText("");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'LogIn.fxml'.";
    }
    
    private boolean isValid(String str) {
    	if ( str == null ) return false;
    	if ( str.length() == 0 ) return false;
    	if ( str.contains(" <>\\|=+") ) return false;
    	return true;
    }
}
