package model.enemies;

import java.util.ArrayList;

import model.Enemy;
import model.Turret;

public class ETest extends Enemy {
	
	public static final String IMG_ADDR = "/resources/mirror.png";
	public static final String PATH_ADDR = "src\\resources\\path\\test.txt";
	public static final int SCORE = 500;
	public static final double FIRE_POS_X = 25;
	public static final double FIRE_POS_Y = 40;

	public ETest() {
		super(IMG_ADDR, 1, SCORE, null, PATH_ADDR);

		hp = 2000;
		
		firePosX = FIRE_POS_X;
		firePosY = FIRE_POS_Y;
	}

	@Override
	public void setEPath() {
		
	}

	@Override
	public void setTurret() {
		ArrayList<double[]> temp = new ArrayList<>();
		ArrayList<double[]> temp2 = new ArrayList<>();
		ArrayList<double[]> temp3 = new ArrayList<>();
		ArrayList<double[]> temp4 = new ArrayList<>();
		ArrayList<double[]> temp5 = new ArrayList<>();
		ArrayList<double[]> temp6 = new ArrayList<>();
		ArrayList<double[]> temp7 = new ArrayList<>();
		
		double[] tn2 = {2.1, 4};
		double[] tn1 = {1.8, 4};
		double[] t0 = {1.5, 4};
		double[] t1 = {1.2, 4};
		double[] t2 = {0.9, 4};
		double[] t3 = {0.6, 4};
		double[] t4 = {0.3, 4};
		double[] t5 = {0, 4};
		double[] t6 = {-0.3, 4};
		double[] t7 = {-0.6, 4};
		double[] t8 = {-0.9, 4};
		double[] t9 = {-1.2, 4};
		double[] t10 = {-1.5, 4};
		double[] t11 = {-1.8, 4};
		double[] t12 = {-2.1, 4};
		
		temp.add(tn2);
		temp.add(t5);
		temp.add(t12);
		
		temp2.add(tn1);
		temp2.add(t5);
		temp2.add(t11);
		
		temp3.add(t0);
		temp3.add(t5);
		temp3.add(t10);
		
		temp4.add(t1);
		temp4.add(t5);
		temp4.add(t9);
		
		temp5.add(t2);
		temp5.add(t5);
		temp5.add(t8);
		
		temp6.add(t3);
		temp6.add(t5);
		temp6.add(t7);
		
		temp7.add(t4);
		temp7.add(t5);
		temp7.add(t6);
		
		for(int i = 0; i <= 60; i++) {
			turret.offer(new Turret(1, 20 + i * 65, 1, temp));
			turret.offer(new Turret(1, 25 + i * 65, 1, temp2));
			turret.offer(new Turret(1, 30 + i * 65, 1, temp3));
			turret.offer(new Turret(1, 35 + i * 65, 1, temp4));
			turret.offer(new Turret(1, 40 + i * 65, 1, temp5));
			turret.offer(new Turret(1, 45 + i * 65, 1, temp6));
			turret.offer(new Turret(1, 50 + i * 65, 1, temp7));
			turret.offer(new Turret(1, 55 + i * 65, 1, temp6));
			turret.offer(new Turret(1, 60 + i * 65, 1, temp5));
			turret.offer(new Turret(1, 65 + i * 65, 1, temp4));
			turret.offer(new Turret(1, 70 + i * 65, 1, temp3));
			turret.offer(new Turret(1, 75 + i * 65, 1, temp2));
			turret.offer(new Turret(1, 80 + i * 65, 1, temp));
		}
	}

}
