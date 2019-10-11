package controller;

import java.util.ArrayList;

import gui.ScorePaneManager;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.Bullet;
import model.EBullet;
import model.Player;
import model.Enemy;
import model.PlayerImpl;
import model.TimeStop;
import model.enemies.ESquare;

public class Game {
	
	// == fields ==
	private int score;
	private int life;
	private int lifePart;
	private int immunity;
	private TimeStop timeStop = new TimeStop();
	
	private int timer = 0;
	private boolean isRunning = false;
	
	private boolean isGameOver;
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private ArrayList<EBullet> eBullets = new ArrayList<>();
	
	public Game() {
		life = GameParam.DEFAULT_LIFE;
		
		initPlayer();
		initEnemy();
	}

	// == init methods ==
	private void initPlayer() {
		player = new PlayerImpl();
	}
	
	private void initEnemy() {
		ESquare sqr = new ESquare();
		sqr.getImageView().setTranslateX(0);
		sqr.getImageView().setTranslateY(0);
		
		enemies.add(sqr);
	}
	
	// == public methods ==
	public void timeStop() {
		timeStop.timeStop();
	}
	
	public void moveTimeStopTimer() {
		timeStop.moveTimer();
	}
	
	public void start() {
		isRunning = true;
	}
	
	public void resetTimer() {
		timer = 0;
	}
	
	public void moveTimer() {
		timer++;
		
		if(timer == GameParam.RESET_POINT) {
			resetTimer();
		}
		
		if(immunity > 0) {
			if(immunity % 10 >= 5) {
				getPlayerImageView().setOpacity(0);
			} else {
				getPlayerImageView().setOpacity(100);
			}
			
			immunity --;
		}
	}
	
	public void score(int score) {
		this.score += score;
	}
	
	/** Will be called when the player dies.
	 *  @return if game status, which can be found in GameParam */
	public int playerDead(ScorePaneManager scorePane) {
		life--;
		scorePane.refreshLife();
		
		if(life == 0) {
			return GameParam.NO_LIFE_LEFT;
		}
		
		return GameParam.LIFE_LEFT;
	}
	
	
	// == getters ==
	public ImageView getPlayerImageView() {
		return player.getImageView();
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public long getTimer() {
		return timer;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isTimeStoped() {
		return timeStop.isStoped();
	}

	public ArrayList<EBullet> geteBullets() {
		return eBullets;
	}

	public int getLife() {
		return life;
	}
	
	public Rectangle getPlayerHitBox() {
		return player.getHitBox();
	}

	public int getImmunity() {
		return immunity;
	}

	public void setImmunity(int immunity) {
		this.immunity = immunity;
	}
}
