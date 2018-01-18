package reversiGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javafx.scene.paint.Color;

public class SettingsFileHandler {
	private static final String fileAddress= "src/reversiGUI/settingsFile.txt";
	private static final int defaultBoardSize = 8;
	
	public SettingsFileHandler() {}
	
	public Color getFirstPlayerColor() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileAddress)));
			String line;
			line = br.readLine();
			br.close();
			return Color.web(line);
		} catch (Exception exc) {
			//default values
			return Color.BLACK;
		}
	}
	
	public Color getSecondPlayerColor() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileAddress)));
			String line;
			line = br.readLine();
			line = br.readLine();
			br.close();
			return Color.web(line);
		} catch (Exception exc) {
			//default values
			return Color.WHITE;
		}
	}
	
	public int getBoardSize() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileAddress)));
			String line;
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			int boardSize = Integer.parseInt(line);
			br.close();
			return boardSize;
		} catch (Exception exc) {
			//default value
			return defaultBoardSize;
		}
	}
	
	public boolean updateSettingsFile(Color firstPlayerColor, Color secondPlayerColor, int boardSize) {
		try {
			File file = new File (fileAddress);
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.println (firstPlayerColor.toString());
			printWriter.println(secondPlayerColor.toString());
			printWriter.println(boardSize + "");
			printWriter.close ();
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
	
	
}
