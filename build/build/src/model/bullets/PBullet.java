package model.bullets;

import model.Bullet;
import model.BulletOld;
import model.GameComp;

public class PBullet extends Bullet {
	
	// == constants ==
	public static final String IMG_ADDR = "/resources/Item-1.png";
	
	public final static double BULLET_SPEED = 25;
	public final static int POWER = 2;
	
	public PBullet() {
		super(IMG_ADDR, POWER);

		hp = 120;
	}
}
