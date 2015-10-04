package fxml.Ingredient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IngredientMain extends Application {
	private FXMLLoader loader;
	private IngredientController ingredientController;

	public static void main(String []args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("Ingredient.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		ingredientController = loader.getController();
		primaryStage.show();
	}
	
	public IngredientController getController() {
		return ingredientController;
	}
}
