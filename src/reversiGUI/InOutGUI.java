package reversiGUI;
import java.util.ArrayList;

import reversiLogic.Board;
import reversiLogic.Board.SquareColor;
import reversiLogic.BoardCell;
import reversiLogic.InOutAbs;
public class InOutGUI implements InOutAbs{
	
	private MazeGameController mazeGameController;
	private TurnManager turnManager;
	
	public InOutGUI(MazeGameController mazeGameController, TurnManager turnManager) {
		this.mazeGameController = mazeGameController;
		this.turnManager = turnManager;
	}
	
	@Override
	public BoardCell receiveMove() {
		// TODO Auto-generated method stub
		return turnManager.getCurrentTurn();
	}


	@Override
	public void PrintString(String str) {
		AlertBoxFactory.display("message", str);
		
	}


	@Override
	public void PrintWhosTurn(boolean whosTurn) {
		this.mazeGameController.setWhosTurn(whosTurn);
		
	}

	@Override
	public void PrintWhoWon(int winner) {
		switch(winner) {
    	case 1:
    		AlertBoxFactory.display("Winning announcement", "First player is the winner");
    		break;
    	case 2:
    		AlertBoxFactory.display("Winning announcement", "Second player is the winner");
    		break;
    	case 3:
    		AlertBoxFactory.display("Winning announcement", "Its a tie");
    		break;
		}
		
	}

	@Override
	public void PrintOptions(ArrayList<BoardCell> optArr) {
		// TODO Auto-generated method stub
		// maybe put something here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	@Override
	public void PrintNotPosMove() {
		// TODO Auto-generated method stub
		AlertBoxFactory.display("Invalid Input", "Your choice isn't one of the possible moves");
	}

	@Override
	public void PrintEnterMovesInForm() {
		// TODO Auto-generated method stub
		//maybe put something here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	@Override
	public void PrintInvaidInput() {
		// TODO Auto-generated method stub
		AlertBoxFactory.display("Invalid Input", "You Chose Invalid Move, Try Again");
	}

	@Override
	public void PrintBoard(Board board) {// work only with the array list
		Board.SquareColor[][] newBoard = new Board.SquareColor[board.getBoardLength()][board.getBoardWidth()];
		Board.SquareColor[][] existingBoard = board.getBoard();
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard[0].length; j++) {
				newBoard[i][j] = existingBoard[i][j];
			}
		}
		this.mazeGameController.getMazeBoard().updateBoard(newBoard);
		this.mazeGameController.getMazeBoard().draw();
	}

	@Override
	public void PrintGameOver() {
		// TODO Auto-generated method stub
		//maybe put something here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	@Override
	public void PrintNoMoves(boolean isEnd) {
		// TODO Auto-generated method stub
		//maybe put something here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

}
