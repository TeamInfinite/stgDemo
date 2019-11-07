package model.enemies;

import java.util.ArrayList;

import model.Boss;
import model.EPath;
import model.Turret;

public class BossMirror extends Boss {
	
	// == constants ==
	public static final String IMG_ADDR = "/resources/mirror.png";
	public static final String PATH_ADDR = "src\\resources\\path\\test3.txt";
	public static final int SCORE = 100;
	public static final double FIRE_POS_X = 25;
	public static final double FIRE_POS_Y = 40;

	public BossMirror() {
		super(IMG_ADDR, 1, SCORE);
	}

	@Override
	public void setPhases() {
		phases.offer(new Phase(7200, 2000));
		phases.peek().phasePath = EPath.load(PATH_ADDR);
		phases.peek().phaseTurret.clear();
		for(int i = 0; i <= 6000; i++) {
			ArrayList<double[]> temp1 = new ArrayList<>();
			for(int j = 0; j < 8; j++) {
				temp1.add(new double[]{3 * Math.sin(j * Math.toRadians(45) + Math.pow(i,2) * Math.toRadians(0.1)), - 3 * Math.cos(j * Math.toRadians(45) + Math.pow(i,2) * Math.toRadians(0.1))});
			}
			phases.peek().phaseTurret.offer(new Turret(1, 20 + i * 3, 1, temp1));
		}
	}

	@Override
	public void setFirePos() {
		firePosX = FIRE_POS_X;
		firePosY = FIRE_POS_Y;
	}

}
