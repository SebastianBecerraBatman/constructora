package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowMainController {
	
	private Stage stageMain;
	private Parent root;
	
	  @FXML
	    private Button btnCreateProject, btnElements, btnApus, btnBudget;

	    @FXML
	    void openCreateProjectWindow(ActionEvent event) {
	    	this.initCreateProjectWindow();
	    }
	
	    @FXML
	    void initBudgetWindow(ActionEvent event) {
	    	this.initBudgetWindow();
	    }

	    @FXML
	    void initElementsApus(ActionEvent event) {
	    	this.initApusWindow();
	    }

	    @FXML
	    void initElementsWindow(ActionEvent event) {
	    	this.initElementsWindow();
	    }
	    
	public void initCreateProjectWindow() {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/CreateProjectWindow.fxml"));
			Scene scene = new Scene(root);
			this.stageMain = new Stage();
			this.stageMain.setScene(scene);
			this.stageMain.initModality(Modality.APPLICATION_MODAL);
			this.stageMain.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initElementsWindow() {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/ElementsWindow.fxml"));
			Scene scene = new Scene(root);
			this.stageMain = new Stage();
			this.stageMain.setScene(scene);
			this.stageMain.setTitle("Elementos");
			this.stageMain.initModality(Modality.APPLICATION_MODAL);
			this.stageMain.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void initApusWindow() {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/ApusWindow.fxml"));
			Scene scene = new Scene(root);
			this.stageMain = new Stage();
			this.stageMain.setScene(scene);
			this.stageMain.setTitle("APU´s");
			this.stageMain.initModality(Modality.APPLICATION_MODAL);
			this.stageMain.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initBudgetWindow() {
		try {
			this.root = FXMLLoader.load(getClass().getResource("/view/BudgetWindow.fxml"));
			Scene scene = new Scene(root);
			this.stageMain = new Stage();
			this.stageMain.setScene(scene);
			this.stageMain.setTitle("Presupuesto");
			this.stageMain.initModality(Modality.APPLICATION_MODAL);
			this.stageMain.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
