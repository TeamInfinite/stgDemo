package model;

import javafx.scene.image.ImageView;

public abstract class GameComp {
	
	// == fields ==
	protected ImageView image;
	protected double scale;
	protected int hp = 1;
	
	// == constructor ==
	protected GameComp(String imgAddress, double scale) {
		image = new ImageView(imgAddress);
		this.scale = scale;
	}
	
	public GameComp(String imgAddress) {
		this(imgAddress, 1);
	}
	
	public void lostHp(int value) {
		hp -= value;
	}

	// == getters ==
	public ImageView getImageView() {
		return image;
	}
	
	public int getHp() {
		return hp;
	}
}
