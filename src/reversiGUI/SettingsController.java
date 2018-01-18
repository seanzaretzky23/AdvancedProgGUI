package reversiGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController implements Initializable{
	private static final int defaultBoardSize = 8;
	@FXML
	private Button saveButton;
	@FXML
	private ColorPicker colorPickerFirst, colorPickerSecond;
	@FXML
	private ChoiceBox<Integer> choiceBox;
	@FXML
	protected void saveSettings() {
		Color firstPlayersColor = colorPickerFirst.getValue();
		Color secondPlayersColor = colorPickerSecond.getValue();
		int boardSize = choiceBox.getValue();
		SettingsFileHandler fileHandler   = new SettingsFileHandler();
		if (firstPlayersColor.equals(secondPlayersColor)) {
			AlertBoxFactory.display("Cant Save", "You need to choose different color for the players");
		} else {
			fileHandler.updateSettingsFile(firstPlayersColor, secondPlayersColor, boardSize);
			Stage buttonsStage = (Stage)this.saveButton.getScene().getWindow();
			buttonsStage.close();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//initialize colorPickers with saved info in file
		for (int i = 2; i <= 10; i++) {
			choiceBox.getItems().add(2 * i);
		}
		SettingsFileHandler fileHandler = new SettingsFileHandler();
		colorPickerFirst.setValue(fileHandler.getFirstPlayerColor());
		colorPickerSecond.setValue(fileHandler.getSecondPlayerColor());
		int boardSize = fileHandler.getBoardSize();
		if (choiceBox.getItems().contains(boardSize))
			choiceBox.setValue(boardSize);
		else
			choiceBox.setValue(defaultBoardSize);
		
	}
}
