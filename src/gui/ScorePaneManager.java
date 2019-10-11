package gui;

import controller.MainController;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ScorePaneManager {
	
	// == fields ==
	private AnchorPane pane;
	private Label score;
	private Label life;
	
	private MainController controller;
	
	public ScorePaneManager(MainController controller) {
		this.controller = controller;
		
		pane = new AnchorPane();
		score = new Label("Score: 0");
		score.setLayoutX(20);
		score.setLayoutY(20);
		life = new Label("Life: 3");
		life.setLayoutX(20);
		life.setLayoutY(60);
		
		pane.getChildren().addAll(score, life);
	}
	
	public void refreshScore() {
		score.setText("Score: " + controller.getGame().getScore());
	}
	
	public void refreshLife() {
		life.setText("Life: " + controller.getGame().getLife());
	}
	
	
	// == getters ==
	public AnchorPane getPane() {
		return pane;
	}
	

}
