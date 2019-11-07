package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {
	
	public static void errorBox(String title, String message) {
		Alert errorBox = new Alert(AlertType.INFORMATION);
		
		errorBox.setTitle(title);
		errorBox.setHeaderText(message);
		errorBox.setContentText(null);
		
		errorBox.showAndWait();
	}

}
