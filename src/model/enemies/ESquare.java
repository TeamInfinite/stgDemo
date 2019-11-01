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
		for(int i = 0; i <= 360; i++) {
			ArrayList<double[]> temp1 = new ArrayList<>();
			for(int j = 0; j < 8; j++) {
				temp1.add(new double[]{3 * Math.sin(j * Math.toRadians(45) + Math.pow(i,2) * Math.toRadians(0.1)), - 3 * Math.cos(j * Math.toRadians(45) + Math.pow(i,2) * Math.toRadians(0.1))});
			}
			turret.offer(new Turret(1, 20 + i * 3, 1, temp1));
		}
	}
}
