package model;

import java.util.Iterator;

import controller.GameParam;
import controller.MainController;
import gui.GamePaneManager;
import gui.ScorePaneManager;
import gui.ViewManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import model.bullets.PBullet;
import util.Util;

public class GameLoop extends AnimationTimer{

	// == fields ==
	private MainController controller;
	private GamePaneManager gamePane;
	private ScorePaneManager scorePane;
	private ViewManager viewManager;
	private long lastUpdate = 0;
	private long startTime = 0;
	
	
	public GameLoop(MainController controller, GamePaneManager gamePane, ScorePaneManager scorePane, ViewManager viewManager) {
		this.controller = controller;
		this.gamePane = gamePane;
		this.scorePane = scorePane;
		this.viewManager = viewManager;
	}

	@Override
	public void handle(long now) {
		try {
			if(controller.getGame().isRunning() && now - lastUpdate >= 8_000_000) {
				
				if(controller.getGame().getRespawning() > 0) {
					controller.getGame().respawning();
					if(controller.getGame().getRespawning() == 0) {
						viewManager.initPlayer();
					}
				} else {
					playerMove();
				}
				bulletsMove();
				
				if(controller.getGame().isTimeStoped()) {
					controller.getGame().moveTimeStopTimer();
					controller.getGame().getPlayerImageView().setOpacity(1);
				} else {
					controller.getGame().moveTimer();
					enemySpawn();
					enemiesMove();
					enemiesFire();
					bossMoveAndFire();
					eBulletsMove();
				}
				
				if(controller.getGame().getBoss() != null) {
					gamePane.refreshBossInfo();
				}
				
				checkSuicide();
				playerFire();
				removeDead();
			}
			refreshFps(30);
			lastUpdate = now ;
		} catch(Exception e) {
			Util.errorBox("Error", e.getMessage());
		}
	}

	private void bossMoveAndFire() {
		if(controller.getGame().getBoss() != null) {
			int score = controller.getGame().getBoss().nextPhase();
			controller.getGame().score(score);
			scorePane.refreshScore();
			controller.getGame().getBoss().move();
			controller.getGame().getBoss().fire(gamePane, controller.getGame());
		}
	}

	private void enemySpawn() {
		controller.getGame().enemySpawn(gamePane);
	}
	
	private void playerMove() {
		if(controller.isMovingUp()) controller.moveUp();
		if(controller.isMovingDown()) controller.moveDown();
		if(controller.isMovingLeft()) controller.moveLeft();
		if(controller.isMovingRight()) controller.moveRight();
		if((!controller.isMovingLeft() && !controller.isMovingRight()) || (controller.isMovingLeft() && controller.isMovingRight())) controller.getGame().getPlayerImageView().setImage(new Image(PlayerImpl.IMG_ADDR));
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
			
			if(controller.getGame().getBoss() != null) {
				if(b.getImageView().getBoundsInParent().intersects(controller.getGame().getBoss().getImageView().getBoundsInParent())) {
					b.suicide();
					controller.getGame().getBoss().lostHp(b.getPower());
					// System.out.println(controller.getGame().getBoss().getPhaseHp());
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
		}
	}
	
	private void eBulletsMove() {
		for(EBullet eb : controller.getGame().geteBullets()) {
			eb.move();
			eb.suiciding();
			if(controller.getGame().getImmunity() == 0) {
				if(controller.getGame().getRespawning() == 0 && eb.getHitBox().getBoundsInParent().intersects(controller.getGame().getPlayerHitBox().getBoundsInParent())) {
					eb.suicide();
					viewManager.killPlayer();
					int result = controller.getGame().playerDead(scorePane);
					if(result == GameParam.NO_LIFE_LEFT) {
						gamePane.showGameOverPane();
					}
				}
			}
		}
	}
	
	private void checkSuicide() {
		for(Enemy e : controller.getGame().getEnemies()) {
			if(controller.getGame().getPlayerHitBox().getBoundsInParent().intersects(e.getImageView().getBoundsInParent())) {
				viewManager.killPlayer();
				viewManager.initPlayer();
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
		
		if(controller.getGame().getBoss() != null) {
			if(controller.getGame().getBoss().isDead()) {
				gamePane.getPane().getChildren().remove(controller.getGame().getBoss().getImageView());
				controller.getGame().setBoss(null);
				gamePane.nextEvent();
			}
		}
	}
	
	private void refreshFps(int frameSpin) {
		if(controller.getGame().getTimer() % frameSpin == 0) {
			viewManager.getFps().setText("" + fpsCalculate(frameSpin));
		}
	}
	
	private void playerFire() {
		controller.fire();
	}
	
	public double fpsCalculate(long frameSpin) {
		long elapseTime = System.nanoTime() - startTime;
		startTime = System.nanoTime();
		double second = (double)elapseTime / 1000000000;
		
		double frame = frameSpin / second;
		return frame;
	}

}
