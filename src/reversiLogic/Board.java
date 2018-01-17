package reversiLogic;
import java.util.ArrayList;
//BoardCell;

//public enum SquareColor {Black, White, Blank};

public abstract class Board {
	public enum SquareColor {Black, White, Blank};
	private static final int DefaultBoardSize = 8;
    protected int boardLength;
    protected int boardWidth;
    protected ArrayList<ArrayList<SquareColor>> gameBoard;
    protected InOutTerminal command=new InOutTerminal();

    /**************************************************************
     * function name: initializeBoard
     * Input: no input
     * @return void
     * Function operation: initializes the gameBoard to its starting state
     **************************************************************/
    public void initializeBoard(){
        for (int i = 0; i < this.boardLength; i++) {
            ArrayList<SquareColor> row = new ArrayList<>();
            for (int j = 0; j < this.boardWidth; j++) {
                row.add(SquareColor.Blank);
            }
            this.gameBoard.add(row);
        }

        //creating the middle square
        int topLeftRow, topLeftColumn;
        topLeftRow = (boardLength / 2) - 1;
        topLeftColumn = (boardWidth / 2) - 1;
        gameBoard.get(topLeftRow).set(topLeftColumn,SquareColor.White);
        gameBoard.get(topLeftRow+1).set(topLeftColumn,SquareColor.Black);
        gameBoard.get(topLeftRow).set(topLeftColumn+1,SquareColor.Black);
        gameBoard.get(topLeftRow+1).set(topLeftColumn+1,SquareColor.White);

    }
    /**************************************************************
     * function name: Board class builder
     * Input: none
     * @return Board object
     * Function operation: creates a Board object with the specified
     *       length and width, no default.
     **************************************************************/

    public Board() {
        boardLength = DefaultBoardSize;
        boardWidth = DefaultBoardSize;
        gameBoard = new ArrayList<ArrayList<SquareColor>>();
        //checking for valid board, if valid initialize
        if (boardLength >= 2 && (boardLength % 2) == 0 && boardWidth >= 2 && (boardWidth % 2) == 0) {
            this.initializeBoard();
        } else {
            throw new IllegalArgumentException("board length and width should be greater than 2 and ");
        }

    };

    /**************************************************************
     * function name: Board class builder
     * Input: int length, int width
     * @return Board object
     * Function operation: creates a Board object with the specified
     *       length and width, no default.
     **************************************************************/

    public Board(int length, int width){


        boardLength=length;
        boardWidth=width;
        gameBoard = new ArrayList<ArrayList<SquareColor>>();
        //checking for valid board, if valid initialize
        if (boardLength >= 2 && (boardLength % 2) == 0 && boardWidth >= 2 && (boardWidth % 2) == 0) {
            this.initializeBoard();
        } else {
            throw new IllegalArgumentException("board length and width should be greater than 2 and ");
        }

    };

    /**************************************************************
     * function name: printBoard
     * Input: no input
     * @return void
     * Function operation: prints the board
     **************************************************************/
    void printBoard() {

    }

    /**************************************************************
     * function name: validBoardCell
     * Input: int xCor, int yCor (represent a boardCell instance)
     * @return bool
     * Function operation: returns true if boardCell represents a cell
     *       a cell in the bounds of the board and false otherwise
     **************************************************************/
    public boolean validBoardCell(int xCor, int yCor){
        return ((xCor >= 0) && (xCor < this.boardLength) &&
                (yCor >= 0) && (yCor < this.boardWidth));

    };
    /**************************************************************
     * function name: validBoardCell
     * Input: BoardCell& boardCell
     * @return bool
     * Function operation: returns true if boardCell represents a cell
     *       a cell in the bounds of the board and false otherwise
     **************************************************************/
    public boolean validBoardCell(BoardCell boardCell){
        int xCor = boardCell.getXCor();
        int yCor = boardCell.getYCor();
        return validBoardCell(xCor, yCor);
    };

    /**************************************************************
     * function name: getColorOfBoardCell
     * Input: BoardCell& boardCell
     * @return SquareColor
     * Function operation: returns the color at the requested cell
     *       of the board.
     *       !!! throws exception if the boardCell doesn't represent
     *       a valid cell in the board !!!
     **************************************************************/

    public SquareColor getColorOfBoardCell(BoardCell boardCell){
        if (this.validBoardCell(boardCell)) {
            return this.gameBoard.get(boardCell.getXCor()).get(boardCell.getYCor());
        } else {
            throw new IllegalArgumentException("boardCell out of bounds of the board");
        }
    }
    /**************************************************************
     * function name: getColorOfBoardCell
     * Input: int xCor, int yCor
     * @return SquareColor
     * Function operation: returns the color at the requested cell
     *       of the board (represented by its co;ordinates).
     *       !!! throws exception if the boardCell doesn't represent
     *       a valid cell in the board !!!
     **************************************************************/
    public SquareColor getColorOfBoardCell(int x, int y)  {
        if (this.validBoardCell(x, y)) {
            return this.gameBoard.get(x).get(y);
        } else {
            throw new IllegalArgumentException("boardCell out of bounds of the board");
        }
    }

    /**************************************************************
     * function name: setColorOfRequestedCell
     * Input: BoardCell& boardCell, SquareColor color
     * @return void
     * Function operation: assignes the requested color at the requested cell
     *       of the board.
     *       !!! throws exception if the boardCell doesn't represent
     *       a valid cell in the board !!!
     **************************************************************/
    public void setColorOfRequestedCell(BoardCell boardCell, SquareColor color) {
        if (this.validBoardCell(boardCell)) {
            this.gameBoard.get(boardCell.getXCor())
                    .set(boardCell.getYCor(),color);
        } else {
            throw new IllegalArgumentException("boardCell out of bounds of the board");
        }

    };

    /**************************************************************
     * function name: getBoardLength
     * Input: no input
     * @return int
     * Function operation: returns the length of the gameBoard in
     *       this instance of Board
     **************************************************************/
    public int getBoardLength(){
        return this.boardLength;
    };

    /**************************************************************
     * function name: getBoardWidth
     * Input: no input
     * @return int
     * Function operation: returns the width of the gameBoard in
     *       this instance of Board
     **************************************************************/
    public int getBoardWidth(){
        return this.boardWidth;
    };
    /**************************************************************
     * function name: gameBoardFull
     * Input: no input
     * @return bool
     * Function operation: returns true if the board is full
     *       (there are no Blank cells) and false otherwise
     **************************************************************/
    public boolean gameBoardFull(){
        for (int i = 0; i < this.getBoardLength(); i++) {
            for (int j = 0; j < this.getBoardWidth(); j++) {
                if (this.getColorOfBoardCell(i, j) == SquareColor.Blank) {
                    return false;
                }
            }
        }
        return true;

    };
    
    /**************************************************************
     * function name: getBoard
     * Input: no input
     * @return SquareColor[][]
     * Function operation: returns the board in a matrix form
     **************************************************************/
    public SquareColor[][] getBoard() {
    	SquareColor[][] returnBoard = new SquareColor[this.boardLength][this.boardWidth];
    	for (int i = 0; i < returnBoard.length; i++) {
    		for (int j = 0; j < returnBoard[0].length; j++) {
    			returnBoard[i][j] = this.gameBoard.get(i).get(j);
    		}
    	}
    	return returnBoard;
    }
}

