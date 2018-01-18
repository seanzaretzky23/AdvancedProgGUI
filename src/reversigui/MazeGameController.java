package reversigui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reversilogic.GameManager;

public class MazeGameController implements Initializable{
	@FXML
	 private BorderPane root;
	@FXML
	private Label whichPlayer;
	@FXML
	private Label firstPlayerScore;
	@FXML
	private Label secondPlayerScore;
	
	 private GameManager gameManager;
	 private MazeBoardController mazeBoard;
	
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 TurnManager turnManager = new TurnManager();
		 SettingsFileHandler fileHandler = new SettingsFileHandler();
		 int boardSize = fileHandler.getBoardSize();
		 this.mazeBoard = new MazeBoardController(boardSize, turnManager);
		 this.gameManager = new GameManager(boardSize, new InOutGUI(this, turnManager));
		 PlayerMoveListener playerMoveListener = new PlayerMoveListener(this.gameManager);
		 this.mazeBoard.setPlayerMoveListener(playerMoveListener);
		 mazeBoard.updateBoard(gameManager.getGameBoard());
		 mazeBoard.setPrefWidth(600);
		 mazeBoard.setPrefHeight(600);
		 
		 Menu optionsMenu = new Menu("Options");
		 MenuItem settingsMenuItem = new MenuItem("Settings...");
		 settingsMenuItem.setOnAction(e -> {
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
		 //initialize the labels (based on the states of the gameManager)!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 }
	 
	 public void setWhosTurn(boolean whosTurn) {
		 //if its the first player's turn
		 if (whosTurn)
			 this.whichPlayer.setText("First");
		 else
			 this.whichPlayer.setText("Second");
	 }
	 
	 public void updatePointsOfPlayers() {
		 this.firstPlayerScore.setText("" + this.gameManager.numberOfPointsOfFirstPlayer());
		 this.secondPlayerScore.setText("" + this.gameManager.numberOfPointsOfSecondPlayer());
	 }
	 
	 public MazeBoardController getMazeBoard() {
		 return this.mazeBoard;
	 }
	 
	 public void closeTheGame() {
		 Stage stage = (Stage)this.firstPlayerScore.getScene().getWindow();
		 stage.close();
	 }
}
