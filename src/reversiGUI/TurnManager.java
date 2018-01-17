package reversiGUI;
import reversiLogic.BoardCell;
public class TurnManager {

	private BoardCell boardCell;
	public TurnManager() {
		this.boardCell = null;
	}
	
	public void updateTurn(int horizontalIndex, int verticalIndex) {
		this.boardCell = new BoardCell(horizontalIndex, verticalIndex);
	}
	
	public BoardCell getCurrentTurn() {
		return new BoardCell(this.boardCell);
	}
}
