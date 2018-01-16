package reversiGUI;

import reversiLogic.GameManager;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MazeGameController implements Initializable{
	@FXML
	 private BorderPane root;
	 private GameManager gameManger;
	
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 int boardSize = 8; // read it from the settings file!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 this.gameManger = new GameManager(boardSize, new InOutGUI());
		 MazeBoardController mazeBoard = new MazeBoardController(this.gameManger.getGameBoard());
		 mazeBoard.setPrefWidth(410);
		 mazeBoard.setPrefHeight(410);
		 
		 Menu optionsMenu = new Menu("Options");
		 MenuItem settingsMenuItem = new MenuItem("Settings...");
		 settingsMenuItem.setOnAction(e -> {
			 	// !!!!!!!!!!!!!!!!!!!!!!! put the creation of the settings window here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 	System.out.println("pressed");
			 	/*AlertBoxFactory.display("Notice", "Invalid choice");*/
			 	
			 	Stage window = new Stage();
		     	//Block events to other windows (together with the window.showAndWait command)
		        window.initModality(Modality.APPLICATION_MODAL);
		        window.setTitle("Settings");
		        window.setMinWidth(600);
		        window.setMinHeight(300);

		        try{
		        	GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Settings.fxml"));
		        	Scene scene = new Scene(root,600,300);
		        	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        	//Display window and wait for it to be closed before returning
			        window.setScene(scene);
			        window.showAndWait();
			        
		        } catch(Exception exc) {
		        	exc.printStackTrace();
		        }
		 });
		 
		 optionsMenu.getItems().add(settingsMenuItem);
		 MenuBar menuBar = new MenuBar();
		 menuBar.getMenus().add(optionsMenu);
		 this.root.setTop(menuBar);
		 
		 //root.getChildren().add(0, mazeBoard);
		 root.setLeft(mazeBoard);
		 root.setOnMouseClicked(mazeBoard.getOnMouseClicked());
		 mazeBoard.draw();
	 
		 root.widthProperty().addListener((observable, oldValue, newValue) -> {
		 double boardNewWidth = newValue.doubleValue() - 200; // make the number a const!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 mazeBoard.setPrefWidth(boardNewWidth);
		 mazeBoard.draw();
		 });

		 root.heightProperty().addListener((observable, oldValue, newValue) -> {
	     double boardNewHeight = newValue.doubleValue() - 45;
		 mazeBoard.setPrefHeight(boardNewHeight);
		 mazeBoard.draw();
		 });
		 
	 }
}
