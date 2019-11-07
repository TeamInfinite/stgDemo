package gui;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Util;

/** Main controller of the program.
 *  @author Yuxuan Bu */
public class SceneManager {
	
	private int score;
	
	private Scene scene;
	private Stage stage;
	

	/** Setting stage, scene and the first pane that will be shown.
	 *  @param pane the first pane of the window, usually the title screen. */
	public void setInitialPane(Pane pane) {
		stage = new Stage();
		scene = new Scene(pane, PaneParam.MAIN_PANE_WIDTH, PaneParam.MAIN_PANE_HEIGHT);
		
		stage.setTitle(PaneParam.GUI_TITLE);
		stage.setScene(scene);
		stage.setResizable(false);
		
		scene.onKeyPressedProperty().bind(pane.onKeyPressedProperty());
		scene.onKeyReleasedProperty().bind(pane.onKeyReleasedProperty());
	}
	
	/** Set another pane as the root of this program
	 *  @param toSet the pane that will be set as root. */
	public void setRoot(Pane toSet) {
		try {
			scene.onKeyPressedProperty().unbind();
			scene.onKeyReleasedProperty().unbind();
			FadeTransition transition = new FadeTransition(Duration.millis(500));
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
		} catch(Exception e) {
			Util.errorBox("Error", e.getMessage());
		}
	}
	
	public void addScore(int score) {
		this.score += score;
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
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
