package reversiGUI;

import reversiLogic.Board;
import reversiLogic.Board.SquareColor;

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
	private TurnManager turnManager;
	private int cellHeight;
	private int cellWidth;
	private PlayerMoveListener playerMoveListener;
	
	public MazeBoardController(int boardSize, TurnManager turnManager) {
		//check the board size validity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.initializeBoard(boardSize);
		this.turnManager = turnManager;
		this.playerMoveListener = null;
	 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MazeBoard.fxml"));
	 	fxmlLoader.setRoot(this);
	 	fxmlLoader.setController(this);
	 	player = new Player(this, 0, 0);
	 	try {
	 		 fxmlLoader.load();
	 		 this.setOnMouseClicked(event -> {
	 			 int horizontalIndex, verticalIndex;
	 			 horizontalIndex = (int)((event.getX())/this.cellWidth);
	 			 verticalIndex = (int)((event.getY())/this.cellHeight);
	 			 this.turnManager.updateTurn(horizontalIndex, verticalIndex);
	 			 System.out.println(horizontalIndex + ", " + verticalIndex);
	 			 this.playerMoveListener.playNextTurn();
	 			 event.consume();
	 		 });
	 		 } catch (IOException exception) {
	 			 throw new RuntimeException(exception);
	 	 	}
	 	
	 }
	
	private void initializeBoard(int boardSize) {
		this.board = new Board.SquareColor[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				this.board[i][j] = Board.SquareColor.Blank;
			}
		}
	}
	
	public void updateBoard(Board.SquareColor[][] newBoard) {
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard[0].length; j++) {
				this.board[i][j] = newBoard[i][j];
			}
		}
	}
	
	public void setPlayerMoveListener(PlayerMoveListener playerMoveListener) {
		this.playerMoveListener = playerMoveListener;
	}
	
	public void draw() {
		 this.getChildren().clear();

		 int height = (int)this.getPrefHeight();
		 int width = (int)this.getPrefWidth();

		 cellHeight = height / board.length;
		 cellWidth = width / board[0].length;
		 
		 this.printBoard(this.board);
		 
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
	
	//erase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private void printBoard(SquareColor[][] board) {
		SquareColor[][] existingBoard = board;
    	System.out.print(" |");
        //cout << " " << '|';
        for (int j = 1; j <= existingBoard.length; j++) {
            //cout << ' ' << j << ' ' << '|';
            System.out.print(" " + j + " |");

        }
        //cout << "" << endl;
        System.out.println("");
        for (int j = 0; j < (4 * existingBoard.length) + 2; j++) {
            //cout << '.';
            System.out.print(".");
        }
        System.out.println("");
        //cout << "" << endl;
        for (int i = 0; i < existingBoard[0].length; i++) {
            //cout << i + 1 << '|';
            System.out.print(i+1 + "|");
            for (int j = 0; j < existingBoard.length; j++) {

                char charToPrint = 0;
                switch (existingBoard[i][j]) {
                    case Black:
                        charToPrint = 'X';
                        break;
                    case White:
                        charToPrint = 'O';
                        break;
                    default:
                        charToPrint = ' ';
                        break;
                }
                System.out.print(" ");
                System.out.print(charToPrint);
                System.out.print(" |");
                //cout << ' ' << charToPrint << " |";
            }
            System.out.println("");
            //cout << "" << endl;
            for (int j = 0; j < (4 * existingBoard.length) + 2; j++) {
                System.out.print(".");
                //cout << '.';
            }
            System.out.println("");
            //cout << "" << endl;
        }
	}
}
