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
		int desiredRounds = 4;
		for (int i = 0; i < desiredRounds; i++) {
			System.out.println("start round " + i);
			round(i);
		}
	}

	public void round(int iter){
		for (Customer customer: customerList){
			theSimService.randomlyAssignCustomerMachine(customer);
			System.out.println("*******" + customer.getName());
			HashMap<Integer,Integer> productsInMachine = theSimService.getProductsInMachine(customer.getCurrentMachineID());
			System.out.println(productsInMachine.toString());
		}
	}

	public void recordTransaction(Customer c, Product p, Machine l, int round){
		int mockUnixDate=1470351705+(round*86400);
		System.out.println("Customer: "+ c.getName() + " bought product: " + p.getProductName() + " from MachineID:" + l.getMachineID() + " at machine: " + l.getMachineName() + " dateTime:" + new java.util.Date((long)mockUnixDate*1000));
		SystemService.getInstance().commitPurchase(c, p, l, mockUnixDate);
	}

	public void recordRequest(Customer c, Product p, Machine l, int round){
		System.out.println("Customer: "+ c.getName() + " requested product: " + p.getProductName() + " from MachineID:" + l.getMachineID() + " at machine: " + l.getMachineName() );

	}

}
