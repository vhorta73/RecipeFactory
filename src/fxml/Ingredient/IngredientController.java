package fxml.Ingredient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import web.interfaces.Session;

/**
 * Sample Skeleton for 'Ingredient.fxml' Controller Class
 */

public class IngredientController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="showOption"
    private ChoiceBox<String> showOption; // Value injected by FXMLLoader

    @FXML // fx:id="notesLabel"
    private Label notesLabel; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="deletedLabel"
    private Label deletedLabel; // Value injected by FXMLLoader

    @FXML // fx:id="okButton"
    private Button okButton; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionText"
    private TextArea descriptionText; // Value injected by FXMLLoader

    @FXML // fx:id="nameText"
    private TextField nameText; // Value injected by FXMLLoader

    @FXML // fx:id="notesText"
    private TextArea notesText; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionLabel"
    private Label descriptionLabel; // Value injected by FXMLLoader

    @FXML // fx:id="showLabel"
    private Label showLabel; // Value injected by FXMLLoader

    @FXML // fx:id="nameLabel"
    private Label nameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="deletedOption"
    private ChoiceBox<String> deletedOption; // Value injected by FXMLLoader

    private Session session;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert showOption != null : "fx:id=\"showOption\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert notesLabel != null : "fx:id=\"notesLabel\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert deletedLabel != null : "fx:id=\"deletedLabel\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert notesText != null : "fx:id=\"notesText\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert showLabel != null : "fx:id=\"showLabel\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'Ingredient.fxml'.";
        assert deletedOption != null : "fx:id=\"deletedOption\" was not injected: check your FXML file 'Ingredient.fxml'.";

        // Build all options and text
        showOption.getItems().addAll("Y","N");
        showOption.setValue("Y");
        deletedOption.getItems().addAll("Y","N");
        deletedOption.setValue("N");
    }

    /**
     * FXML OK button pressed.
     * 
     * @param event
     */
    @FXML
    void pressedOk(ActionEvent event) {
//    	DBIngredient db = session.getDB().getConnection(DatabaseNames.getCoreDatabase());
    	System.out.println("selected:" +showOption.getValue()+ " and session is : ");//+session.toString());
//    	if ( !session.getUserPrivileges().can(EnumTool.INGREDIENT_MANAGEMENT) ) System.out.print("I am sorry.. but you are not allowed to do this");
    }
    
    public void setThis(Session session) {
    	this.session = session;
    }
    
    public String getIngredientName() {
    	return "";
//    	return nameText.getText();
    }
}