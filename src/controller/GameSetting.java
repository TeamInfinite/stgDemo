package controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/* Parameters:
 * 1. fullScreen = [True/False]
 */
public class GameSetting {
	
	public static boolean fullScreen = false;
	public static double bulletTransparency = 0.5;
	
	public static void save() {
		PrintWriter writer = null;
		
		try {
			File file = new File("save00.dat");
			
			if(file.exists()) {
				file.delete();
			}
			
			file.createNewFile();
			
			writer = new PrintWriter(file);
			
			writer.println("fullScreen=" + (fullScreen ? "True" : "False"));
			
		} catch(Exception e) {
			
		} finally {
			writer.close();
		}
	}
	
	public static void load() {
		Scanner scan = null;
		
		try {
			
			File file = new File("save00.dat");
			
			if(!file.exists()) {
				save();
			}
			
			scan = new Scanner(file);
			
			while(scan.hasNext()) {
				String[] temp = scan.nextLine().split("=");
				
				switch(temp[0]) {
				case "fullScreen":
					fullScreen = Boolean.parseBoolean(temp[1]);
				default:
				}
			}
			
			
			
		} catch(Exception e) {
			
		}
	}

}
