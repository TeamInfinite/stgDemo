package model.enemies;

import model.Enemy;
import model.EPath;

public class ESquare extends Enemy {
	
	// == constants ==
	public static final String IMG_ADDR = "/resources/mirror.png";
	public static final String PATH_ADDR = "src\\resources\\path\\test.txt";
	public static final int SCORE = 100;
	
	// == fields ==
	public ESquare() {
		super(IMG_ADDR, 1, SCORE);
		
		testSetting();
		
		hp = 1000;
	}
	
	private void testSetting() {
		
		
	}


	@Override
	public void move() {
		if(path.peek() != null) {
			EPath temp = path.poll();
			image.setLayoutX(temp.getX());
			image.setLayoutY(temp.getY());
		} else {
			hp = 0;
		}
	}

	@Override
	public int getScore() {
		return SCORE;
	}

	@Override
	public void setEPath() {
		setPath(EPath.load(PATH_ADDR));
		
	}

}
