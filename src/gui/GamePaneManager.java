package gui;

import java.util.LinkedList;

import controller.MainController;
import gui.titleScreen.TitleScreenManager;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GamePaneManager {
	
	// == fields ==
	private MainController controller;
	private SceneManager sm;
	
	private AnchorPane pane;
	private Rectangle back;
	private PausePane pausePane = new PausePane();
	private DialogPane dialogPane = new DialogPane();
	private GameOverPane gameOverPane;
	
	// == constructor ==
	public GamePaneManager(SceneManager sm, MainController controller) {
		this.controller = controller;
		this.sm = sm;
		
		gameOverPane = new GameOverPane(sm, controller);
		
		pane = new AnchorPane();
		pane.setPrefSize(PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT);
		pane.setMaxSize(PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT);
		pane.setMinSize(PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT);
		back = new Rectangle(0, 10, PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT - 20);
		back.setStroke(Color.AZURE);
		back.setStrokeWidth(1);
		back.setFill(Color.rgb(0, 105, 183));
		back.setArcHeight(10);
		back.setArcWidth(10);
		pane.setClip(new Rectangle(0, 10, PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT - 20));
		
		pane.getChildren().addAll(back, dialogPane);
	}
	
	public void showPausePane() {
		pane.getChildren().add(pausePane);
	}
	
	public void removePausePane() {
		if(pane.getChildren().contains(pausePane))
			pane.getChildren().remove(pausePane);
	}
	
	public void showGameOverPane() {
		FadeTransition fadein = new FadeTransition(Duration.millis(1000));
		pane.getChildren().add(gameOverPane);
		fadein.setNode(gameOverPane);
		fadein.setFromValue(0);
		fadein.setToValue(1);
		fadein.setOnFinished(e -> {
			sm.getScene().onKeyPressedProperty().bind(gameOverPane.onKeyPressedProperty());
			sm.getScene().onKeyReleasedProperty().bind(gameOverPane.onKeyReleasedProperty());
		});
		
		
		fadein.play();
	}
	
	public void removeGameOverPane() {
		if(pane.getChildren().contains(gameOverPane)) {
			pane.getChildren().remove(gameOverPane);
		}
	}
	
	public void showDialog() {
		dialogPane.showDialog();
	}
	
	public void addDialog(double x, double y, String imageAddr, String dialog) {
		dialogPane.addDialog(x, y, imageAddr, dialog);
	}
	
	public void removeDialog() {
		dialogPane.clear();
	}
	
	public boolean hasNextDialog() {
		return dialogPane.hasNext();
	}
	
	// == getters ==
	public AnchorPane getPane() {
		return pane;
	}
}

class PausePane extends AnchorPane {
	
	private Rectangle back = new Rectangle(0, 10, PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT - 20);
	private Label lb = new Label("PAUSE");
	
	public PausePane() {
		back.setFill(Color.BLACK);
		back.setOpacity(0.3);
		lb.setTextFill(Color.WHITE);
		lb.setTranslateX(PaneParam.GAME_PANE_WIDTH / 2 - 10);
		lb.setTranslateY(PaneParam.GAME_PANE_HEIGHT / 2);
		
		getChildren().addAll(back, lb);
	}
}

class DialogPane extends AnchorPane {
	private class DialogBox extends AnchorPane {
		private Rectangle imageBoxBack = new Rectangle(0, 0, PaneParam.DIALOG_BOX_SIDE, PaneParam.DIALOG_BOX_SIDE);
		private ImageView image;
		private ImageView box = new ImageView(PaneParam.BOX_IMAGE_ADDR);
		private Label dialog = new Label();
		
		public DialogBox(double x, double y, String image_addr, String dialog) {
			setTranslateX(x);
			setTranslateY(y);
			
			imageBoxBack.setArcHeight(20);
			imageBoxBack.setArcWidth(20);
			imageBoxBack.setStroke(Color.RED);
			
			image = new ImageView(image_addr);
			image.setFitWidth(62);
			image.setFitHeight(62);
			image.setTranslateX(5);
			image.setTranslateY(5);
			
			box.setTranslateX(-40);
			box.setTranslateY(45);
			box.setRotate(180);
			box.setFitWidth(200);
			box.setFitHeight(80);
			
			this.dialog.setText(dialog);
			this.dialog.setTranslateX(10);
			this.dialog.setTranslateY(83);
			this.dialog.setTextFill(Color.WHITE);
			
			getChildren().addAll(imageBoxBack, image, box, this.dialog);
		}
	}
	
	private Rectangle back = new Rectangle(0, 10, PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT - 20);
	private LinkedList<DialogBox> dialogs = new LinkedList<>();
	
	public DialogPane() {
		setPrefSize(PaneParam.GAME_PANE_WIDTH, PaneParam.GAME_PANE_HEIGHT);
		setOpacity(0);
		
		back.setFill(Color.BLACK);
		back.setOpacity(0.3);
		getChildren().add(back);
	}
	
	public void showDialog() {
		setOpacity(1);
		if(getChildren().size() > 1) {
			getChildren().remove(1);
		}
		getChildren().add(dialogs.poll());
	}
	
	public void addDialog(double x, double y, String imageAddr, String dialog) {
		dialogs.offer(new DialogBox(x, y, imageAddr, dialog));
	}
	
	public void clear() {
		setOpacity(0);
	}
	
	public boolean hasNext() {
		return dialogs.size() != 0;
	}
}

class GameOverPane extends AnchorPane {
	
	private MainController controller;
	
	private ImageView back;
	private ImageView continueGame;
	private ImageView startOver;
	private ImageView backToTitle;
	private ImageView current;
	
	public GameOverPane(SceneManager sm, MainController controller) {
		this.controller = controller;
		
		setWidth(PaneParam.GAME_PANE_WIDTH);
		setHeight(PaneParam.GAME_PANE_HEIGHT);
		
		back = new ImageView(PaneParam.GAME_OVER_BACK_ADDR);
		back.setFitWidth(PaneParam.GAME_PANE_WIDTH);
		back.setFitHeight(PaneParam.GAME_PANE_HEIGHT);
		
		continueGame = new ImageView(PaneParam.GAME_OVER_CONTINUE_SELECTED);
		continueGame.setLayoutX(PaneParam.GAME_PANE_WIDTH / 2 - 100);
		continueGame.setLayoutY(PaneParam.GAME_PANE_HEIGHT / 2 - 80);
		continueGame.setFitWidth(150);
		continueGame.setFitHeight(50);
		
		startOver = new ImageView(PaneParam.GAME_OVER_STARTOVER);
		startOver.setLayoutX(PaneParam.GAME_PANE_WIDTH / 2 - 100);
		startOver.setLayoutY(PaneParam.GAME_PANE_HEIGHT / 2 - 20);
		startOver.setFitWidth(150);
		startOver.setFitHeight(50);
		
		
		backToTitle = new ImageView(PaneParam.GAME_OVER_BACK_TO_TITLE);
		backToTitle.setLayoutX(PaneParam.GAME_PANE_WIDTH / 2 - 100);
		backToTitle.setLayoutY(PaneParam.GAME_PANE_HEIGHT / 2 + 40);
		backToTitle.setFitWidth(150);
		backToTitle.setFitHeight(50);
		
		current = continueGame;
		
		setOnKeyPressed(e -> {
			switch(e.getCode()) {
			case UP:
				if(current == startOver) {
					current.setImage(new Image(PaneParam.GAME_OVER_STARTOVER));
					current = continueGame;
					current.setImage(new Image(PaneParam.GAME_OVER_CONTINUE_SELECTED));
				} else if(current == backToTitle) {
					current.setImage(new Image(PaneParam.GAME_OVER_BACK_TO_TITLE));
					current = startOver;
					current.setImage(new Image(PaneParam.GAME_OVER_STARTOVER_SELECTED));
				}
				break;
			case DOWN:
				if(current == continueGame) {
					current.setImage(new Image(PaneParam.GAME_OVER_CONTINUE));
					current = startOver;
					current.setImage(new Image(PaneParam.GAME_OVER_STARTOVER_SELECTED));
				} else if(current == startOver) {
					current.setImage(new Image(PaneParam.GAME_OVER_STARTOVER));
					current = backToTitle;
					current.setImage(new Image(PaneParam.GAME_OVER_BACK_TO_TITLE_SELECTED));
				}
				break;
			case ENTER:
				if(current == continueGame) {
					
				} else if(current == startOver) {
					
				} else if(current == backToTitle) {
					sm.setRoot(new LoadingScreen(new TitleScreenManager(sm)));
				}
				break;
			default:
				break;
			}
		});
		
		getChildren().addAll(back, continueGame, startOver, backToTitle);
	}
	
}
