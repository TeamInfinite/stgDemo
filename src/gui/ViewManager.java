package gui;


import java.util.Iterator;

import controller.GameParam;
import controller.MainController;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Bullet;
import model.EBullet;
import model.Enemy;
import model.PlayerImpl;
import model.bullets.PBullet;

public class ViewManager {
	
	// == fields ==
	private Stage stage;
	private Scene scene;
	private AnchorPane pane;
	private GamePaneManager gamePane;
	private ScorePaneManager scorePane;
	private Label fps;
	private AnimationTimer gameLoop;
	
	private long startTime = 0;
	
	private MainController controller = new MainController();
	
	// == constructor ==
	public ViewManager() {
		
		initStage();
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
	private void initStage() {
		pane = new AnchorPane();
		scene = new Scene(pane, Param.MAIN_PANE_WIDTH, Param.MAIN_PANE_HEIGHT);
		stage = new Stage();
		
		stage.setTitle(Param.GUI_TITLE);
		stage.setScene(scene);
		stage.setResizable(false);
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
	
	private void initPlayer() {
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
		gameLoop = new AnimationTimer() {
			
			private long lastUpdate = 0 ;
			@Override
			public void handle(long now) {
				
				if(controller.getGame().isRunning() && now - lastUpdate >= 8_000_000) {
					playerMove();
					bulletsMove();
					
					if(controller.getGame().isTimeStoped()) {
						controller.getGame().moveTimeStopTimer();
					} else {
						controller.getGame().moveTimer();
						enemySpawn();
						enemiesMove();
						enemiesFire();
						eBulletsMove();
					}
					
					//System.out.println(controller.getGame().isTimeStoped());
					
					// checkBoundary();
					checkSuicide();
					playerFire();
					removeDead();
				}
				refreshFps(30);
				lastUpdate = now ;
			}

		};
		
		gameLoop.start();
	}
	
	// == game loop methods ==
	private void enemySpawn() {
		controller.getGame().enemySpawn(gamePane);
	}
	
	private void playerMove() {
		if(controller.isMovingUp()) controller.moveUp();
		if(controller.isMovingDown()) controller.moveDown();
		if(controller.isMovingLeft()) controller.moveLeft();
		if(controller.isMovingRight()) controller.moveRight();
		if((!controller.isMovingLeft() && !controller.isMovingRight()) || (controller.isMovingLeft() && controller.isMovingRight())) controller.getGame().getPlayerImageView().setImage(new Image(PlayerImpl.IMG_ADDR));
		// System.out.println(controller.getGame().getPlayerHitBox().getLayoutX() + " " + controller.getGame().getPlayerHitBox().getLayoutY() + " " + controller.getGame().getPlayerImageView().getLayoutX() + " " + controller.getGame().getPlayerImageView().getLayoutY());
	}
	
	private void bulletsMove() {
		for(Bullet b : controller.getGame().getBullets()) {
			if(!gamePane.getPane().getChildren().contains(b.getImageView())) {
				gamePane.getPane().getChildren().add(b.getImageView());
			}
			b.suiciding();
			b.getImageView().setTranslateY(b.getImageView().getTranslateY() - PBullet.BULLET_SPEED);
			for(Enemy e : controller.getGame().getEnemies()) {
				if(b.getImageView().getBoundsInParent().intersects(e.getImageView().getBoundsInParent())) {
					b.suicide();
					e.lostHp(b.getPower());
				}
			}
		}
	}
	
	private void enemiesFire() {
		for(Enemy e : controller.getGame().getEnemies()) {
			e.fire(gamePane, controller.getGame());
		}
	}
	
	private void enemiesMove() {
		for(Enemy e : controller.getGame().getEnemies()) {
			e.move();
			// System.out.println(e.getImageView().getLayoutX() + " " + e.getImageView().getLayoutY());
		}
	}
	
	private void eBulletsMove() {
		for(EBullet eb : controller.getGame().geteBullets()) {
			eb.move();
			eb.suiciding();
			if(controller.getGame().getImmunity() == 0) {
				if(eb.getHitBox().getBoundsInParent().intersects(controller.getGame().getPlayerHitBox().getBoundsInParent())) {
					eb.suicide();
					killPlayer();
					initPlayer();
					controller.getGame().playerDead(scorePane);
				}
			}
		}
	}
	
	private void checkSuicide() {
		for(Enemy e : controller.getGame().getEnemies()) {
			if(controller.getGame().getPlayerHitBox().getBoundsInParent().intersects(e.getImageView().getBoundsInParent())) {
				killPlayer();
				initPlayer();
			}
		}
	}
	
	private void removeDead() {
		Iterator<Bullet> iterator = controller.getGame().getBullets().iterator();
		
		while(iterator.hasNext()) {
			Bullet bullet = iterator.next();
			if(bullet.getHp() <= 0) {
				gamePane.getPane().getChildren().remove(bullet.getImageView());
				iterator.remove();
			}
		}
		
		Iterator<Enemy> eIterator = controller.getGame().getEnemies().iterator();
		
		while(eIterator.hasNext()) {
			Enemy e = eIterator.next();
			if(e.getHp() <= 0) {
				gamePane.getPane().getChildren().remove(e.getImageView());
				eIterator.remove();
				
				if(e.getHp() != -10) {
					controller.getGame().score(e.getScore());
					scorePane.refreshScore();
				}
			}
		}
		
		Iterator<EBullet> eBIterator = controller.getGame().geteBullets().iterator();
		
		while(eBIterator.hasNext()) {
			EBullet eb = eBIterator.next();
			if(eb.getHp() <= 0) {
				gamePane.getPane().getChildren().remove(eb.getImageView());
				gamePane.getPane().getChildren().remove(eb.getHitBox());
				eBIterator.remove();
			}
		}
	}
	
	private void refreshFps(int frameSpin) {
		if(controller.getGame().getTimer() % frameSpin == 0) {
			fps.setText("" + fpsCalculate(frameSpin));
		}
	}
	
	private void playerFire() {
		controller.fire();
	}
	
	// == others ==
	private double fpsCalculate(long frameSpin) {
		long elapseTime = System.nanoTime() - startTime;
		startTime = System.nanoTime();
		double second = (double)elapseTime / 1000000000;
		
		double frame = frameSpin / second;
		return frame;
	}
	
	private void killPlayer() {
		gamePane.getPane().getChildren().remove(controller.getGame().getPlayerImageView());
		gamePane.getPane().getChildren().remove(controller.getGame().getPlayerHitBox());
	}
	
	// == getters ==
	public Stage getStage() {
		return stage;
	}
}
