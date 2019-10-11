package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import controller.Game;
import gui.GamePaneManager;
import model.enemies.ESquare;

// 
public class EnemySpawn {
	
	private int spawnTime;
	private int enemyType;
	private String addr;

	public EnemySpawn(int spawnTime, int enemyType, String addr) {
		this.spawnTime = spawnTime;
		this.enemyType = enemyType;
		
		this.addr = addr;
	}
	
	public static void spawn(int enemyType, GamePaneManager gamePane, Game game, String addr) {
		Enemy e = null;
		switch(enemyType) {
		case 1: 
			e = new ESquare();
			break;
		default :
			break;
		}
		
		if(!addr.equals("default")) {
			e.setEPath(EPath.load(addr));
		}
		
		game.getEnemies().add(e);
		gamePane.getPane().getChildren().add(e.image);
	}
	
	public static LinkedList<EnemySpawn> loadFile(String addr) {
		LinkedList<EnemySpawn> level = new LinkedList<>();
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(addr));
		} catch(FileNotFoundException e) {
			while(scan.hasNext()) {
				String line = scan.nextLine();
				
				level.add(translate());
			}
		} finally {
			scan.close();
		}
		
		return level;
	}
	
	public static EnemySpawn translate() {
		return null;
	}
	
	// == getters and setters ==
	public int getSpawnTime() {
		return spawnTime;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public String getAddr() {
		return addr;
	}
	
}
