package model;

public class EBullet extends GameComp implements Suiciding {
	
	// == fields ==
	private double dx;
	private double dy;

	public EBullet(String imgAddress, double dx, double dy) {
		super(imgAddress);
		
		this.dx = dx;
		this.dy = dy;
		
		hp = 200;
	}
	
	// == public methods ==
	public void move() {
		image.setLayoutX(image.getLayoutX() + dx);
		image.setLayoutY(image.getLayoutY() + dy);
	}

	@Override
	public void suicide() {
		hp = 0;
	}

	@Override
	public void suiciding() {
		hp--;
	}
}
