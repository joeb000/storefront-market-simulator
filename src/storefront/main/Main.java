package storefront.main;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
	//	Main theMain = new Main();
		Simulation theMain = new FilledMode();

		theMain.initServices();
		theMain.loadTables();
		theMain.startSimulation();
	}

}
