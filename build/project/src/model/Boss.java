package model;

import java.util.LinkedList;

import controller.Game;
import gui.GamePaneManager;

public abstract class Boss extends Enemy {
	
	public class Phase {
		// == fields ==
		private int elapseTime;
		private int phaseHp;
		public LinkedList<EPath> phasePath = new LinkedList<EPath>();
		public LinkedList<Turret> phaseTurret = new LinkedList<>();
		
		public Phase(int elapseTime, int phaseHp, String phasePathAddr, String phaseTurretAddr) {
			this.elapseTime = elapseTime;
			this.phaseHp = phaseHp;
			EPath.load(phasePathAddr);
			Turret.readFile(phaseTurretAddr);
		}
		
		public Phase(int elapseTime, int phaseHp) {
			this.elapseTime = elapseTime;
			this.phaseHp = phaseHp;
		}
	}
	
	// == fields ==
	protected int score;
	protected int timer;
	protected LinkedList<Phase> phases = new LinkedList<Phase>();
	protected double firePosX;
	protected double firePosY;
	protected Phase currPhase;
	protected LinkedList<EPath> currPath = new LinkedList<>();
	protected LinkedList<Turret> currTurret = new LinkedList<>();
	protected Turret curr;
	protected boolean dead = false;

	protected Boss(String imageAddr, double scale, int score) {
		super(imageAddr, scale, score, null, null);
		
		setPhases();
		setFirePos();
	}
	
	public void timer() {
		timer++;
		
		if(--currPhase.elapseTime == 1) {
			currPhase.phaseHp = -10;
		}
	}
	
	public int nextPhase() {
		if(currPhase == null || currPhase.phaseHp <= 0) {
			if(phases.peek() != null) {
				currPhase = phases.poll();
				
				currPath = new LinkedList<EPath>(currPhase.phasePath);
				currTurret = new LinkedList<Turret>(currPhase.phaseTurret);
				curr = currTurret.poll();
			} else {
				dead = true;
			}
			
			if(currPhase.phaseHp <= 0 && currPhase.phaseHp != -10) {
				return 1000;
			}
		}
		
		return 0;
	}
	
	public void move() {
		timer();
		
		if(currPath.peek() == null) {
			currPath = new LinkedList<EPath>(currPhase.phasePath);
		}
		
		EPath temp = currPath.poll();
		image.setLayoutX(temp.getX());
		image.setLayoutY(temp.getY());
		
	}
	
	public void fire(GamePaneManager gamePane, Game game) {
		if(timer == curr.getFireTime()) {
			Turret.fire(this, curr, gamePane, game);
			
			if(currTurret.peek() != null) {
				curr = currTurret.poll();
			}
		}
	}
	
	@Override
	public void lostHp(int damage) {
		currPhase.phaseHp -= damage;
	}
	
	public void setEPath() {}
	
	public void setTurret() {}

	abstract public void setPhases();
	abstract public void setFirePos();
	
	public boolean isDead() {
		return dead;
	}

	public double getFirePosX() {
		return firePosX;
	}

	public double getFirePosY() {
		return firePosY;
	}
	
	public int getPhaseHp() {
		return currPhase.phaseHp;
	}
	
	public int getPhaseElapseTime() {
		return currPhase.elapseTime / 60;
	}
}
