package gui;

import controller.GameParam;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Enemy;
import model.GameLoop;

public class ViewManager {
	
	// == fields ==
	private Scene scene;
	private AnchorPane pane;
	private GamePaneManager gamePane;
	private ScorePaneManager scorePane;
	private Label fps;
	
	private MainController controller = new MainController();
	private GameLoop loop;
	
	// == constructor ==
	public ViewManager(SceneManager sc) {
		scene = sc.getScene();
		
		initPane();
		initBackground();
		initGamePane();
		initScorePane();
		initFpsLabel();
		initKeys();
		initPlayer();
		initEnemy();
		initGameLoop();
		testSitting();
		
	}

	private void testSitting() {
		controller.getGame().start();
	}

	// == init methods ==
	private void initPane() {
		pane = new AnchorPane();
	}
	
	private void initBackground() {
		ImageView back = new ImageView("/resources/BACKGROUND.jpg");
		back.setFitWidth(640);
		back.setFitHeight(480);
		pane.getChildren().add(back);
	}
	
	private void initGamePane() {
		gamePane = new GamePaneManager(controller);
		gamePane.getPane().setLayoutX(10);
		gamePane.getPane().setLayoutY(10);
		pane.getChildren().add(gamePane.getPane());
	}
	
	private void initScorePane() {
		scorePane = new ScorePaneManager(controller);
		scorePane.getPane().setLayoutX(Param.GAME_PANE_WIDTH + 20);
		scorePane.getPane().setLayoutY(0);
		pane.getChildren().add(scorePane.getPane());
	}
	
	private void initFpsLabel() {
		fps = new Label();
		fps.setTextFill(Color.YELLOW);
		fps.setLayoutX(600);
		fps.setLayoutY(5);
		pane.getChildren().add(fps);
	}
	
	private void initKeys() {
		scene.setOnKeyPressed(e -> {
			switch(e.getCode()) {
			case UP:
				controller.setMovingUp(true);
				break;
			case DOWN:
				controller.setMovingDown(true);
				break;
			case LEFT:
				controller.setMovingLeft(true);
				break;
			case RIGHT:
				controller.setMovingRight(true);
				break;
			case SHIFT:
				controller.setSlowlyMoving(true);
				break;
			case Z:
				controller.setFiring(true);
				break;
			default:
				break;
			}
		});
		
		scene.setOnKeyReleased(e -> {
			switch(e.getCode()) {
			case UP:
				controller.setMovingUp(false);
				break;
			case DOWN:
				controller.setMovingDown(false);
				break;
			case LEFT:
				controller.setMovingLeft(false);
				break;
			case RIGHT:
				controller.setMovingRight(false);
				break;
			case SHIFT:
				controller.setSlowlyMoving(false);
				break;
			case Z:
				controller.setFiring(false);
				break;
			case A:
				controller.getGame().timeStop();
				break;
			case ESCAPE:
				if(controller.getGame().isRunning())
					controller.getGame().setRunning(false);
				else
					controller.getGame().setRunning(true);
				System.out.println(controller.getGame().isRunning());
				break;
			default:
				break;
			}
		});
		
	}
	
	public void initPlayer() {
		ImageView player = controller.getGame().getPlayerImageView();
		Rectangle hitBox = controller.getGame().getPlayerHitBox();
		
		player.setLayoutX(Param.SPAWN_POS_X);
		player.setLayoutY(Param.SPAWN_POS_Y);
		hitBox.setLayoutX(Param.SPAWN_POS_X + 16.5);
		hitBox.setLayoutY(Param.SPAWN_POS_Y + 22);
		
		hitBox.setFill(Color.RED);
		if(!GameParam.TEST_MODE)
			hitBox.setOpacity(0);
		
		controller.getGame().setImmunity(GameParam.RESPAWN_IMMUNITY_TIME);
		
		gamePane.getPane().getChildren().addAll(player, hitBox);
		
	}
	
	private void initEnemy() {
		for(Enemy e : controller.getGame().getEnemies()) {
			gamePane.getPane().getChildren().add(e.getImageView());
		}
		
	}
	
	private void initGameLoop() {
		loop = new GameLoop(controller, gamePane, scorePane, this);
		loop.start();
	}
	
	// == others ==
	public void killPlayer() {
		gamePane.getPane().getChildren().remove(controller.getGame().getPlayerImageView());
		gamePane.getPane().getChildren().remove(controller.getGame().getPlayerHitBox());
	}
	
	// == getters ==
	public AnchorPane getPane() {
		return pane;
	}
	
	public Label getFps() {
		return fps;
	}
}
