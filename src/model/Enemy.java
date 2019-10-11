package model;

import java.util.LinkedList;

import controller.Game;
import gui.GamePaneManager;

public abstract class Enemy extends GameComp {
	
	// == fields ==
	protected int score;
	protected int timer;
	protected LinkedList<EPath> path = new LinkedList<>();
	protected LinkedList<Turret> turret = new LinkedList<>();
	protected Turret curr;
	protected double firePosX;
	protected double firePosY;
	
	public Enemy(String imgAddress, double scale, int score) {
		super(imgAddress, scale);
		
		this.score = score;
		setEPath();
		setTurret();
		
		if(turret.peek() != null) {
			curr = turret.poll();
		}
	}
	
	public void move() {
		timer();
		
		if(path.peek() != null) {
			EPath temp = path.poll();
			image.setLayoutX(temp.getX());
			image.setLayoutY(temp.getY());
		} else {
			hp = -10;
		}
	}
	
	public void fire(GamePaneManager gamePane, Game game) {
		if(timer == curr.getFireTime()) {
			Turret.fire(this, curr, gamePane, game);
			
			if(turret.peek() != null) {
				curr = turret.poll();
			}
		}
	}
	
	public void timer() {
		timer++;
		
		if(timer == EnemyParam.RESET_POINT) {
			timer = 0;
		}
	}
	
	public void setEPath(LinkedList<EPath> path) {
		this.path = path;
	}
	
	// == setters and getters ==
	public void setPath(LinkedList<EPath> path) {
		this.path = path;
	}
	
	public LinkedList<Turret> getTurret() {
		return turret;
	}

	public int getScore() {
		return score;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public double getFirePosX() {
		return firePosX;
	}

	public double getFirePosY() {
		return firePosY;
	}

	abstract public void setEPath();
	abstract public void setTurret();
}
