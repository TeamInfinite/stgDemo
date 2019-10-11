package controller;

import gui.Param;
import javafx.scene.image.Image;
import model.PlayerImpl;
import model.bullets.PBullet;

public class MainController {
	
	// == fields ==
	private Game game = new Game();
	
	private boolean isMovingUp;
	private boolean isMovingDown;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean isSlowlyMoving;
	private boolean isFiring;
	
	public MainController() {
		
	}
	
	// == move methods ==
	public void moveUp() {
		if(game.getPlayerImageView().getLayoutY() + game.getPlayerImageView().getTranslateY() > 0) {
			if(isSlowlyMoving) {
				game.getPlayerImageView().setLayoutY(game.getPlayerImageView().getLayoutY() - GameParam.SLOW_SPEED);
				game.getPlayerHitBox().setLayoutY(game.getPlayerHitBox().getLayoutY() - GameParam.SLOW_SPEED);
			} else {
				game.getPlayerImageView().setLayoutY(game.getPlayerImageView().getLayoutY() - GameParam.DEFAULT_SPEED);
				game.getPlayerHitBox().setLayoutY(game.getPlayerHitBox().getLayoutY() - GameParam.DEFAULT_SPEED);
			}
		}
	}
	
	public void moveDown() {
		if(game.getPlayerImageView().getLayoutY() + game.getPlayerImageView().getTranslateY() < Param.GAME_PANE_HEIGHT - game.getPlayerImageView().getImage().getHeight()) {
			if(isSlowlyMoving) {
				game.getPlayerImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + GameParam.SLOW_SPEED);
				game.getPlayerHitBox().setLayoutY(game.getPlayerHitBox().getLayoutY() + GameParam.SLOW_SPEED);
			} else {
				game.getPlayerImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + GameParam.DEFAULT_SPEED);
				game.getPlayerHitBox().setLayoutY(game.getPlayerHitBox().getLayoutY() + GameParam.DEFAULT_SPEED);
			}
		}
	}
	
	public void moveLeft() {
		game.getPlayerImageView().setImage(new Image(PlayerImpl.IMG_ADDR_LEFT));
		if(game.getPlayerImageView().getLayoutX() + game.getPlayerImageView().getTranslateX() > 0) {
			if(isSlowlyMoving) {
				game.getPlayerImageView().setLayoutX(game.getPlayerImageView().getLayoutX() - GameParam.SLOW_SPEED);
				game.getPlayerHitBox().setLayoutX(game.getPlayerHitBox().getLayoutX() - GameParam.SLOW_SPEED);
			} else {
				game.getPlayerImageView().setLayoutX(game.getPlayerImageView().getLayoutX() - GameParam.DEFAULT_SPEED);
				game.getPlayerHitBox().setLayoutX(game.getPlayerHitBox().getLayoutX() - GameParam.DEFAULT_SPEED);
			}
		}
	}
	
	public void moveRight() {
		game.getPlayerImageView().setImage(new Image(PlayerImpl.IMG_ADDR_RIGHT));
		if(game.getPlayerImageView().getLayoutX() + game.getPlayerImageView().getTranslateX() < Param.GAME_PANE_WIDTH - game.getPlayerImageView().getImage().getWidth()) {
			if(isSlowlyMoving) {
				game.getPlayerImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + GameParam.SLOW_SPEED);
				game.getPlayerHitBox().setLayoutX(game.getPlayerHitBox().getLayoutX() + GameParam.SLOW_SPEED);
			} else {
				game.getPlayerImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + GameParam.DEFAULT_SPEED);
				game.getPlayerHitBox().setLayoutX(game.getPlayerHitBox().getLayoutX() + GameParam.DEFAULT_SPEED);
			}
		}
	}
	
	public void fire() {
		if(isFiring) {
			PBullet pb = new PBullet();
			pb.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + 20);
			if(game.getTimer() % 2 == 0)
				pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + 18);
			else
				pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + 30);
			game.getBullets().add(pb);
		}
	}
	
	// == getters and setters ==
	public Game getGame() {
		return game;
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingDown() {
		return isMovingDown;
	}

	public void setMovingDown(boolean isMovingDown) {
		this.isMovingDown = isMovingDown;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
		
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean isSlowlyMoving() {
		return isSlowlyMoving;
	}

	public void setSlowlyMoving(boolean isSlowlyMoving) {
		this.isSlowlyMoving = isSlowlyMoving;
	}

	public boolean isFiring() {
		return isFiring;
	}

	public void setFiring(boolean isFiring) {
		this.isFiring = isFiring;
	}
}
