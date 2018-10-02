package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateUserController {
	 @FXML
	    private Button btnClose;

	    @FXML
	    void closeWindow(ActionEvent event) {
	    	Stage stage = (Stage) this.btnClose.getScene().getWindow();
	        stage.close();
	    }
}
