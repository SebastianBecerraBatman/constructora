package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateProjectController {
	  @FXML
	    private Button btnCancel;

	    @FXML
	    void closeWindow(ActionEvent event) {
	    	Stage stage = (Stage) this.btnCancel.getScene().getWindow();
	        stage.close();
	    }
}
