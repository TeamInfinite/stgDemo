package gui;

import gui.titleScreen.TitleScreenManager;

// --module-path D:\javafx-sdk-11.0.2\lib  --add-modules=javafx.controls 

import javafx.application.Application;
import javafx.stage.Stage;
import test.TestPane;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		SceneManager sc = new SceneManager();
		sc.setInitialPane(new LoadingScreen(new TitleScreenManager(sc)));
		
		stage = sc.getStage();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
