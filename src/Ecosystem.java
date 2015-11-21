import java.util.ArrayList;

public class Ecosystem {

	private Resources resources;
	private int temperature, lightLevel;
	private int changeRate, dayLength;
	private int width;
	private int height;
	private int age;
	private ArrayList<Plant> plants;

	public Ecosystem(Resources resources, int temperature, int dayLength, int changeRate) {
		this.temperature = temperature;
		this.dayLength = dayLength;
		this.resources = resources;
		this.changeRate = changeRate;
		plants = new ArrayList<Plant>();
	}

	public boolean cycle(){

		if (plants.size() > 0) {
			changeLight();
			changeClimate();
			agePlants();
			age++;
			return true;
		}
		System.out.println("Ecosystem has failed!");
		return false;
	}

	public void agePlants(){

		try {
			if (!plants.isEmpty()) {
				for (int i = 0; i < plants.size(); i++) {
					plants.get(i).age(this);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e){

			e.printStackTrace();
		}
	}

	private void changeClimate(){

		if (age % changeRate == 0) {
			temperature += (Math.random() * 3) - 1;
			System.out.println("Temperature: " + temperature + " degrees celsius.");
		}
	}

	private void changeLight(){

		lightLevel= Math.abs((age % dayLength) - dayLength/2);
	}

	public Resources getResources() {
		return resources;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getLightLevel() {

		return lightLevel;
	}

	public Plant getPlantAt(int index) {
		return plants.get(index);
	}

	public int size(){

		return plants.size();
	}

	public void addPlant(Plant plant){

		plants.add(plant);
	}

	public void removePlant(Plant plant){

		plants.remove(plant);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {

		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setDimensions(int width, int height){
		this.width = width;
		this.height = height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}
}
