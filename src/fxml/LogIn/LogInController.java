package fxml.LogIn;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import secure.LoginCredentials;
import fxml.Main.MainWindow;

/**
 * The logInController.
 * 
 * @author Vasco
 *
 */
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

        // Check for valid username and passwords
        if ( isValid(username.getText()) && isValid(password.getText()) ) {

            // Get a new LoginCredentials to validate given username and password.
            LoginCredentials credentials = new LoginCredentials();

            // There will be exceptions that we must capture if thrown to let user know.
            try {
                credentials.logIn(username.getText(), password.getText());

                if ( credentials.getSession().isLoggedIn() ) {
                    // Successful logged in. Initialise a new Main Windows with loaded session.
                    MainWindow main = new MainWindow(credentials.getSession());
                    main.start((Stage)login.getScene().getWindow());
                }
                else {
                    // Wrong username or password. Clean fields, alert user to retry.
                    JOptionPane.showMessageDialog(null, "Username or password wrong.");
                    this.password.setText("");
                    this.username.setText("");
                }
            } catch ( Exception e ) {
                // Exception given by the process. Show it to the user.
                this.password.setText("");
                this.username.setText("");
                JOptionPane.showMessageDialog(null, "Exception: "+e.getMessage());
            }
        }
        else {
            // Invalid characters entered somewhere.
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
    
    /**
     * Checking the username or password strings.
     * 
     * @param str
     * @return true if valid
     */
    private boolean isValid(String str) {
        // Null is not a valid string
        if ( str == null ) return false;
        
        // Must have a length bigger than zero.
        if ( str.length() == 0 ) return false;
        
        // Not allowing spaces, <> for scripting and escape characters mostly 
        if ( str.contains(" <>\\") ) return false;
        
        return true;
    }
}
