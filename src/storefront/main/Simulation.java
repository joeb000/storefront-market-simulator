package storefront.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import storefront.entities.Area;
import storefront.entities.Customer;
import storefront.entities.Machine;
import storefront.entities.Product;
import storefront.helpers.DBConnection;
import storefront.services.SimulatorService;
import storefront.services.SystemService;

public abstract class Simulation {
	
	final static Logger log = Logger.getLogger(Simulation.class);


	protected SimulatorService theSimService = SimulatorService.getInstance();
	protected SystemService theSystemService = SystemService.getInstance();

	protected ArrayList<Product> productList = new ArrayList<Product>();
	protected ArrayList<Customer> customerList = new ArrayList<Customer>();
	protected ArrayList<Machine> machineList = new ArrayList<Machine>();
	protected ArrayList<Area> areaList = new ArrayList<Area>();
		
	public void initServices(){
		if (new File("storefront.db").delete()){

			try {
				DBConnection.getInstance().executeDBScripts("sql/CreateTables2.sql");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		try {
			theSimService.readProductsFromFile(new File("input/Products.txt"));		
			theSimService.readMachinesFromFile(new File("input/Machines.txt"));
			theSimService.readCustomersFromFile(new File("input/Customers.txt"));
			theSimService.readAreasFromFile(new File("input/Areas.txt"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadTables(){
			productList = theSystemService.retrieveAllProducts();		
			customerList = theSystemService.retrieveAllCustomers();
			machineList = theSystemService.retrieveAllMachines();
			areaList = theSystemService.retrieveAllAreas();
			
	}

	public void startSimulation(int rounds){
		int desiredRounds = rounds;
		for (int i = 0; i < desiredRounds; i++) {
			log.debug("start round " + i);
			round(i);
			
		}
	}
	
	public abstract void round(int roundIter);
	
	
	protected void recordTransaction(int cID, int pID, int mID, int round){
		int mockUnixDate=1470351705+(round*86400);
		log.info("Customer: "+ cID + " bought product: " + pID + " from MachineID:" + mID + " dateTime:" + new java.util.Date((long)mockUnixDate*1000));
		theSystemService.commitPurchase(cID, pID, mID, mockUnixDate);
	}

	protected void recordRequest(int cID, int pID, int mID, int round){
		int mockUnixDate=1470351705+(round*86400);
		log.info("Customer: "+ cID + " requested product: " + pID +  " at machine: " + mID );
		theSystemService.commitRequest(cID, pID, mID, mockUnixDate);
	}
	

}
