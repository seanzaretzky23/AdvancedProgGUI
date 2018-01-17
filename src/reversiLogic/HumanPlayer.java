package reversiLogic;
import java.util.ArrayList;
public class HumanPlayer extends Player {
	
	private InOutAbs command;
	
    /**************************************************************
     * function name: HumanPlayer class builder
     * Input: SquareColor color
     *
     * Function operation: creates a HumanPlayer object of the specified color.
     *      throws an exception if color is Blank
     **************************************************************/
    public HumanPlayer(Board.SquareColor color, InOutAbs communicationAdapt){
        if (color == Board.SquareColor.Blank) {
            throw new IllegalArgumentException("player's color can only be black or white");
        } else {
            this.playersColor = color;
        }
        command = communicationAdapt;
    }

    /**************************************************************
     * function name: makeMove
     * Input: vector<BoardCell> possibleMoves
     * @return BoardCell representing player's choice for the next move
     * Function operation: asks the player for input on its next move
     *   untill a valid choice is entered
     **************************************************************/
    public BoardCell makeMove(ArrayList<BoardCell> possibleMoves) {
        if (possibleMoves.size() == 0) {
            throw new IllegalArgumentException("empty possibleMoves vector is invalid");
        }
        BoardCell chosenCell = null;
        command.PrintOptions(possibleMoves);
        command.PrintEnterMovesInForm();
        chosenCell = command.receiveMove();
	
        //checking if the choice is one of the offered moves
        if(!possibleMoves.contains(chosenCell)) {
        	command.PrintNotPosMove();
        	chosenCell = null;
        }
               
        return chosenCell;
    }
}


