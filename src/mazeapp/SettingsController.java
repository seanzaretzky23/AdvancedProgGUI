package mazeapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

public class SettingsController implements Initializable{
	@FXML
	private ColorPicker colorPickerFirst, colorPickerSecond;
	@FXML
	private ChoiceBox<Integer> choiceBox;
	@FXML
	protected void saveSettings() {
		Color firstPlayersColor = colorPickerFirst.getValue();
		Color SecondPlayersColor = colorPickerSecond.getValue();
		int boardSize = choiceBox.getValue();
		try {
		 //save into file
		} catch(Exception exc) {
			
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//initialize colorPickers with saved info in file
		colorPickerFirst.setValue(Color.BLACK);
		colorPickerSecond.setValue(Color.WHITE);
		for (int i = 2; i <= 10; i++) {
			choiceBox.getItems().add(2 * i);
		}
		choiceBox.setValue(8); // put the value written in the file !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}
}
