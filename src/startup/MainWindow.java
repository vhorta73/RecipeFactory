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
//	private Session session;
	
	public void setSession(Session session) {
//		this.session = session;
		System.out.println(session);
	}
	
	public void start() {
		System.out.println("gah!2)");
        openIngredientWindow();
	}

	private void openIngredientWindow() {
//		System.out.println(ingredientMain);
//		Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
				IngredientMain ingredientMain = new IngredientMain();
				try {
					ingredientMain.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
//		});

//		thread.start();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		System.out.println("sdfgsdfsdf");
	}
}
