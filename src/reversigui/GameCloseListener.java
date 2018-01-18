package reversigui;

import javafx.stage.Stage;

public class GameCloseListener {
	private Stage stageToManageCLose;
	
	public GameCloseListener(Stage stage) {
		this.stageToManageCLose = stage;
	}
	
	public void closeTheStage(){
		this.stageToManageCLose.close();
	}
}
