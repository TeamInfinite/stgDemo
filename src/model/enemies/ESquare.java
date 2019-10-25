package model.enemies;

import model.Enemy;
import model.Turret;

import java.util.ArrayList;

import model.EPath;

public class ESquare extends Enemy {
	
	// == constants ==
	public static final String IMG_ADDR = "/resources/mirror.png";
	public static final String PATH_ADDR = "src\\resources\\path\\test.txt";
	public static final int SCORE = 100;
	public static final double FIRE_POS_X = 25;
	public static final double FIRE_POS_Y = 40;
	
	// == fields ==
	public ESquare() {
		// TODO add turret
		super(IMG_ADDR, 1, SCORE, null, PATH_ADDR);

		hp = 20;
		
		firePosX = FIRE_POS_X;
		firePosY = FIRE_POS_Y;
	}

	@Override
	public int getScore() {
		return SCORE;
	}

	@Override
	public void setEPath() {
		setPath(EPath.load(PATH_ADDR));
	}
	
	public void setEPath(String addr) {
		setPath(EPath.load(addr));
	}

	@Override
	public void setTurret() {
		
		ArrayList<double[]> temp = new ArrayList<>();
		ArrayList<double[]> temp2 = new ArrayList<>();
		ArrayList<double[]> temp3 = new ArrayList<>();
		ArrayList<double[]> temp4 = new ArrayList<>();
		
		double[] t1 = {0.8, 4};
		double[] t2 = {0.6, 4};
		double[] t3 = {0.4, 4};
		double[] t4 = {0.2, 4};
		double[] t5 = {0, 4};
		double[] t6 = {-0.2, 4};
		double[] t7 = {-0.4, 4};
		double[] t8 = {-0.6, 4};
		double[] t9 = {-0.8, 4};
		temp.add(t1);
		temp.add(t5);
		temp.add(t9);
		
		temp2.add(t2);
		temp2.add(t5);
		temp2.add(t8);
		
		temp3.add(t3);
		temp3.add(t5);
		temp3.add(t7);
		
		temp4.add(t4);
		temp4.add(t5);
		temp4.add(t6);
		
		for(int i = 0; i <= 60; i++) {
			turret.offer(new Turret(1, 20 + i * 35, 1, temp));
			turret.offer(new Turret(1, 25 + i * 35, 1, temp2));
			turret.offer(new Turret(1, 30 + i * 35, 1, temp3));
			turret.offer(new Turret(1, 35 + i * 35, 1, temp4));
			turret.offer(new Turret(1, 40 + i * 35, 1, temp3));
			turret.offer(new Turret(1, 45 + i * 35, 1, temp2));
			turret.offer(new Turret(1, 50 + i * 35, 1, temp));
		}
	}

}
