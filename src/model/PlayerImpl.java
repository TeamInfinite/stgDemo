package model;

import javafx.scene.image.ImageView;

public class PlayerImpl extends Player {
	
	// == constants ==
	public final static String IMG_ADDR = "/resources/Koishi1.png";
	public final static String IMG_ADDR_LEFT = "/resources/Koishi2.png";
	public final static String IMG_ADDR_RIGHT = "/resources/Koishi3.png";
	// == fields ==
	
	// == constructor ==
	public PlayerImpl() {
		super(IMG_ADDR);
		
		hp = 1;
	}

	@Override
	public ImageView getHitBox() {
		return null;
	}

}
