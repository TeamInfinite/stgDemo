package controller;

import java.util.ArrayList;
import java.util.LinkedList;

import gui.GamePaneManager;
import gui.ScorePaneManager;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.Bullet;
import model.EBullet;
import model.Player;
import model.Enemy;
import model.EnemySpawn;
import model.PlayerImpl;
import model.TimeStop;

public class Game {
	
	// == fields ==
	private int score;
	private int life;
	private int lifePart;
	private int immunity;
	private int respawning = 0;
	private TimeStop timeStop = new TimeStop();
	
	private int timer = 0;
	private boolean isRunning = false;
	
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private ArrayList<EBullet> eBullets = new ArrayList<>();
	private LinkedList<EnemySpawn> level = new LinkedList<>();
	private EnemySpawn next;
	
	public Game() {
		life = GameParam.DEFAULT_LIFE;
		
		initPlayer();
		initEnemy();
		test();
		
		if(level.peek() != null) {
			next = level.poll();
		}
	}
	
	private void test() {
		for(int i = 0 ; i < 5; i++) {
			level.offer(new EnemySpawn(100 + 50 * i, 1, "default"));
			level.offer(new EnemySpawn(125 + 50 * i, 1, "src\\resources\\path\\test2.txt"));
		}
	}

	// == init methods ==
	private void initPlayer() {
		player = new PlayerImpl();
	}
	
	private void initEnemy() {	
		
	}
	
	// == public methods ==
	public void enemySpawn(GamePaneManager gamePane) {
		if(next.getSpawnTime() == timer) {
			EnemySpawn.spawn(next.getEnemyType(), gamePane, this, next.getAddr());
		
			if(level.peek() != null) {
				next = level.poll();
			}
		}
	}
	
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
		
		respawning = GameParam.RESPAWNING_TIME;
		
		return GameParam.LIFE_LEFT;
	}
	
	public void respawning() {
		respawning --;
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

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public int getRespawning() {
		return respawning;
	}
}
