package gui;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Loading;

public class LoadingScreen extends AnchorPane {
	
	private class LoadContents extends Task<Void> {
		
		private Loading toLoad;
		
		public LoadContents(Loading toLoad) {
			this.toLoad = toLoad;
		}

		@Override
		protected Void call() throws Exception {
			toLoad.loading();
			
			updateProgress(100, 100);
			
			return null;
		}
	}
	
	// == fields ==
	private Rectangle back = new Rectangle(0, 0, PaneParam.MAIN_PANE_WIDTH, PaneParam.MAIN_PANE_HEIGHT);
	private Label label = new Label();
	private LoadContents loading;
	private Thread loadThread;
			
	public LoadingScreen(Loading toLoad) {
		loading = new LoadContents(toLoad);
		loadThread = new Thread(loading);
		
		setPrefSize(PaneParam.MAIN_PANE_WIDTH, PaneParam.MAIN_PANE_HEIGHT);
		
		back.setFill(Color.WHITE);
		label.setLayoutX(PaneParam.MAIN_PANE_WIDTH / 2 - 10);
		label.setLayoutY(PaneParam.MAIN_PANE_HEIGHT / 2 - 10);
		loading.progressProperty().addListener((obs, old, newVal) -> {
			Platform.runLater(() -> label.setText((int)(newVal.doubleValue() * 100) + "%"));
		});
		
		getChildren().addAll(back, label);
		
		loadThread.start();
	}
	
	

}
