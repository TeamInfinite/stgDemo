package controller;

import gui.SceneManager;

public abstract class GameEvent {
	
	// == fields ==
	protected SceneManager sm;
	
	public GameEvent(SceneManager sm) {
		this.sm = sm;
	}

	public abstract void nextMove();
}
