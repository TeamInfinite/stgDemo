package gui.storyPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import controller.GameEvent;
import gui.SceneManager;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Loading;
import util.Util;

/* Command: 
 * 1. new [Character] [PosX] [PosY] -- add a new character to the pane
 * 2. remove [Character] -- remove the certain character from the pane
 * 3. removeAll -- remove all characters from the pane 
 * 4. dialog [Character] [Dialog] -- show dialog
 * 5. back [Background] -- change the background of the pane
 * 6. set [Character] [newPosX] [newPosY] -- change the pos of the character
 * */
public class StoryPaneManager implements Loading {
	
	private class DialogPane extends AnchorPane {
		
		// TODO change rect to imageview
		private Rectangle back = new Rectangle(620, 175);
		private Label name = new Label();
		private Label dialog = new Label();
		
		public DialogPane() {
			back.setFill(Color.AZURE);
			back.setOpacity(0.5);
			
			getChildren().addAll(back, name, dialog);
			
			back.setLayoutX(10);
			back.setLayoutY(260);
			
			name.setLayoutX(25);
			name.setLayoutY(270);
			
			dialog.setLayoutX(25);
			dialog.setLayoutY(300);
		}
	}
	
	private abstract class StoryCommand {
		protected boolean next;
		
		public abstract void run();
	}
	
	// == fields ==
	private ImageView back = new ImageView();
	private DialogPane dialogPane = new DialogPane();
	private AnchorPane pane = new AnchorPane();
	
	private ArrayList<Character> characters = new ArrayList<>();
	private LinkedList<StoryCommand> commands = new LinkedList<>();
	private String commandsAddr;
	
	private boolean running = true;
	private GameEvent nextEvent;
	private SceneManager sm;
	
	public StoryPaneManager(SceneManager sm, String commandsAddr, GameEvent nextEvent) {
		this.commandsAddr = commandsAddr;
		this.nextEvent = nextEvent;
		this.sm = sm;
	}
	
	@Override
	public void loading() {
		initilizePane();
		initilizeKey();
		loadCommands(commandsAddr);
		next();
		
		sm.setRoot(pane);
	}

	// == init methods ==
	private void initilizePane() {
		pane.getChildren().addAll(back, dialogPane);
	}
	
	private void initilizeKey() {
		pane.setOnKeyPressed(e -> {
			switch(e.getCode()) {
			case ENTER:
				if(!running) {
					next();
				}
				break;
			default:
				break;
			}
		});
	}
	
	private void loadCommands(String commandsAddr) {
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(commandsAddr));
			
			while(scanner.hasNext()) {
				// TODO add set command
				String[] temp = scanner.nextLine().split(" ");
				
				switch(temp[0]) {
				case "new":
					commands.offer(new StoryCommand() {
						@Override
						public void run() {
							next = true;
							Character tempC = Character.getCharacter(temp[1]);
							characters.add(tempC);
							pane.getChildren().add(tempC.getImg());
							tempC.getImg().setLayoutX(Double.parseDouble(temp[2]));
							tempC.getImg().setLayoutY(Double.parseDouble(temp[3]));
							dialogPane.toFront();
						}
					});
					break;
				case "remove":
					commands.offer(new StoryCommand() {
						@Override
						public void run() {
							next = true;
							Character tempC = Character.getCharacter(temp[1]);
							if(characters.contains(tempC)) {
								pane.getChildren().remove(tempC.getImg());
								characters.remove(tempC);
							}
						}
					});
					break;
				case "removeAll":
					commands.offer(new StoryCommand() {
						@Override
						public void run() {
							next = true;
							for(Character c : characters) {
								pane.getChildren().remove(c.getImg());
							}
							characters.clear();
						}
					});
					break;
				case "dialog":
					commands.offer(new StoryCommand() {
						@Override
						public void run() {
							next = false;
							Character tempC = Character.getCharacter(temp[1]);
							dialogPane.name.setTextFill(tempC.getColor());
							dialogPane.dialog.setTextFill(tempC.getColor());
							dialogPane.name.setText(tempC.getName());
							dialogPane.dialog.setText(temp[2]);
						}
					});
					break;
				case "back":
					commands.offer(new StoryCommand() {
						@Override
						public void run() {
							next = true;
							switch(temp[1]) {
							case "Test1":
								back.setImage(new Image(StoryPaneAddr.TEST_BACK1));
								break;
							default:
							}
						}
					});
					break;
				}
			}
		} catch(IOException | NumberFormatException e) {
			Util.errorBox("Error opening the file", "ÎÄ¼þËð»µ¡£");
			System.exit(1);
		} finally {
			scanner.close();
		}
	}
	
	// == methods ==
	public void next() {
		running = true;
		if(commands.peek() != null) {
			StoryCommand temp = commands.poll();
			temp.run();
			
			if(temp.next) {
				next();
			}
			running = false;
		} else {
			nextEvent.nextMove();
		}
	}
}
