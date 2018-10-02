package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable{
	
	private Stage initStage, mainWindowStage, createUserWindowStage;
	private Parent root;

    @FXML
    private Button btnLogn;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;
    
    @FXML
    void validateUser(ActionEvent event) {
    	this.initMainWindow();
    }

    @FXML
    void createUser(ActionEvent event) {
    	this.initCreateUserWindow();
    }
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
	
	public void start(Stage primaryStage) {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			this.initStage = primaryStage;
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initMainWindow() {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/WindowMain.fxml"));
			Scene scene = new Scene(root);
			this.mainWindowStage = new Stage();
			this.mainWindowStage.setScene(scene);
			this.mainWindowStage.initOwner(this.initStage);
			this.mainWindowStage.initModality(Modality.APPLICATION_MODAL);
			this.mainWindowStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initCreateUserWindow() {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/CreateUserWindow.fxml"));
			Scene scene = new Scene(root);
			this.createUserWindowStage = new Stage();
			this.createUserWindowStage.setScene(scene);
			this.createUserWindowStage.initOwner(this.initStage);
			this.createUserWindowStage.initModality(Modality.APPLICATION_MODAL);
			this.createUserWindowStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Stage getInitStage() {
		return initStage;
	}
	
	
}
