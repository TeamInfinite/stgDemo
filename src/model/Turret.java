package model;

import java.util.ArrayList;
import java.util.LinkedList;

import controller.Game;
import gui.GamePaneManager;

//[
public class Turret {
	
	private int fireTime;
	private int bulletType;
	private ArrayList<double[]> bulletsPos;
	
	public Turret(int fireTime, int bulletType, ArrayList<double[]> bulletsPos) {
		this.fireTime = fireTime;
		this.bulletType = bulletType;
		this.bulletsPos = bulletsPos;
	}
	
	public static void fire(Enemy source, Turret turret, GamePaneManager gamePane, Game game) {
		for(double[] pos : turret.getBulletsPos()) {
			EBullet e = null;
			switch(turret.getBulletType()) {
			case 1: 
				e = new EBullet(EBulletType.TEST_BULLET.getAddr(), pos[0], pos[1]);
				break;
			}
			
			e.getImageView().setLayoutX(source.getImageView().getLayoutX() + source.getFirePosX());
			e.getImageView().setLayoutY(source.getImageView().getLayoutY() + source.getFirePosY());
			game.geteBullets().add(e);
			gamePane.getPane().getChildren().add(e.getImageView());
		}
	}
	
	public static LinkedList<Turret> readFile(String addr) {
		
		return null;
	}
	
	public static Turret translate(String line) {
		return null;
	}
	
	public int getFireTime() {
		return fireTime;
	}

	public int getBulletType() {
		return bulletType;
	}

	public ArrayList<double[]> getBulletsPos() {
		return bulletsPos;
	}
}
