package reversiLogic;
public class ConsoleBoard extends Board{

    public ConsoleBoard(int length, int width)
    {
        /*boardLength = length;
        boardWidth = width;
        //checking for valid board, if valid initialize
        if (boardLength >= 2 && (boardLength % 2) == 0 && boardWidth >= 2 && (boardWidth % 2) == 0) {
            this.initializeBoard();
        } else {
            throw new IllegalArgumentException("board length and width should be greater than 2 and ");
        }*/
    	super(length, width);

    }
    public ConsoleBoard()
    {
        /*boardLength=8;
        boardWidth=8;
        //checking for valid board, if valid initialize
        if (boardLength >= 2 && (boardLength % 2) == 0 && boardWidth >= 2 && (boardWidth % 2) == 0) {
            this.initializeBoard();
        } else {
            throw new IllegalArgumentException("board length and width should be greater than 2 and ");
        }*/
    	super();
    }


    public void printBoard() {
        command.PrintBoard(this);
    }

}
