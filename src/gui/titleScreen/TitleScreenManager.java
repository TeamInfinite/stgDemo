package gui.titleScreen;

import java.util.ArrayList;

import controller.GameSetting;
import gui.LoadingScreen;
import gui.PaneParam;
import gui.SceneManager;
import gui.SettingPaneManager;
import gui.ViewManager;
import gui.storyPane.StoryPaneManager;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Loading;
import model.events.TestEvent1;

/*
Start
Player data
Settings
Music
Exit
*/

public class TitleScreenManager implements Loading {
	
	// == fields ==
	private AnchorPane titlePane;
	private mainMenuButton startButton;
	private mainMenuButton playerDataButton;
	private mainMenuButton SettingsButton;
	private mainMenuButton MusicButton;
	private mainMenuButton ExitButton;
	
	private SceneManager scene;
	
	private ArrayList<mainMenuButton> mainMenuList = new ArrayList<>();
	
	private int curr = 0;
	
	// == constructor ==
	public TitleScreenManager(SceneManager sm) {
		titlePane = new AnchorPane();
		scene = sm;
	}
	
	// == methods ==
	
	public void getKey() {
		titlePane.setOnKeyPressed(e -> {
			switch(e.getCode()) {
			case UP:
				pressUp();
				break;
			case DOWN:
				pressDown();
				break;
			case ENTER:
				if(curr == 0) {
					scene.setRoot(new LoadingScreen(new StoryPaneManager(scene, "src\\resources\\text\\Text1", new TestEvent1(scene))));
				} else if(curr == 1) {
					scene.setRoot(new LoadingScreen(new SettingPaneManager(scene)));
				}
				break;
			default :
			}
		});
	}
	
	
	private void setBackground(String addr) {
		ImageView background = new ImageView(new Image(addr));
		background.setFitHeight(PaneParam.MAIN_PANE_HEIGHT);
		background.setFitWidth(PaneParam.MAIN_PANE_WIDTH);
		titlePane.getChildren().add(background);
	}
	
	private void createbuttons() {
		startButton = new mainMenuButton(TitleScreenParam.START_ACTIVE_IMAGE,TitleScreenParam.START_INACTIVE_IMAGE);
		playerDataButton = new mainMenuButton(TitleScreenParam.PLAYER_DATA_ACTIVE_IMAGE,TitleScreenParam.PLAYER_DATA_INACTIVE_IMAGE);
		SettingsButton = new mainMenuButton(TitleScreenParam.SETTINGS_ACTIVE_IMAGE,TitleScreenParam.SETTINGS_INACTIVE_IMAGE);
		MusicButton = new mainMenuButton(TitleScreenParam.MUSIC_ACTIVE_IMAGE,TitleScreenParam.MUSIC_INACTIVE_IMAGE);
		ExitButton = new mainMenuButton(TitleScreenParam.EXIT_ACTIVE_IMAGE,TitleScreenParam.EXIT_INACTIVE_IMAGE);
	}
	
	private void addButtonsToList() {
		mainMenuList.add(startButton);
		mainMenuList.add(playerDataButton);
		mainMenuList.add(SettingsButton);
		mainMenuList.add(MusicButton);
		mainMenuList.add(ExitButton);
	}
	
	private void setButtonsLocation()
	{
		for(int i = 0; i < 5; i++)
		{
			titlePane.getChildren().add(mainMenuList.get(i));
			mainMenuList.get(i).setLayoutX(50);
			mainMenuList.get(i).setLayoutY(140 + i * 60);
		}
		
		mainMenuList.get(0).setActive();
	}

	private void pressDown() {
		mainMenuList.get(curr).setInactive();
		curr++;
		if(curr == mainMenuList.size())
		{
			curr = 0;
		}
		mainMenuList.get(curr).setActive();
	}
	
	private void pressUp() {
		mainMenuList.get(curr).setInactive();
		curr--;
		if(curr == -1)
		{
			curr = mainMenuList.size() - 1;
		}
		mainMenuList.get(curr).setActive();
	}

	public AnchorPane getPane() {
		return titlePane;
	}

	@Override
	public void loading() {
		getKey();
		setBackground(TitleScreenParam.BACKGROUND_IMAGE);
		createbuttons();
		addButtonsToList();
		setButtonsLocation();
		
		Platform.runLater(() -> scene.setRoot(getPane()));
	}
}

class mainMenuButton extends AnchorPane {
	private String buttonImageInactive;
	private String buttonImageActive;
	
	private ImageView image = new ImageView();
	
	public mainMenuButton(String buttonImageActive, String buttonImageInactive) {
		this.buttonImageInactive = buttonImageInactive;
		this.buttonImageActive = buttonImageActive;
		image.setFitWidth(250);
		image.setFitHeight(75);
		
		setInactive();
		getChildren().add(image);
		setPrefSize(200, 100);
	}
	
	public void setActive() {
		image.setImage(new Image(buttonImageActive));
		
	}
	
	public void setInactive() {
		image.setImage(new Image(buttonImageInactive));
	}
}


