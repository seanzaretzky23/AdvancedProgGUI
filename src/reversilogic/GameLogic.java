package reversilogic;
import java.util.ArrayList;

import reversilogic.Board.SquareColor;

public abstract class GameLogic {


    protected Board gameBoard;
    public GameLogic(){ };
    /**************************************************************
     * function name: GameLogic class builder
     * Input: Board *board
     * @return GameLogic object instance
     * Function operation: creates a GameLogic object instance.
     **************************************************************/
    public GameLogic(Board board){
        gameBoard=board;
    };

    /**************************************************************
     * function name: possibleMovesForColor
     * Input: SquareColor color
     * @return vector<BoardCell> (list of possible moves)
     * Function operation: returns the list of possible moves for the player
     *       of the received color to preform.
     **************************************************************/
    public abstract ArrayList<BoardCell> possibleMovesForColor(Board.SquareColor color);

    /**************************************************************
     * function name: makeMove
     * Input: BoardCell &boardCell, SquareColor color
     * @return bool representing who's turn it is to go next,
     *       !!! true for the Black, false for the White !!!
     * Function operation: makes the requested move on the board and returns the
     *       player to play the next turn (!assums the move being made is one of the valid options
     *       returned by possibleMovesForColor)
     **************************************************************/
    public abstract boolean makeMove(BoardCell boardCell, Board.SquareColor color);

    /**************************************************************
     * function name: whoHasMorePoints
     * Input: no input
     * @return int representing which player has more points,
     *       !!! 1 for the Black (player1), 2 for the White (player2)
     *       and 3 for a tie!!!
     * Function operation: returns which player has more points
     **************************************************************/
    public int whoHasMorePoints(){
        int whiteCounter = 0, blackCounter = 0;
        for (int i = 0; i < this.gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < this.gameBoard.getBoardWidth(); j++) {
                switch (this.gameBoard.getColorOfBoardCell(i, j)) {
                    case White:
                        whiteCounter++;
                        break;
                    case Black:
                        blackCounter++;
                        break;
                    default:
                    	break;
                }
            }
        }
        if (whiteCounter > blackCounter) {
            return 2;
        } else {
            if (blackCounter > whiteCounter) {
                return 1;
            } else {
                return 3;
            }
        }
    }
    
    /**************************************************************
     * function name: numberOfPoints
     * Input: SquareColor colorToCount
     * @return the number of points of the received color on the board
     * Function operation: returns the number of points of the
     * 	received color on the board, throws exception if received Blank
     **************************************************************/
    public int numberOfPoints(SquareColor colorToCount) throws RuntimeException {
    	if (colorToCount == SquareColor.Blank)
    		throw new IllegalArgumentException("received color can't be Blank");
    	int counter = 0;
    	for (int i = 0; i < this.gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < this.gameBoard.getBoardWidth(); j++) {
                if (this.gameBoard.getColorOfBoardCell(i, j) == colorToCount) {
                	counter++;
                }
            }
        }
    	return counter;
    }

    /**************************************************************
     * function name: turnAllPawnsBetweenTheCells
     * Input: BoardCell &boardCell1, BoardCell &boardCell2
     * @return void
     * Function operation: assumes the two received boardCells are valid
     *       and represent two boardCells of same color with line of
     *       boardCells of the opposite color between them.
     **************************************************************/
    protected void turnAllPawnsBetweenTheCells(BoardCell boardCell1,
                                               BoardCell boardCell2){
        int xCor1, yCor1, xCor2, yCor2;
        xCor1 = boardCell1.getXCor();
        yCor1 = boardCell1.getYCor();
        xCor2 = boardCell2.getXCor();
        yCor2 = boardCell2.getYCor();

        //finding how the boardCells are placed one relatively to the other and calling to helper methods
        if (xCor1 == xCor2) {
            if (yCor1 > yCor2) {
                turnAllPawnsBetweenSameColCells(boardCell1, boardCell2);
            } else {
                turnAllPawnsBetweenSameColCells(boardCell2, boardCell1);
            }
        } else {
            if (yCor1 == yCor2) {
                if (xCor1 < xCor2) {
                    turnAllPawnsBetweenSameRowCells(boardCell1, boardCell2);
                } else {
                    turnAllPawnsBetweenSameRowCells(boardCell2, boardCell1);
                }
            } else {
                turnAllPawnsBetweenSameDiagCells(boardCell1, boardCell2);
            }
        }
    };

    /**************************************************************
     * function name: turnAllPawnsBetweenSameRowCells
     * Input: BoardCell &boardCell1, BoardCell &boardCell2
     * @return void
     * Function operation: assumes the two received boardCells are valid
     *       and represent two boardCells of same color with line of
     *       boardCells of the opposite color between them.
     *       also assums boardCell1 is to the left of boardCell2
     **************************************************************/
    protected void turnAllPawnsBetweenSameRowCells(BoardCell boardCell1,
                                                   BoardCell boardCell2){
        Board.SquareColor color = this.gameBoard.getColorOfBoardCell(boardCell1);
        //starting co'ordinates are of boardCell1
        int xCor, yCor;
        xCor = boardCell1.getXCor();
        yCor = boardCell1.getYCor();

        //finish x co'ordinate
        int xCorF = boardCell2.getXCor();

        for (int i = 1; xCor + i < xCorF; i++) {
            BoardCell boardCellTmp=new BoardCell(xCor + i, yCor);
            this.gameBoard.setColorOfRequestedCell(boardCellTmp, color);
        }

    };

    /**************************************************************
     * function name: turnAllPawnsBetweenSameColCells
     * Input: BoardCell &boardCell1, BoardCell &boardCell2
     * @return void
     * Function operation: assumes the two received boardCells are valid
     *       and represent two boardCells of same color with line of
     *       boardCells of the opposite color between them.
     *       also assums boardCell1 is higher than boardCell2
     **************************************************************/
    protected void turnAllPawnsBetweenSameColCells(BoardCell boardCell1,
                                                   BoardCell boardCell2){
        Board.SquareColor color = this.gameBoard.getColorOfBoardCell(boardCell1);

        //starting co'ordinates are of boardCell1
        int xCor, yCor;
        xCor = boardCell1.getXCor();
        yCor = boardCell1.getYCor();

        //finish y co'ordinate
        int yCorF = boardCell2.getYCor();

        for (int i = 1; yCor - i > yCorF; i++) {
            BoardCell boardCellTmp=new BoardCell(xCor, yCor - i);
            this.gameBoard.setColorOfRequestedCell(boardCellTmp, color);
        }

    };

    /**************************************************************
     * function name: turnAllPawnsBetweenSameDiagCells
     * Input: BoardCell &boardCell1, BoardCell &boardCell2
     * @return void
     * Function operation: assumes the two received boardCells are valid
     *       and represent two boardCells of same color with line of
     *       boardCells of the opposite color between them.
     **************************************************************/
    protected void turnAllPawnsBetweenSameDiagCells(BoardCell boardCell1,
                                                    BoardCell boardCell2){
        Board.SquareColor color = this.gameBoard.getColorOfBoardCell(boardCell1);

        int xCor1, yCor1, xCor2, yCor2;
        xCor1 = boardCell1.getXCor();
        yCor1 = boardCell1.getYCor();
        xCor2 = boardCell2.getXCor();
        yCor2 = boardCell2.getYCor();

        if (xCor1 < xCor2) {
            if (yCor1 < yCor2) {
                for (int i = 1; xCor1 + i < xCor2 ; i++) {
                    BoardCell boardCellTmp=new BoardCell(xCor1 + i, yCor1 + i);
                    this.gameBoard.setColorOfRequestedCell(boardCellTmp, color);
                }
            } else {
                //yCor1 > yCor2
                for (int i = 1; xCor1 + i < xCor2 ; i++) {
                    BoardCell boardCellTmp=new BoardCell(xCor1 + i, yCor1 - i);
                    this.gameBoard.setColorOfRequestedCell(boardCellTmp, color);
                }
            }
        } else {
            turnAllPawnsBetweenSameDiagCells(boardCell2, boardCell1);
        }

    };


}
