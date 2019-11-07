package model.events;

import controller.GameEvent;
import gui.LoadingScreen;
import gui.SceneManager;
import gui.ViewManager;

public class TestEvent1 extends GameEvent {

	public TestEvent1(SceneManager sm) {
		super(sm);
	}

	@Override
	public void nextMove() {
		sm.setRoot(new LoadingScreen(new ViewManager(sm, new TestEvent1(sm))));
	}

}
