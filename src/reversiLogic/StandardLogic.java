package reversiLogic;
import java.util.ArrayList;

import static java.lang.Float.max;

public class StandardLogic extends GameLogic {

    /**************************************************************
     * function name: StandardLogic class builder
     * Input: Board *board
     * Function operation: creates a StandardLogic object instance,
     *       using GameLogic builder
     **************************************************************/
    public StandardLogic(Board board){
        gameBoard=board;
    };

    /**************************************************************
     * function name: possibleMovesForColor
     * Input: SquareColor color
     * @return vector<BoardCell>
     * Function operation: returns a vector of possible moves for
     *       the player of received color represented by BoardCells
     *       !!! if the received color is Blank throws exception
     *       (only White and Black are legal) !!!
     **************************************************************/
    public ArrayList<BoardCell> possibleMovesForColor(SquareColor color){
        ArrayList<BoardCell> possibleMoves=new ArrayList<BoardCell>();

        if (color == SquareColor.Blank) {
            throw new IllegalArgumentException("only Black and White are valid inputs");
        }

        for (int i = 0; i < this.gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < this.gameBoard.getBoardWidth(); j++) {
                BoardCell tmp=new BoardCell(i, j);
                if ((this.gameBoard.getColorOfBoardCell(i, j) == SquareColor.Blank) &&
                (!boardCellsCreatingScoreLines(tmp, color).isEmpty())) {
                    possibleMoves.add(tmp);
                }
            }
        }

        return possibleMoves;
    };

    /**************************************************************
     * function name: makeMove
     * Input: BoardCell &boardCell, SquareColor color
     * @return bool representing who's turn it is to go next,
     *       !!! true for the Black, false for the White !!!
     * Function operation: makes the requested move on the board and returns the
     *       player to play the next turn (!if the move being made isn't one of the valid options
     *       returned by possibleMovesForColor than throws exception)
     **************************************************************/
    public boolean makeMove(BoardCell boardCell, SquareColor color){
        ArrayList<BoardCell> options = possibleMovesForColor(color);

        //variable containing if the received boardCell is one of the boardCells in options vector
        boolean ok = false;
        for (int i = 0; (i < options.size()) && !ok; i++) {
            if (boardCell.equals(options.get(i))) {
                ok = true;
            }
        }

        if (ok) {
            this.gameBoard.setColorOfRequestedCell(boardCell, color);
            //flipping boardCells of the opposite color that need to be flipped after current move
            ArrayList<BoardCell>  cellsUntilWhichToFlip = this.boardCellsCreatingScoreLines(boardCell, color);
            for (int i = 0; i < cellsUntilWhichToFlip.size(); i++) {
                this.turnAllPawnsBetweenTheCells(boardCell, cellsUntilWhichToFlip.get(i));
            }

            if (color == SquareColor.White) {
                return true; //Black
            } else {
                return false; //White
            }
        } else {
            throw new IllegalArgumentException("this boardCell isn't one of the options for a valid move");
        }};


    /****************************************** ********************
     * function name: boardCellsCreatingScoreLines
     * Input: BoardCell &boardCell, SquareColor color
     * @return vector<BoardCell>
     * Function operation: returns a vector of boardCells that together
     * with the received boardCell (of the received color) form line
     * that give score in the game
     **************************************************************/
    protected ArrayList<BoardCell> boardCellsCreatingScoreLines(BoardCell boardCell, SquareColor color){

        ArrayList<BoardCell> boardCells=new ArrayList<BoardCell>();

        SquareColor oppositeColor;
        if (color == SquareColor.White) {
            oppositeColor = SquareColor.Black;
        } else {
            oppositeColor = SquareColor.White;
        }

        //boolean array of size 8 representing if we want to continue the search in each one
        //of the 8 directions from the received boardCell
        // (starting from 0 for bottom right corner and moving counter clockwise)
        ArrayList<Boolean> contSearch=new ArrayList<Boolean>();

        //board dimensions
        int boardLength = this.gameBoard.getBoardLength();
        int boardWidth = this.gameBoard.getBoardWidth();

        //the co'ordinates of the boardCell
        int xCor, yCor;
        xCor = boardCell.getXCor();
        yCor = boardCell.getYCor();

        initializeBoolVectorForBCCSLmethod(contSearch, boardCell, color);
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int mainLoopSize = (int) max (max (xCor + 1, yCor + 1), max (boardLength - xCor, boardWidth - yCor));

        //the main loop of this method
        for (int i = 2; i < mainLoopSize; i++) {
            //checking each of the 8 directions around the boardCell
            this.helperMethodForBCCSL(contSearch, 0, boardCells, 1, 1, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 1, boardCells, 1, 0, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 2, boardCells, 1, -1, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 3, boardCells, 0, -1, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 4, boardCells, -1, -1, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 5, boardCells, -1, 0, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 6, boardCells, -1, 1, xCor, yCor, i, color);
            this.helperMethodForBCCSL(contSearch, 7, boardCells, 0, 1, xCor, yCor, i, color);

        }
        return boardCells;
    };



    /**************************************************************
     * function name: initializeBoolVectorForBCCSLmethod
     * Input: vector<bool> &vec, BoardCell &boardCell, SquareColor color
     * @return void
     * Function operation: initializes the vector used in the method
     *       boardCellsCreatingScoreLines
     **************************************************************/
    private void initializeBoolVectorForBCCSLmethod(ArrayList<Boolean> vec, BoardCell boardCell, SquareColor color){
        SquareColor oppositeColor;
        if (color == SquareColor.White) {
            oppositeColor = SquareColor.Black;
        } else {
            oppositeColor = SquareColor.White;
        }

        //the co'ordinates of the boardCell
        int xCor, yCor;
        xCor = boardCell.getXCor();
        yCor = boardCell.getYCor();


        for (int i = 0; i < 8; i++) {
            vec.add(false);
        }

        vec.set(0, (this.gameBoard.validBoardCell(xCor + 1, yCor + 1) &&
                this.gameBoard.getColorOfBoardCell(xCor + 1, yCor + 1) == oppositeColor));

        vec.set(1,(this.gameBoard.validBoardCell(xCor + 1, yCor) &&
                this.gameBoard.getColorOfBoardCell(xCor + 1, yCor) == oppositeColor));

        vec.set(2, (this.gameBoard.validBoardCell(xCor + 1, yCor - 1) &&
                this.gameBoard.getColorOfBoardCell(xCor + 1, yCor - 1) == oppositeColor));

        vec.set(3, (this.gameBoard.validBoardCell(xCor, yCor - 1) &&
                this.gameBoard.getColorOfBoardCell(xCor, yCor - 1) == oppositeColor));

        vec.set(4, (this.gameBoard.validBoardCell(xCor - 1, yCor - 1) &&
                this.gameBoard.getColorOfBoardCell(xCor - 1, yCor - 1) == oppositeColor));

        vec.set(5, (this.gameBoard.validBoardCell(xCor - 1, yCor) &&
                this.gameBoard.getColorOfBoardCell(xCor - 1, yCor) == oppositeColor));

        vec.set(6, (this.gameBoard.validBoardCell(xCor - 1, yCor + 1) &&
                this.gameBoard.getColorOfBoardCell(xCor - 1, yCor + 1) == oppositeColor));

        vec.set(7, (this.gameBoard.validBoardCell(xCor, yCor + 1) &&
                this.gameBoard.getColorOfBoardCell(xCor, yCor + 1) == oppositeColor));
    };

    /**************************************************************
     * function name: helperMethodForBCCSL
     * Input: vector<bool> &contSearch, int index, vector<BoardCell> &boardCells,
     *       int xCoefficient, int yCoefficient, int &xCor, int &yCor, int &i,
     *       SquareColor color
     * @return void
     * Function operation: helper method for the method:
     *       boardCellsCreatingScoreLines, that enables avoid copying
     *       code segment that is repeating in the BCCSL method
     **************************************************************/
    private void helperMethodForBCCSL(ArrayList<Boolean> contSearch, int index,ArrayList<BoardCell> boardCells,
                              int xCoefficient, int yCoefficient, int xCor, int yCor, int i,
                              SquareColor color) {
        if (contSearch.get(index) && this.gameBoard.validBoardCell(xCor + (xCoefficient * i), yCor + (yCoefficient * i))) {
            if (this.gameBoard.getColorOfBoardCell(xCor + (xCoefficient * i), yCor + (yCoefficient * i)) == color) {
                boardCells.add(new BoardCell(xCor + (xCoefficient * i), yCor + (yCoefficient * i)));
                contSearch.set(index, false);
            } else {
                if (this.gameBoard.getColorOfBoardCell(xCor + (xCoefficient * i), yCor + (yCoefficient * i))
                        == SquareColor.Blank) {
                    contSearch.set(index, false);
                }
            }
        }
    };
}
