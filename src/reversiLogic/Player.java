package reversiLogic;
import java.util.ArrayList;

public abstract class Player {

    protected Board.SquareColor playersColor;
    public Player(){};
    /**************************************************************
     * function name: Player class builder
     * Input: SquareColor color
     * @return Player object instance
     * Function operation: creates a Player object of the specified color.
     *      throws an exception if color is Blank
     **************************************************************/
    public Player(Board.SquareColor color){
        if (color == Board.SquareColor.Blank) {
            throw new IllegalArgumentException("player's color can only be black or white");
        } else {
            this.playersColor = color;
        }
    };

    /**************************************************************
     * function name: getColor
     * Input: no input
     * @return the color of the player
     **************************************************************/
    public Board.SquareColor getColor(){
        return this.playersColor;
    };

    /**************************************************************
     * function name: makeMove
     * Input: vector<BoardCell> possibleMoves
     * @return BoardCell representing player's choice for the next move
     **************************************************************/
    public abstract BoardCell makeMove(ArrayList<BoardCell> possibleMoves);
}
