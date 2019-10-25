package model;

import javafx.scene.shape.Rectangle;

public abstract class Player extends GameComp {
	
	// == constants ==
	public static final double FIRE_POS_POWER_Y = 8;
	public static final double FIRE_POS_POWER1_X = 10;
	public static final double FIRE_POS_POWER2_X1 = 5;
	public static final double FIRE_POS_POWER2_X2 = 15;
	public static final double FIRE_POS_POWER3_X1 = 0;
	public static final double FIRE_POS_POWER3_X2 = 10;
	public static final double FIRE_POS_POWER3_X3 = 20;
	
	// == fields ==
	protected Rectangle hitBox = new Rectangle(2, 2);

	public Player(String imgAddress) {
		super(imgAddress);
	}
	
	// == getters and setters ==
	public Rectangle getHitBox() {
		return hitBox;
	}
}
