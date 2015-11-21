public class Pliny {

	public static void main(String[] args){

		Resources ecoRes = new Resources();
		Resources genesisIntake = new Resources();
		for (int i = 0; i < 118; i++) {
			ecoRes.setResourceAt(i, 5000);
			genesisIntake.setResourceAt(i, 1);
		}

		Ecosystem ecosystem = new Ecosystem(ecoRes, 0, 205, 100);
		DNA genesis = new DNA(genesisIntake, 15, 100, 10, 35, -15, 5, 50);
		Plant adam = new Plant(genesis, 150, 0);
		ecosystem.addPlant(adam);

		PlinyWindow mainWindow = new PlinyWindow(ecosystem);

		while (ecosystem.cycle()){

			try {
				mainWindow.repaint();
				Thread.sleep(10);
			}
			catch (InterruptedException e){}
		}
	}
}
