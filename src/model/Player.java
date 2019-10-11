package model;

import javafx.scene.shape.Rectangle;

public abstract class Player extends GameComp {
	
	// == fields ==
	private Rectangle hitBox = new Rectangle(0, 0, 2, 2);

	public Player(String imgAddress) {
		super(imgAddress);
		
		hitBox.setLayoutX(image.getLayoutX() + 5);
		hitBox.setLayoutY(image.getLayoutY() + 5);
	}
	
	// == getters ==
	public Rectangle getHitBox() {
		return hitBox;
	}
	
}
