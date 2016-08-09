package storefront.main;

import java.io.FileNotFoundException;

import org.apache.log4j.PropertyConfigurator;

public class Main {
    static final String log4j_path = "config/log4j.properties";


	public static void main(String[] args) throws FileNotFoundException {

        PropertyConfigurator.configure(log4j_path);
        
	//	Main theMain = new Main();
		Simulation theMain = new FilledMode();

		theMain.initServices();
		theMain.loadTables();
		theMain.startSimulation();
	}

}
