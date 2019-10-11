package model;

public abstract class Bullet extends GameComp implements Suiciding{

	// == fields ==
	protected int power;
	
	protected Bullet(String imgAddress, double scale, int power) {
		super(imgAddress, scale);
		
		this.power = power;
	}
	
	protected Bullet(String imgAddress, int power) {
		this(imgAddress, 1, power);
	}
	
	public void suiciding() {
		hp--;
	}

	public void suicide() {
		hp = 0;
	}
	
	// == getters ==
	public int getPower() {
		return power;
	}

}
