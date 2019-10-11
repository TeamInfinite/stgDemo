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
		super(IMG_ADDR, 1, SCORE);
		
		testSetting();
		
		hp = 1000;
		
		firePosX = FIRE_POS_X;
		firePosY = FIRE_POS_Y;
	}
	
	private void testSetting() {
		
		
	}

	@Override
	public int getScore() {
		return SCORE;
	}

	@Override
	public void setEPath() {
		setPath(EPath.load(PATH_ADDR));
	}

	@Override
	public void setTurret() {
		
		ArrayList<double[]> temp = new ArrayList<>();
		double[] t1 = {0.8, 4};
		double[] t2 = {0, 4};
		double[] t3 = {-0.8, 4};
		temp.add(t1);
		temp.add(t2);
		temp.add(t3);
		
		for(int i = 0; i <= 20; i++) {
			turret.offer(new Turret(100 + i * 20, 1, temp));
		}
	}

}
