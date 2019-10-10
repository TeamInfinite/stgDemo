package model;

import java.util.LinkedList;

import javafx.scene.image.ImageView;

public interface EnemyOld {
	
	ImageView getImageView();
	LinkedList<EPath> getPath();
	void move();
	int getScore();
	
}
