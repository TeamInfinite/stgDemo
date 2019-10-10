package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Player extends GameComp {
	
	// == fields ==
	protected ImageView hitBox = new ImageView();

	public Player(String imgAddress, String hitBoxAddr) {
		super(imgAddress);
		
		hitBox.setImage(new Image(hitBoxAddr));
	}
	
	public Player(String imgAddress) {
		super(imgAddress);
	}
	
	// == getters ==
	public ImageView getHitBox() {
		return hitBox;
	}
	
}
