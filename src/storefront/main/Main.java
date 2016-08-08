package storefront.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Customer;
import storefront.entities.Machine;
import storefront.entities.Product;
import storefront.helpers.DBConnection;
import storefront.services.CustomerService;
import storefront.services.MachineService;
import storefront.services.ProductService;
import storefront.services.SystemService;

public class Main {
	ProductService theProductService = ProductService.getInstance();
	CustomerService theCustomerService = CustomerService.getInstance();
	MachineService theMachineService = MachineService.getInstance();

	ArrayList<Product> productList = new ArrayList<Product>();
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<Machine> machineList = new ArrayList<Machine>();

	public static void main(String[] args) throws FileNotFoundException {
		Main theMain = new Main();
		theMain.initServices();
		theMain.loadTables();

		theMain.startSimulation();
	}

	public void initServices(){
		if (new File("storefront.db").delete()){

			try {
				DBConnection.getInstance().executeDBScripts("sql/CreateTables.sql");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		try {
			theProductService.readProductsFromFile(new File("input/Products.txt"));		
			theMachineService.readMachinesFromFile(new File("input/Machines.txt"));
			theCustomerService.readCustomersFromFile(new File("input/Customers.txt"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTables(){
			productList = theProductService.retrieveAllProducts();		
			customerList = theCustomerService.retrieveAllCustomers();
			machineList = theMachineService.retrieveAllMachines();
	}



	public void startSimulation(){
		int desiredRounds = 100;
		for (int i = 0; i < desiredRounds; i++) {
			System.out.println("start round " + i);
			round(i);
		}
	}

	public void round(int iter){
		

		theCustomerService.randomlyAssignCustomerMachine(theCustomerService.retrieveAllCustomers());

		for (Customer customer: customerList){

			System.out.println("*******" + customer.getName());

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
