package engine;

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
			int deficit  = ecosystem.getResources().takeResources(dna.getIntake(), resources);
			if (deficit < dna.getResourceThresh()){
				// Grow the plant.
				if (starvation > 0) {
					starvation-=2;
				}
				else {
					height++;
				}
			}
			else {
				// Starve the plant.
				starvation++;
				if (starvation > dna.getStarvThresh()) {
					// engine.Plant dies.
				}
			}
		}
	}

	private void checkLight(Ecosystem ecosystem){

		if (ecosystem.getLightLevel() > dna.getLightThresh()){
			int excess = Math.abs(ecosystem.getLightLevel() - dna.getLightThresh());
			if (checkProbability(excess)){
				starvation++;
			}
		}
		else if (ecosystem.getLightLevel() < dna.getDarkThresh()){
			int excess = Math.abs(ecosystem.getLightLevel() - dna.getDarkThresh());
			if (checkProbability(excess)){
				starvation++;
			}
		}
	}

	private void checkTemperature(Ecosystem ecosystem){

		if (ecosystem.getTemperature() > dna.getHotThresh()){
			int excess = Math.abs(ecosystem.getTemperature() - dna.getHotThresh());
			if (checkProbability(excess)){
				starvation++;
			}
		}
		else if (ecosystem.getTemperature() < dna.getColdThresh()) {
			int excess = Math.abs(ecosystem.getTemperature() - dna.getColdThresh());
			if (checkProbability(excess)){
				starvation++;
			}
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

	private boolean checkProbability(int excess){

		int probability = (int)(Math.random() * 100);
		return (probability + excess) > 90;
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

	public DNA getDna() {
		return dna;
	}

	@Override
	public String toString() {

		return "engine.Plant{" +
						       "age=" + age +
						       ", height=" + height +
						       ", starvation=" + starvation +
						       ", gen=" + generation+
						       '}';
	}
}
