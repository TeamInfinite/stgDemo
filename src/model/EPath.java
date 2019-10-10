package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// "[command] [original x] [original y] [final x] [final y] [type] [frames] [radius]"
public class EPath {
	
	private double x;
	private double y;
	
	public EPath(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static LinkedList<EPath> load(String addr){
		LinkedList<EPath> temp = new LinkedList<>();
		Scanner scan = null;
		
		try {
			scan = new Scanner(new File(addr));
			while(scan.hasNext()) {
				String[] line = scan.nextLine().split(" ");
				double oriX = Double.parseDouble(line[0]);
				double oriY = Double.parseDouble(line[1]);
				double finX = Double.parseDouble(line[2]);
				double finY = Double.parseDouble(line[3]);
				String type = line[4];
				int frames = Integer.parseInt(line[5]);
				double radius = Double.parseDouble(line[6]);
				
				temp.addAll(translate(oriX, oriY, finX, finY, type, frames, radius));
			}	
		} catch(FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			scan.close();
		}
		
		return temp;
	}
	
	public static LinkedList<EPath> translate(double oriX, double oriY, double finX, double finY, String type, int frames, double radius) {
		LinkedList<EPath> result = new LinkedList<>();
		
		double difX = finX - oriX;
		double difY = finY - oriY;
		double length = Math.sqrt(Math.pow(difX,2) + Math.pow(difY, 2));
		
		if(type.equals("D")) {
			for(int i = 0; i <= frames; i++)
			{
				EPath tempPosition = directPath(difX, difY, i, frames);
				tempPosition.addX(oriX);
				tempPosition.addY(oriY);
				result.offer(tempPosition);
				
				// System.out.println(tempPosition.getX() + " " + tempPosition.getY());
			}
		} else if (type.equals("TC")) {
			
		} else if (type.equals("CR")) {
			double angleSpeed = 2 * Math.PI / frames;
			for(int i = 0; i <= frames; i++)
			{
				EPath tempPosition = circlePath(oriX, oriY, radius, i, angleSpeed);
				result.offer(tempPosition);
			}
		}
		
		return result;
	}
	

	private static EPath directPath(double difX, double difY, double elapseF, double frames)
	{
		double tempX, tempY;
		tempX = difX * elapseF / frames;
		tempY = difY * elapseF / frames;
		
		//System.out.println(tempX + " " + tempY + " " + elapseF);
		return new EPath(tempX, tempY);
	}
	
	private static EPath circlePath(double strX, double strY, double radius, double elapseF, double angleSpeed) {
		double cirOriX = strX - radius;
		double cirOriY = strY;
		double tempX = radius * Math.cos(elapseF * angleSpeed) + cirOriX;
		double tempY = radius * Math.sin(elapseF * angleSpeed) + cirOriY;
		
		return new EPath(tempX, tempY);
	}

	// == getters and setters ==
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void addX(double toAdd) {
		x += toAdd;
	}
	
	public void addY(double toAdd) {
		y += toAdd;
	}
}
