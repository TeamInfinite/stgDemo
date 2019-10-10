package model;

public class EBullet extends GameComp {
	
	// == fields ==
	private double dx;
	private double dy;

	public EBullet(String imgAddress) {
		super(imgAddress);
		// TODO Auto-generated constructor stub
	}
	
	// == public methods ==
	public void move() {
		image.setLayoutX(image.getLayoutX() + dx);
		image.setLayoutY(image.getLayoutY() + dy);
	}
	
}
