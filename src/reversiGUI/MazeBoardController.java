package reversiGUI;

import java.io.IOException;
import java.util.Stack;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MazeBoardController extends GridPane {
	private int[][] board;
	private static final int FREE = 0;
	private static final int WALL = 1;
	private Player player;
	
	public MazeBoardController(int[][] board) {
		this.board = board;
	 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MazeBoard.fxml"));
	 	fxmlLoader.setRoot(this);
	 	fxmlLoader.setController(this);
	 	player = new Player(this, 0, 0);
	 	try {
	 		 fxmlLoader.load();
	 		 this.setOnKeyPressed(event -> {
	 			 switch (event.getCode()) {
	 			 	case DOWN:
	 			 		player.moveDown();
	 			 		break;
	 			 	case UP:
	 			 		player.moveUp();
	 			 		break;
	 			 	case LEFT:
	 			 		player.moveLeft();
	 			 		break;
	 			 	case RIGHT:
	 			 		player.moveRight();
	 			 		break;
	 			 	default:
	 			 		break;
	 			 }
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
				 if (board[i][j] == FREE) {
					 stackPane.getChildren().add(new Rectangle(cellWidth, cellHeight, Color.CADETBLUE));
					 this.add(stackPane, j, i);
				 } else {
					 stackPane.getChildren().add(new Rectangle(cellWidth, cellHeight, Color.BLACK));
					 this.add(stackPane, j, i);
				 }
			 }
		 }
		 player.draw(cellWidth, cellHeight);
	}
}
