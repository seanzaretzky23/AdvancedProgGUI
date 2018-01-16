package reversiLogic;
import java.util.ArrayList;

public interface InOutAbs {

    public abstract int InInt();
    public abstract String InString();
    public abstract void PrintString(String str);
    public abstract void PrintChar(char ch);
    public abstract void PrintInt();
    public abstract void PrintWhosTurn(boolean whosTurn);
    public abstract void PrintWhoWon(int winner);
    public abstract void PrintOptions(ArrayList<BoardCell> optArr);
    public abstract void PrintNotPosMove();
    public abstract void PrintEnterMovesInForm();
    public abstract void PrintInvaidInput();
    public abstract void PrintBoard(int boardLength,int boardWidth,
                                    ArrayList<ArrayList<SquareColor>> gameBoard);
    public abstract void PrintGameOver();
    public abstract void PrintNoMoves(boolean isEnd );
}
