package controller;

import gui.PaneParam;
import javafx.scene.image.Image;
import model.Player;
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
		if(game.getPlayerImageView().getLayoutY() + game.getPlayerImageView().getTranslateY() < PaneParam.GAME_PANE_HEIGHT - game.getPlayerImageView().getImage().getHeight()) {
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
		if(game.getPlayerImageView().getLayoutX() + game.getPlayerImageView().getTranslateX() < PaneParam.GAME_PANE_WIDTH - game.getPlayerImageView().getImage().getWidth()) {
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
		if(game.getRespawning() == 0 && isFiring) {
			if(game.getPower() == 1) {
				PBullet pb = new PBullet();
				pb.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + Player.FIRE_POS_POWER1_X);
				if(game.getTimer() % 2 == 0)
					pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y);
				else
					pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y + 12);
				game.getBullets().add(pb);
			} else if(game.getPower() == 2) {
				PBullet pb = new PBullet();
				PBullet pb2 = new PBullet();
				pb.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + Player.FIRE_POS_POWER2_X1);
				pb2.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + Player.FIRE_POS_POWER2_X2);
				if(game.getTimer() % 2 == 0) {
					pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y);
					pb2.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y);
				} else {
					pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y + 12);
					pb2.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y + 12);
				}
				game.getBullets().add(pb);
				game.getBullets().add(pb2);
			} else if(game.getPower() == 3) {
				PBullet pb = new PBullet();
				PBullet pb2 = new PBullet();
				PBullet pb3 = new PBullet();
				pb.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + Player.FIRE_POS_POWER3_X1);
				pb2.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + Player.FIRE_POS_POWER3_X2);
				pb3.getImageView().setLayoutX(game.getPlayerImageView().getLayoutX() + Player.FIRE_POS_POWER3_X3);
				if(game.getTimer() % 2 == 0) {
					pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y);
					pb2.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y);
					pb3.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y);
				} else {
					pb.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y + 12);
					pb2.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y + 12);
					pb3.getImageView().setLayoutY(game.getPlayerImageView().getLayoutY() + Player.FIRE_POS_POWER_Y + 12);
				}
				game.getBullets().add(pb);
				game.getBullets().add(pb2);
				game.getBullets().add(pb3);
			}
			
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
