package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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
			e.getHitBox().setLayoutX(source.getImageView().getLayoutX() + source.getFirePosX() + 8.2);
			e.getHitBox().setLayoutY(source.getImageView().getLayoutY() + source.getFirePosY() + 9);
			game.geteBullets().add(e);
			gamePane.getPane().getChildren().addAll(e.getImageView(), e.getHitBox());
		}
	}
	
	public static LinkedList<Turret> readFile(String addr) {
		if(addr == null) return new LinkedList<>();
		
		LinkedList<Turret> turret = new LinkedList<>();
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(addr));
			
			Turret temp = translate();
			turret.add(temp);
		} catch(FileNotFoundException e) {
			
		} finally {
			scan.close();
		}
		
		return turret;
	}
	
	// TODO implement later
	public static Turret translate() {
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
