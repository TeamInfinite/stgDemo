package test;

import gui.SceneManager;
import gui.ViewManager;
import gui.titleScreen.TitleScreenManager;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class TestPane extends AnchorPane {
	
	private SceneManager scene;
	
	public TestPane(SceneManager scene) {
		this.scene = scene;
		
		Button testButton = new Button("test");
		testButton.setOnAction(e -> scene.setRoot(new TitleScreenManager(scene).getPane()));
		
		getChildren().add(testButton);
		
	}

}
