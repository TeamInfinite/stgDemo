package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class EBullet extends GameComp implements Suiciding {
	
	// == fields ==
	private double dx;
	private double dy;
	
	private Circle hitBox = new Circle(4);

	public EBullet(String imgAddress, double dx, double dy) {
		super(imgAddress);
		
		this.dx = dx;
		this.dy = dy;
		
		hitBox.setFill(Color.RED);
		
		hp = 200;
	}
	
	// == public methods ==
	public void move() {
		image.setLayoutX(image.getLayoutX() + dx);
		image.setLayoutY(image.getLayoutY() + dy);
		hitBox.setLayoutX(hitBox.getLayoutX() + dx);
		hitBox.setLayoutY(hitBox.getLayoutY() + dy);
	}

	@Override
	public void suicide() {
		hp = 0;
	}

	@Override
	public void suiciding() {
		hp--;
	}
	
	// == getters ==
	public Circle getHitBox() {
		return hitBox;
	}
}
