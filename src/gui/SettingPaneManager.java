package gui;

import java.util.ArrayList;

import controller.GameSetting;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Loading;

public class SettingPaneManager implements Loading {
	
	private abstract class SettingMenu extends AnchorPane{
		protected Rectangle back;
		protected Label name;
		protected Label option;
		
		protected SettingMenu(String name, String option) {
			this.name = new Label(name);
			this.option = new Label(option);
			back = new Rectangle(800, 30);
			
			getChildren().addAll(back, this.name, this.option);
			
			back.setStroke(Color.TRANSPARENT);
			back.setFill(Color.BLUE);
			back.setOpacity(0.1);
			back.setLayoutX(50);
			back.setLayoutY(-5);
			
			this.name.setLayoutX(85);
			this.name.setLayoutY(2);
			
			this.option.setLayoutX(705);
			this.option.setLayoutY(2);
		}
		
		public void selected() {
			back.setOpacity(0.3);
			option.setText("< " + option.getText() + " >");
		}
		
		public void deselected() {
			back.setOpacity(0.1);
			String str = option.getText();
			option.setText(str.substring(2, str.length() - 2));
		}
		
		abstract public void change();
	}
	
	// == fields ==
	private AnchorPane pane = new AnchorPane();;
	private ImageView back;
	// TODO change rect to imgview
	private Rectangle rect;
	private VBox contents;
	private Label title;
	
	private ArrayList<SettingMenu> choices;
	private int curr = 0;
	private SettingMenu fullScreen;
	private SettingMenu bulletTransparency;
	
	private SceneManager sm;
	
	public SettingPaneManager(SceneManager sm) {
		this.sm = sm;
	}

	@Override
	public void loading() {
		pane = new AnchorPane();
		back = new ImageView();
		rect = new Rectangle(800, 650);
		contents = new VBox(2.5);
		title = new Label("Game Setting");
		
		choices = new ArrayList<>();
		// TODO add more options
		fullScreen = new SettingMenu("Full Screen Mode", (GameSetting.fullScreen ? "Full Screen" : "Window")) {
			@Override
			public void change() {
				option.setText((option.getText().equals("< Full Screen >") ? "< Window >" : "< Full Screen >"));
			}
		};
		bulletTransparency = new SettingMenu("Bullet Transparency", GameSetting.bulletTransparency + "") {
			@Override
			public void change() {
				switch(option.getText()) {
				case "< 0.5 >":
					option.setText("< " + 0.7 + " >");
					break;
				case "< 0.7 >":
					option.setText("< " + 0.2 + " >");
					break;
				case "< 0.2 >":
					option.setText("< " + 0.5 + " >");
					break;
				}
			}
		};
		choices.add(fullScreen);
		choices.add(bulletTransparency);
		
		contents.getChildren().addAll(fullScreen, bulletTransparency);
		
		pane.getChildren().addAll(back, title, rect, contents);
		
		// TODO adjust
		rect.setLayoutX(200);
		rect.setLayoutY(75);
		rect.setFill(Color.AZURE);
		
		title.setLayoutX(600);
		title.setLayoutY(35);
		
		contents.setLayoutX(150);
		contents.setLayoutY(100);
		
		choices.get(curr).selected();
		pane.setOnKeyPressed(e-> {
			switch(e.getCode()) {
			case UP:
				choices.get(curr).deselected();
				if(curr == 0) {
					curr = choices.size();
				}
				choices.get(--curr).selected();
				break;
			case DOWN:
				choices.get(curr).deselected();
				if(curr == choices.size() - 1) {
					curr = -1;
				}
				choices.get(++curr).selected();
				break;
			case LEFT:
				choices.get(curr).change();
				break;
			case RIGHT:
				choices.get(curr).change();
				break;
			default:
			}
		});
		
		sm.setRoot(pane);
		System.out.println(1);
	}
}
