package model;

public class Turret {
	
	private Enemy source;
	private int fireTime;
	private int bulletType;
	
	public Turret(Enemy source, int fireTime, int bulletType) {
		this.source = source;
		this.fireTime = fireTime;
		this.bulletType = bulletType;
	}
	
	
}
