package storefront.main;

import java.io.FileNotFoundException;

import org.apache.log4j.PropertyConfigurator;

public class Main {
    static final String log4j_path = "config/log4j.properties";

	public static void main(String[] args) throws FileNotFoundException {

        PropertyConfigurator.configure(log4j_path);
        
		Simulation theMain = filledMode();
	//	Simulation theMain = dumbMode();

		theMain.initServices();
		theMain.loadTables();
		theMain.startSimulation(331);
	}
	
	public static Simulation dumbMode(){
		DumbMode mode = new DumbMode();
		mode.setRestockPeriod(30);
		mode.setSimulationRounds(301);
		return mode;
	}
	
	public static Simulation filledMode(){
		FilledMode mode = new FilledMode();
		mode.setRestockPeriod(30);
		mode.setSimulationRounds(301);
		return mode;
	}
	
	public static Simulation responsiveMode(){
		ResponsiveMode mode = new ResponsiveMode();
		mode.setRestockPeriod(30);
		mode.setSimulationRounds(301);
		return mode;
	}
}
