package reversiGUI;

import reversiLogic.Board;

import java.io.IOException;
import java.util.Stack;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;

public class MazeBoardController extends GridPane {
	private Board.SquareColor[][] board;
	private Player player;
	
	public MazeBoardController(Board.SquareColor[][] board) {
		this.board = board;
	 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MazeBoard.fxml"));
	 	fxmlLoader.setRoot(this);
	 	fxmlLoader.setController(this);
	 	player = new Player(this, 0, 0);
	 	try {
	 		 fxmlLoader.load();
	 		 this.setOnMouseClicked(event -> {
	 			 //
	 			 event.consume();
	 		 });
	 		 } catch (IOException exception) {
	 			 throw new RuntimeException(exception);
	 	 	}
	 	
	 }
	public void draw() {
		 this.getChildren().clear();

		 int height = (int)this.getPrefHeight();
		 int width = (int)this.getPrefWidth();

		 int cellHeight = height / board.length;
		 int cellWidth = width / board[0].length;

		 for (int i = 0; i < board.length; i++) {
			 for (int j = 0; j < board[i].length; j++) {
				 StackPane stackPane = new StackPane();
				 stackPane.setBorder(new Border(new BorderStroke(null, null, null, null, null, null, null, null, null, null, getInsets())));
				 stackPane.getChildren().add(new Rectangle(cellWidth, cellHeight, Color.CADETBLUE));
				 switch (board[i][j]) {
				 	case Black:
				 		stackPane.getChildren().add(new Circle(cellWidth/2, cellHeight/2, cellWidth/4, Color.BLACK));
				 		//Ellipse ellipse = new Ellipse(cellWidth/2, cellHeight/2, cellWidth/4, cellHeight/4);
				 		//ellipse.setFill(Color.GRAY);
				 		//stackPane.getChildren().add(ellipse);
				 		//stackPane.getChildren().add(new Sphere(cellWidth/4));
				 		break;
				 	case White:
				 		stackPane.getChildren().add(new Circle(cellWidth/2, cellHeight/2, cellWidth/4, Color.WHITE));
				 		break;
					default:
						break;
				 }
				 
				 this.add(stackPane, j, i);
			 }
		 }
		 
		 player.draw(cellWidth, cellHeight);
	}
}
