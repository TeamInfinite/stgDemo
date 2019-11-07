package gui;

import controller.GameParam;
import controller.MainController;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class ScorePaneManager {
	
	// == fields ==
	private AnchorPane pane;
	private Label score;
	private Label life;
	private Label bomb;
	
	private MainController controller;
	
	public ScorePaneManager(MainController controller) {
		this.controller = controller;
		
		pane = new AnchorPane();
		score = new Label("Score: 0");
		score.setLayoutX(20);
		score.setLayoutY(30);
		score.setTextFill(Color.WHITE);
		life = new Label("Life: 3");
		life.setLayoutX(20);
		life.setLayoutY(60);
		life.setTextFill(Color.WHITE);
		bomb = new Label("Bomb: " + GameParam.INIT_BOMB_CAPACITY);
		bomb.setLayoutX(20);
		bomb.setLayoutY(90);
		bomb.setTextFill(Color.WHITE);
		
		pane.getChildren().addAll(score, life, bomb);
	}
	
	public void refreshScore() {
		score.setText("Score: " + controller.getGame().getScore());
	}
	
	public void refreshLife() {
		life.setText("Life: " + controller.getGame().getLife());
	}
	
	public void refreshBomb() {
		bomb.setText("Bomb: " + controller.getGame().getBomb());
	}
	
	// == getters ==
	public AnchorPane getPane() {
		return pane;
	}
	

}
