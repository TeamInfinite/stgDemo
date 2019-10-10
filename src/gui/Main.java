package gui;

// --module-path D:\javafx-sdk-11.0.2\lib  --add-modules=javafx.controls 

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ViewManager vm = new ViewManager();
		
		stage = vm.getStage();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
