package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ElementsController {
	

    @FXML
    private Button btnBack;

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) this.btnBack.getScene().getWindow();
        stage.close();
    }

}
