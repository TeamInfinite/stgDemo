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
		scene = new Scene(pane, PaneParam.MAIN_PANE_WIDTH, PaneParam.MAIN_PANE_HEIGHT);
		
		stage.setTitle(PaneParam.GUI_TITLE);
		stage.setScene(scene);
		stage.setResizable(false);
		
		scene.onKeyPressedProperty().bind(pane.onKeyPressedProperty());
		scene.onKeyReleasedProperty().bind(pane.onKeyReleasedProperty());
	}
	
	public void setRoot(Pane toSet) {
		scene.onKeyPressedProperty().unbind();
		scene.onKeyReleasedProperty().unbind();
		FadeTransition transition = new FadeTransition(Duration.millis(1500));
		transition.setNode(scene.getRoot());
		transition.setFromValue(1);
		transition.setToValue(0);
		transition.setOnFinished(e -> {
			transition.setNode(toSet);
			transition.setFromValue(0);
			transition.setToValue(1);
			transition.setOnFinished(e2 -> {});
			transition.play();
			scene.setRoot(toSet);
			scene.onKeyPressedProperty().bind(toSet.onKeyPressedProperty());
			scene.onKeyReleasedProperty().bind(toSet.onKeyReleasedProperty());
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
