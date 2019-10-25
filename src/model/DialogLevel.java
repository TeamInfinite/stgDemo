package model;

public class DialogLevel implements Level {
	private double x;
	private double y;
	private String imageAddr;
	private String text;
	private int time;
	
	public DialogLevel(double x, double y, String imageAddr, String text, int time) {
		this.x = x;
		this.y = y;
		this.imageAddr = imageAddr;
		this.text = text;
		this.time = time;
	}

	public String getImageAddr() {
		return imageAddr;
	}

	public String getText() {
		return text;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public int getSpawnTime() {
		// TODO Auto-generated method stub
		return time;
	}
}