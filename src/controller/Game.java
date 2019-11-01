package controller;

import java.util.ArrayList;
import java.util.LinkedList;

import gui.GamePaneManager;
import gui.ScorePaneManager;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.Bullet;
import model.DialogLevel;
import model.EBullet;
import model.Player;
import model.Enemy;
import model.EnemySpawn;
import model.Level;
import model.PlayerImpl;
import model.TimeStop;

public class Game {
	
	// == fields ==
	private int score;
	private int life;
	private int lifePart;
	private int bomb = GameParam.INIT_BOMB_CAPACITY;
	private int power = 3;
	private int immunity;
	private int respawning = 0;
	private TimeStop timeStop = new TimeStop();
	
	private int timer = 0;
	private boolean running = false;
	private boolean inDialog = false;
	
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private ArrayList<EBullet> eBullets = new ArrayList<>();
	private LinkedList<Level> level = new LinkedList<>();
	private Level next;
	
	public Game() {
		life = GameParam.DEFAULT_LIFE;
		
		initPlayer();
		initEnemy();
		test();
		
		if(level.peek() != null) {
			next = level.poll();
		}
		
		if(GameParam.IMMUNITY_MODE) {
			immunity = -1;
		}
	}
	
	private void test() {
		level.offer(new DialogLevel(20, 20, "/resources/testImage.jpg", "啊我是驴", 100));
		level.offer(new DialogLevel(20, 20, "/resources/testImage.jpg", "小驴子冲击！", 0));
		
		level.offer(new EnemySpawn(200, 1, "src\\resources\\path\\test3.txt"));
		// level.offer(new EnemySpawn(1201, 3, "src\\resources\\path\\test5.txt"));
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
			if(next instanceof DialogLevel) {
				while(next instanceof DialogLevel) {
					gamePane.addDialog(((DialogLevel) next).getX(), ((DialogLevel) next).getY(), ((DialogLevel) next).getImageAddr(), ((DialogLevel) next).getText());
					if(level.peek() != null) {
						next = level.poll();
					}
				}
				running = false;
				inDialog = true;
				gamePane.showDialog();
				
			} else if(next instanceof EnemySpawn){
				EnemySpawn.spawn(((EnemySpawn) next).getEnemyType(), gamePane, this, ((EnemySpawn) next).getAddr());
				if(level.peek() != null) {
					next = level.poll();
				}
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
		running = true;
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
			running = false;
			return GameParam.NO_LIFE_LEFT;
		}
		
		respawning = GameParam.RESPAWNING_TIME;
		clearEnemyBullets();
		power = 1;
		
		return GameParam.LIFE_LEFT;
	}
	
	public void respawning() {
		respawning --;
	}
	
	public void clearEnemyBullets() {
		for(EBullet eb : eBullets) {
			eb.suicide();
		}
	}
	
	public void bomb() {
		if(bomb > 0) {
			bomb--;
			clearEnemyBullets();
			for(Enemy e : enemies) {
				e.lostHp(GameParam.BOMB_DAMAGE);
			}
		}
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
		return running;
	}

	public void setRunning(boolean isRunning) {
		this.running = isRunning;
	}

	public int getRespawning() {
		return respawning;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public boolean isInDialog() {
		return inDialog;
	}

	public void setInDialog(boolean inDialog) {
		this.inDialog = inDialog;
	}
}
