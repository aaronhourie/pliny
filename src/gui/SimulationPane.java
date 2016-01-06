package gui;

import engine.Ecosystem;
import engine.Plant;

import javax.swing.*;
import java.awt.*;

public class SimulationPane extends JPanel {

	private Ecosystem ecosystem;

	public SimulationPane(Ecosystem ecosystem){

		super();
		this.ecosystem = ecosystem;
	}

	public void paint(Graphics g){

		ecosystem.setDimensions(getWidth(), getHeight());
		g.setColor(Color.decode(getBackgroundColor()));
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < ecosystem.size(); i++){
			Plant curr = ecosystem.getPlantAt(i);
			g.setColor(Color.decode(getPlantColor(curr)));
			g.fillRect(curr.getLocation(), getHeight() - 5*curr.getHeight(), 5, 10*curr.getHeight());
		}
	}

	public String getPlantColor(Plant plant){

		if (plant.getStarvation() > 0){
			return "#225522";
		}
		else {
			return "#22FF22";
		}
	}

	private String getBackgroundColor() {

		String color = "#";
		int[] rgb = getTemperatureColor();
		//rgb = addLightColor(rgb);

		for (int i = 0; i < rgb.length; i++){
			color += Integer.toHexString(rgb[i]);
		}

		return color;
	}

	private int[] getTemperatureColor(){

		int[] rgb = {0x00, 0x00, 0x00};

		if (ecosystem.getTemperature() > 40){

			rgb[0] = 0xFF;
			rgb[1] = 0x10;
			rgb[2] = 0x10;
		}
		else if (ecosystem.getTemperature() < 0) {

			rgb[0] = 0xAA;
			rgb[1] = 0xAA;
			rgb[2] = 0xFF;
		}
		else {

			rgb[0] = 0x20;
			rgb[1] = 0x20;
			rgb[2] = 0xAA;
		}


		for (int i = 0; i < rgb.length; i++){
			if (rgb[i] < 0x10){
				rgb[i] = 0x10;
			}
			else if (rgb[i] > 0xFF){
				rgb[i] = 0xFF;
			}
		}

		return rgb;
	}

	private int[] addLightColor(int[] rgb){

		for (int i = 0; i < rgb.length; i++){

			int light = (ecosystem.getLightLevel() * 0xAA / (ecosystem.getDayLength()/2));
			light -= 0xAA;
			rgb[i] += light;

			if (rgb[i] > 0xF0){
				rgb[i] = 0xF0;
			}
			else if (rgb[i] < 0x10) {
				rgb[i] = 0x10;
			}
		}

		return rgb;
	}
}