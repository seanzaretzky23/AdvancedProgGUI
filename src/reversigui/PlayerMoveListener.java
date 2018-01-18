package reversigui;

public class PlayerMoveListener {
	private reversilogic.GameManager gameManager;
	
	public PlayerMoveListener(reversilogic.GameManager gameManager) {
		this.gameManager = gameManager;
	}
	
	public void playNextTurn() {
		this.gameManager.playNextGameIteration();
	}
}
