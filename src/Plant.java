public class Plant {

	private Resources resources;
	private int age, generation, height, starvation, location;
	private DNA dna;

	public Plant(DNA parent, int location, int parentGeneration) {

		this.dna = new DNA(parent);
		resources = new Resources();
		age = 0;
		height = 1;
		starvation = 0;
		generation = parentGeneration + 1;
		this.location = location;

		/*
		if (generation == 50){
			System.out.println(toString());
			System.out.println(dna.toString());
			System.exit(0);
		}
		*/
	}

	public void age(Ecosystem ecosystem){

		if (ecosystem != null){

			age++;
			checkResources(ecosystem);
			checkLight(ecosystem);
			checkTemperature(ecosystem);
			checkChildren(ecosystem);
			checkDeath(ecosystem);
		}
	}

	private void checkResources(Ecosystem ecosystem){

			if (age % dna.getGrowthRate() == 0) {
			// If the plant successfully draws resources
			if (!ecosystem.getResources().takeResources(dna.getIntake(), resources)){
				// Grow the plant.
				if (starvation > 0) {
					starvation--;
				}
				else {
					height++;
				}
			}
			else {
				// Starve the plant.
				starvation++;
				if (starvation > dna.getStarvThresh()) {
					// Plant dies.
				}
			}
		}
	}

	private void checkLight(Ecosystem ecosystem){

		if (ecosystem.getLightLevel() > dna.getLightThresh()){
			starvation++;
		}
		else if (ecosystem.getLightLevel() < dna.getDarkThresh()){
			starvation++;
		}
	}

	private void checkTemperature(Ecosystem ecosystem){

		if (ecosystem.getTemperature() > dna.getHotThresh()){
			starvation++;
		}
		else if (ecosystem.getTemperature() < dna.getColdThresh()){
			starvation++;
		}
	}

	private void checkChildren(Ecosystem ecosystem){

		if (starvation < 1 && dna.getMaturity() < age){
			int newLocation = (int)(Math.random() * ecosystem.getWidth());
			Plant child = new Plant(dna, newLocation, generation);
			ecosystem.addPlant(child);
			starvation += dna.getStarvThresh()/2;
		}
	}

	private void checkDeath(Ecosystem ecosystem){

		if (starvation > dna.getStarvThresh()){
			ecosystem.getResources().giveResources(resources);
			ecosystem.removePlant(this);
		}
	}

	public int getAge() {
		return age;
	}

	public int getLocation() {
		return location;
	}

	public int getHeight() {
		return height;
	}

	public int getStarvation() {
		return starvation;
	}

	@Override
	public String toString() {
		return "Plant{" +
						       "age=" + age +
						       ", height=" + height +
						       ", starvation=" + starvation +
						       ", gen=" + generation+
						       '}';
	}
}
