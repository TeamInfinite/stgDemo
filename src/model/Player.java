package model;

import javafx.scene.shape.Rectangle;

public abstract class Player extends GameComp {
	
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
