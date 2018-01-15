package mazeapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

public class SettingsController implements Initializable{
	@FXML
	private ColorPicker colorPickerFirst, colorPickerSecond;
	@FXML
	private PasswordField passwordField;
	@FXML
	protected void saveSettings() {
		Color firstPlayersColor = colorPickerFirst.getValue();
		Color SecondPlayersColor = colorPickerSecond.getValue();
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
		
	}
}
