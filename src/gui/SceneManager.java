package gui;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneManager {
	
	private Scene scene;
	private Stage stage;

	public void setInitialPane(Pane pane) {
		stage = new Stage();
		scene = new Scene(pane, Param.MAIN_PANE_WIDTH, Param.MAIN_PANE_HEIGHT);
		
		stage.setTitle(Param.GUI_TITLE);
		stage.setScene(scene);
		stage.setResizable(false);
	}
	
	public void setRoot(Pane toSet) {
		FadeTransition transition = new FadeTransition(Duration.millis(1000));
		transition.setNode(scene.getRoot());
		transition.setFromValue(1);
		transition.setToValue(0);
		transition.setOnFinished(e -> {
			scene.setRoot(toSet);
			transition.setNode(toSet);
			transition.setFromValue(0);
			transition.setToValue(1);
			transition.setOnFinished(e2 -> {});
			transition.play();
		});
		transition.play();
	}
	
	// == getters and setters ==
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public Stage getStage() {
		return stage;
	}
}
