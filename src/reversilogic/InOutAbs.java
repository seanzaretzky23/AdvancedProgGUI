package reversilogic;
import java.util.ArrayList;

public interface InOutAbs {

    public abstract BoardCell receiveMove();
    public abstract void PrintString(String str);
    public abstract void PrintWhosTurn(boolean whosTurn);
    public abstract void PrintWhoWon(int winner);
    public abstract void PrintOptions(ArrayList<BoardCell> optArr);
    public abstract void PrintNotPosMove();
    public abstract void PrintEnterMovesInForm();
    public abstract void PrintInvaidInput();
    public abstract void PrintBoard(Board board);
    public abstract void PrintGameOver();
    public abstract void PrintNoMoves(boolean isEnd );
}
