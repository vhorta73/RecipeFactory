package fxml.Main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import web.interfaces.Session;

/**
 * The MainController.
 * 
 * @author Vasco
 *
 */
public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menuBar"
    private MenuBar menuBar; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
    
    /** Main Controller methods outside of the GUI logic **/
    
    private Session session;
    
    public void setSession(Session session) {
    	this.session = session;
    }
}
