package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import controller.Game;
import gui.GamePaneManager;
import model.enemies.ESquare;
import model.enemies.ETest;
import model.enemies.ETest2;

// 
public class EnemySpawn implements Level {
	
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
		case 2: 
			e = new ETest();
			break;
		case 3: 
			e = new ETest2();
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
		if(addr == null) return new LinkedList<>();
		
		LinkedList<EnemySpawn> level = new LinkedList<>();
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(addr));
			while(scan.hasNext()) {
				String line = scan.nextLine();
				
				level.add(translate());
			}
		} catch(FileNotFoundException e) {
			
		} finally {
			scan.close();
		}
		
		return level;
	}
	
	// TODO implement later
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
