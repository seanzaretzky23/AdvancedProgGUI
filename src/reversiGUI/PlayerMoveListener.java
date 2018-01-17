package reversiGUI;

public class PlayerMoveListener {
	private reversiLogic.GameManager gameManager;
	
	public PlayerMoveListener(reversiLogic.GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	public void playNextTurn() {
		this.gameManager.playNextGameIteration();
	}
}
