package model;

import java.util.LinkedList;

public abstract class Enemy extends GameComp {
	
	// == fields ==
	protected int score;
	protected int timer;
	protected LinkedList<EPath> path = new LinkedList<>();
	
	public Enemy(String imgAddress, double scale, int score) {
		super(imgAddress, scale);
		
		this.score = score;
		setEPath();
	}
	
	public void move() {
		while(path.peek() != null) {
			EPath temp = path.poll();
			image.setLayoutX(temp.getX());
			image.setLayoutY(temp.getY());
		}
	}
	
	public void timer() {
		timer++;
		
		if(timer == EnemyParam.RESET_POINT) {
			timer = 0;
		}
	}
	
	// == setters and getters ==
	public void setPath(LinkedList<EPath> path) {
		this.path = path;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTimer() {
		return timer;
	}
	
	abstract public void setEPath();
}
