package model;

import controller.Game;
import gui.GamePaneManager;
import model.enemies.BossMirror;

public class BossSpawn implements Level {
	
	private int spawnTime;
	private int bossType;
	
	public BossSpawn(int spawnTime, int bossType) {
		this.spawnTime = spawnTime;
		this.bossType = bossType;
	}
	
	public static void spawn(int enemyType, GamePaneManager gamePane, Game game) {
		Boss e = null;
		switch(enemyType) {
		case 1: 
			e = new BossMirror();
			break;
		default :
			break;
		}
		
		
		
		game.setBoss(e);
		gamePane.getPane().getChildren().add(e.image);
	}

	@Override
	public int getSpawnTime() {
		return spawnTime;
	}

	public int getBossType() {
		return bossType;
	}
}
