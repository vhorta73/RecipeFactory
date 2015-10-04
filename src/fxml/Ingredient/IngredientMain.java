package fxml.Ingredient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import web.interfaces.Session;

public class IngredientMain extends Application {
	private FXMLLoader loader;
	private IngredientController ingredientController;
	private Session session;

	public IngredientMain(Session session) {
		this.session = session;
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("Ingredient.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		ingredientController = loader.getController();
		ingredientController.setSession(session);
		primaryStage.setX(0);
		primaryStage.setY(0);
//		primaryStage.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 250);
//		primaryStage.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - 250);
		primaryStage.show();
	}
	
	public IngredientController getController() {
		return ingredientController;
	}
}
