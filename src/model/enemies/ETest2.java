package model.enemies;

import java.util.ArrayList;

import model.Enemy;
import model.Turret;

public class ETest2 extends Enemy {
	
	public static final String IMG_ADDR = "/resources/mirror.png";
	public static final String PATH_ADDR = "src\\resources\\path\\test.txt";
	public static final int SCORE = 500;
	public static final double FIRE_POS_X = 25;
	public static final double FIRE_POS_Y = 40;

	public ETest2() {
		super(IMG_ADDR, 1, SCORE, null, PATH_ADDR);

		hp = 300;
		
		firePosX = FIRE_POS_X;
		firePosY = FIRE_POS_Y;
	}

	@Override
	public void setEPath() {
		
	}

	@Override
	public void setTurret() {
		ArrayList<double[]> temp = new ArrayList<>();
		double[] t1 = {2 , 0};
		temp.add(t1);
		
		for(int i = 0; i <= 20; i++) {
			turret.offer(new Turret(1, 5 + i * 5, 1, temp));
		}
	}

}

