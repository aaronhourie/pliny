package engine;

import java.io.*;
import java.util.Scanner;

public class Resources {

	private int[] resources;
	private static String[] elements = getElements();
	private static final int numResources = 118;

	public Resources(){

		resources = new int[numResources];
	}

	public Resources(Resources parent){

		resources = new int[numResources];

		for (int i = 0; i < numResources; i++){
			this.resources[i] = parent.getResourceAt(i);
		}
	}

	public static String[] getElements() {

		String[] foundElements = new String[numResources];

		try {
			File file = new File("elements.txt");
			Scanner scn = new Scanner(file);
			int i = 0;
			while (scn.hasNext()) {
				foundElements[i] = scn.nextLine();
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred: elements.txt was not found.");
		}

		return foundElements;
	}

	public int takeResources(Resources intake, Resources dest){

		int deficit = 0;

		for (int i = 0; i < numResources; i++){

			if (getResourceAt(i) < intake.getResourceAt(i)){
				dest.addResourceAt(i, getResourceAt(i));
				deficit += (dest.getResourceAt(i) - getResourceAt(i));
				setResourceAt(i, 0);
			}
			else {
				subtractResourceAt(i, intake.getResourceAt(i));
				dest.addResourceAt(i, intake.getResourceAt(i));
			}
		}
		return deficit;
	}

	public void giveResources(Resources given){

		for (int i = 0; i < numResources; i++){
			addResourceAt(i, given.getResourceAt(i));
		}
	}
	public int getResourceAt(int index) {
		return resources[index];
	}

	public void setResourceAt(int index, int value){
		resources[index] = value;
	}

	public void subtractResourceAt(int index, int value){
		resources[index] -= value;
	}

	public void addResourceAt(int index, int value){

		resources[index] += value;
	}
	public static int getNumResources() {
		return numResources;
	}

	public void printResources(){
		for (int i = 0; i < numResources; i++){
			System.out.println(elements[i] + ": " + resources[i]);
		}
	}
}
