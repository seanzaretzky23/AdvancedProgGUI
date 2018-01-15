package mazeapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MazeGameController implements Initializable{
	@FXML
	 private BorderPane root;
	 private int[][] maze = {{0,1,0,1,0,0,0,1,0,0,0}, {0,1,0,1,1,1,0,1,0,1,0}, {0,0,0,1,0,0,0,1,0,1,0}, {1,1,0,1,1,1,0,1,0,1,1},
			 {0,1,0,0,0,0,0,0,0,0,0}, {1,1,0,1,0,1,1,1,1,1,1}, {0,0,0,1,0,0,0,0,0,1,0}, {0,1,0,1,1,1,1,1,0,1,0},
			 {0,1,0,0,0,0,0,1,0,1,0}, {1,1,0,1,1,1,0,1,1,1,0}, {0,0,0,0,0,1,0,0,0,0,0}};
	
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 MazeBoardController mazeBoard = new MazeBoardController(maze);
		 mazeBoard.setPrefWidth(410);
		 mazeBoard.setPrefHeight(410);
		 
		 Menu optionsMenu = new Menu("Options");
		 MenuItem settingsMenuItem = new MenuItem("Settings...");
		 settingsMenuItem.setOnAction(e -> {
			 // !!!!!!!!!!!!!!!!!!!!!!! put the creation of the settings window here !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			 System.out.println("pressed");
			 AlertBoxFactory.display("Notice", "Invalid choice");
		 });
		 optionsMenu.getItems().add(settingsMenuItem);
		 MenuBar menuBar = new MenuBar();
		 menuBar.getMenus().add(optionsMenu);
		 this.root.setTop(menuBar);
		 
		 //root.getChildren().add(0, mazeBoard);
		 root.setLeft(mazeBoard);
		 root.setOnKeyPressed(mazeBoard.getOnKeyPressed());
		 mazeBoard.draw();
	 
		 root.widthProperty().addListener((observable, oldValue, newValue) -> {
		 double boardNewWidth = newValue.doubleValue() - 180; // make the number a const!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 mazeBoard.setPrefWidth(boardNewWidth);
		 mazeBoard.draw();
		 });

		 root.heightProperty().addListener((observable, oldValue, newValue) -> {
		 mazeBoard.setPrefHeight(newValue.doubleValue());
		 mazeBoard.draw();
		 });
		 
	 }
}
