package constructoraMain;
	

import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		LoginController controller = new LoginController();
		controller.start(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
