package gui.storyPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public enum Character {
	
	Test1("/resources/character/mikutest.png", "Miku", Color.BLUE),
	Test2("/resources/character/rintest.png", "Rin", Color.YELLOW);

	// == fields ==
	private ImageView img = new ImageView();
	private final String name;
	private final Color textColor;
	
	private Character(String imgAddr, String name, Color textColor) {
		img.setImage(new Image(imgAddr));
		img.setFitHeight(400);
		img.setFitWidth(250);
		
		this.name = name;
		this.textColor = textColor;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return textColor;
	}
	
	public ImageView getImg() {
		return img;
	}
	
	public static Character getCharacter(String name) {
		switch(name) {
		case "Miku":
			return Test1;
		case "Rin":
			return Test2;
		}
		
		return Test1;
	}
}
