package startup;

import javafx.application.Application;
import javafx.stage.Stage;
import web.interfaces.Session;
import fxml.Ingredient.IngredientMain;

/**
 * The Main window logic.
 * 
 * @author Vasco
 *
 */
public class MainWindow extends Application {
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void start() {
        openIngredientWindow();
	}

	private void openIngredientWindow() {
		IngredientMain ingredientMain = new IngredientMain(session);
		try {
			ingredientMain.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		System.out.println("sdfgsdfsdf");
	}
}
