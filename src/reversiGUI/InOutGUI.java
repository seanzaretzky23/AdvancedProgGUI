package reversiGUI;
import java.util.ArrayList;

import reversiLogic.Board.SquareColor;
import reversiLogic.BoardCell;
import reversiLogic.InOutAbs;
public class InOutGUI implements InOutAbs{
	
	private MazeBoardController mazeBoardController;
	@Override
	public BoardCell receiveMove() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void PrintString(String str) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void PrintWhosTurn(boolean whosTurn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintWhoWon(int winner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintOptions(ArrayList<BoardCell> optArr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintNotPosMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintEnterMovesInForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintInvaidInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintBoard(int boardLength, int boardWidth, ArrayList<ArrayList<SquareColor>> gameBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintNoMoves(boolean isEnd) {
		// TODO Auto-generated method stub
		
	}

}
