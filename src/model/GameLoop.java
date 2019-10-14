package model;

import java.util.Iterator;

import controller.MainController;
import gui.GamePaneManager;
import gui.ScorePaneManager;
import gui.ViewManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import model.bullets.PBullet;

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
			} else {
				controller.getGame().moveTimer();
				enemySpawn();
				enemiesMove();
				enemiesFire();
				eBulletsMove();
			}
			checkSuicide();
			playerFire();
			removeDead();
		}
		refreshFps(30);
		lastUpdate = now ;
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
				if(eb.getHitBox().getBoundsInParent().intersects(controller.getGame().getPlayerHitBox().getBoundsInParent())) {
					eb.suicide();
					viewManager.killPlayer();
					controller.getGame().playerDead(scorePane);
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
