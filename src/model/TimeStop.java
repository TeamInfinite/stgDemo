package model;

import controller.GameParam;

public class TimeStop {
	
	// == fields ==
	private boolean stoped = false;
	private int timer;
	
	// == methods ==
	public boolean isStoped() {
		return stoped;
	}
	
	public void moveTimer() {
		timer--;
		System.out.println(timer);
		if(timer == 0) {
			stoped = false;
		}
	}
	
	public void timeStop() {
		if(!stoped) {
			timer = GameParam.TIME_STOP_LENGTH;
			stoped = true;
		}
	}

}
