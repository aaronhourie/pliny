import java.util.ArrayList;

public class DNA {

	private Resources intake;
	private int starvThresh, lightThresh, darkThresh, hotThresh, coldThresh;
	private int growthRate, maturity;
	private static int mutationRate = 5;

	public DNA(DNA parent){

		this.intake = new Resources(parent.intake);
		this.starvThresh = parent.starvThresh;
		this.lightThresh = parent.lightThresh;
		this.darkThresh = parent.darkThresh;
		this.hotThresh = parent.hotThresh;
		this.coldThresh = parent.coldThresh;
		this.growthRate = parent.growthRate;
		this.maturity = parent.maturity;

		mutate();
	}

	public DNA(Resources intake, int starvThresh, int lightThresh, int darkThresh,
	           int hotThresh, int coldThresh, int growthRate, int maturity) {

		this.intake = intake;
		this.starvThresh = starvThresh;
		this.lightThresh = lightThresh;
		this.darkThresh = darkThresh;
		this.hotThresh = hotThresh;
		this.coldThresh = coldThresh;
		this.growthRate = growthRate;
		this.maturity = maturity;
	}

	public void mutate() {

		String gene = getRandomGene();

		switch (gene){
			case "starvThresh":
				starvThresh += randomMutation();
				break;
			case "lightThresh":
				lightThresh += randomMutation();
				break;
			case "darkThresh":
				darkThresh += randomMutation();
				break;
			case "hotThresh":
				hotThresh += randomMutation();
				break;
			case "coldThresh":
				coldThresh += randomMutation();
				break;
			case "growthRate":
				growthRate += randomMutation();
				break;
			case "maturity":
				maturity += randomMutation();
				break;
			case "intake":
			default:
				mutateIntake();
				break;
		}
	}

	private String getRandomGene(){

		// Base number of genes
		// Intakes count as 1 gene. int numGenes = 8;
		int numGenes = 8;
		String gene;

		int geneNum = (int)(Math.random() * numGenes);

		switch (geneNum){
			case 1: gene = "starvThresh"; break;
			case 2: gene = "lightThresh"; break;
			case 3: gene = "darkThresh"; break;
			case 4: gene = "hotThresh"; break;
			case 5: gene = "coldThresh"; break;
			case 6: gene = "growthRate"; break;
			case 7: gene = "maturity"; break;
			case 0:
			default: gene = "intake"; break;
		}

		return gene;
	}

	private int randomMutation(){

		int mutation = (int)(Math.random() * mutationRate);
		mutation *= (int)((Math.random() * 3) - 1);
		return mutation;
	}

	private int rareMutation(){

		int mutationChance = (int)(Math.random() * 1000);
		int mutation = 0;

		if (mutationChance < 10){
			mutation = (int)(Math.random() * mutationRate);
		}

		return mutation;
	}


	private void mutateIntake(){

		for (int i = 0; i < intake.getNumResources(); i++){
			if (intake.getResourceAt(i) != 0) {
				intake.addResourceAt(i, randomMutation());
			}
			else {
				intake.addResourceAt(i, randomMutation());
			}
		}
	}

	public Resources getIntake() {
		return intake;
	}

	public int getStarvThresh() {
		return starvThresh;
	}

	public int getLightThresh() {
		return lightThresh;
	}

	public int getDarkThresh() {
		return darkThresh;
	}

	public int getHotThresh() {
		return hotThresh;
	}

	public int getColdThresh() {
		return coldThresh;
	}

	public int getGrowthRate() {
		return growthRate;
	}

	public int getMaturity() {
		return maturity;
	}

	@Override
	public String toString() {
		return "DNA{" +
						       "intake=" + intake +
						       ", starvThresh=" + starvThresh +
						       ", lightThresh=" + lightThresh +
						       ", darkThresh=" + darkThresh +
						       ", hotThresh=" + hotThresh +
						       ", coldThresh=" + coldThresh +
						       ", growthRate=" + growthRate +
						       ", maturity=" + maturity +
						       '}';
	}
}
