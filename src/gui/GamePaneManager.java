package gui;

import controller.MainController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePaneManager {
	
	// == fields ==
	private MainController controller;
	
	private AnchorPane pane;
	private Rectangle back;
	
	// == constructor ==
	public GamePaneManager(MainController controller) {
		this.controller = controller;
		
		pane = new AnchorPane();
		pane.setPrefSize(Param.GAME_PANE_WIDTH, Param.GAME_PANE_HEIGHT);
		pane.setMaxSize(Param.GAME_PANE_WIDTH, Param.GAME_PANE_HEIGHT);
		pane.setMinSize(Param.GAME_PANE_WIDTH, Param.GAME_PANE_HEIGHT);
		back = new Rectangle(0, 10, Param.GAME_PANE_WIDTH, Param.GAME_PANE_HEIGHT - 20);
		back.setStroke(Color.AZURE);
		back.setStrokeWidth(1);
		back.setFill(Color.rgb(0, 105, 183));
		back.setArcHeight(10);
		back.setArcWidth(10);
		pane.setClip(new Rectangle(0, 10, Param.GAME_PANE_WIDTH, Param.GAME_PANE_HEIGHT - 20));
		
		pane.getChildren().add(back);
	}
	
	// == getters ==
	public AnchorPane getPane() {
		return pane;
	}

}
