package storefront.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import storefront.entities.Area;
import storefront.entities.Customer;
import storefront.entities.Machine;
import storefront.entities.Product;
import storefront.helpers.DBConnection;
import storefront.services.SimulatorService;
import storefront.services.SystemService;

public class Main {

	private SimulatorService theSimService = SimulatorService.getInstance();
	private SystemService theSystemService = SystemService.getInstance();


	public ArrayList<Product> productList = new ArrayList<Product>();
	public ArrayList<Customer> customerList = new ArrayList<Customer>();
	public ArrayList<Machine> machineList = new ArrayList<Machine>();
	public ArrayList<Area> areaList = new ArrayList<Area>();

	public static void main(String[] args) throws FileNotFoundException {
		Main theMain = new Main();
		theMain.initServices();
		theMain.loadTables();
		
		theMain.startSimulation();
	}

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



	public void startSimulation(){
		int desiredRounds = 100;
		for (int i = 0; i < desiredRounds; i++) {
			System.out.println("start round " + i);
			round(i);
		}
	}

	public void round(int roundIter){
		for (Customer customer: customerList){
			theSimService.randomlyAssignCustomerMachine(customer);
			System.out.println("*******" + customer.getName());
			HashMap<Integer,Integer> productsInMachine = theSimService.getProductsInMachine(customer.getCurrentMachineID());
			System.out.println(productsInMachine.toString());
			int productChosen = theSimService.chooseProduct(customer.getCustomerID());
			System.out.println("Product Chosen:" + productChosen);
			if (productsInMachine.containsKey(productChosen) && productsInMachine.get(productChosen)>=1){
				System.out.println("YES THE MACHINE HAS YOUR PRODUCT (and its stocked)!");
				recordTransaction(customer.getCustomerID(), productChosen, customer.getCurrentMachineID(), roundIter);
			}
			else {
				System.out.println("BOOOOOO! the machine doesn't have what you are looking for");
				recordRequest(customer.getCustomerID(), productChosen, customer.getCurrentMachineID(), roundIter);
			}
			
		}
	}

	public void recordTransaction(int cID, int pID, int mID, int round){
		int mockUnixDate=1470351705+(round*86400);
		System.out.println("Customer: "+ cID + " bought product: " + pID + " from MachineID:" + mID + " dateTime:" + new java.util.Date((long)mockUnixDate*1000));
		theSystemService.commitPurchase(cID, pID, mID, mockUnixDate);
	}

	public void recordRequest(int cID, int pID, int mID, int round){
		int mockUnixDate=1470351705+(round*86400);
		System.out.println("Customer: "+ cID + " requested product: " + pID +  " at machine: " + mID );
		theSystemService.commitRequest(cID, pID, mID, mockUnixDate);
	}

}
