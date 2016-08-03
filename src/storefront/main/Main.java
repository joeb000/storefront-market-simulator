package storefront.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Customer;
import storefront.entities.Location;
import storefront.entities.Machine;
import storefront.entities.Product;
import storefront.helpers.DBConnection;
import storefront.services.CustomerService;
import storefront.services.LocationService;
import storefront.services.MachineService;
import storefront.services.ProductService;

public class Main {
	ProductService theProductService = ProductService.getInstance();
	CustomerService theCustomerService = CustomerService.getInstance();
	LocationService theLocationService = LocationService.getInstance();
	MachineService theMachineService = MachineService.getInstance();

	public static void main(String[] args) throws FileNotFoundException {
		Main theMain = new Main();
		theMain.initServices();
		theMain.startSimulation();
	}

	public void initServices(){
		try {
			try {
				//	DBConnection.getInstance().executeCreate();
				//DBConnection.getInstance().executeInsert();
				DBConnection.getInstance().executeDBScripts("sql/CreateTables.sql");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			theProductService.readProductsFromFile(new File("input/Products.txt"));		
			System.out.println(theProductService.getProductsByID(1));
			theLocationService.readLocationsFromFile(new File("input/Locations.txt"));
			theCustomerService.readCustomersFromFile(new File("input/Customers.txt"));
			theMachineService.createOneMachinePerLocation();

			theCustomerService.randomlyAssignCustomerLocation();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void startSimulation(){
		int desiredRounds = 1000;
		for (int i = 0; i < desiredRounds; i++) {
			System.out.println("start round " + i);
			round(i);
		}
	}

	public void round(int iter){

		for (Customer customer: theCustomerService.getCustomerList()){
			System.out.println("*******" + customer.getName());
			for (Product custProduct : customer.getPreferences()){

				Location customerLocation = theLocationService.getLocationByID(customer.getCurrentLocationID());
				if(customerLocation!=null) { 
					if (customerLocation.getMachineIDList().size()>=1){
						int closestMachineID=customerLocation.getMachineIDList().get(0);
						Machine closestMachine = theMachineService.getMachineByID(closestMachineID);
						if (closestMachine.hasProductStocked(custProduct.getProductID())){
							recordTransaction(customer,custProduct,closestMachine,iter);
						}
					} else {
						System.out.println("x");
					}
				} else {
					System.out.println(customer.getCurrentLocationID());
					System.out.println("y");
				}
			}

		}
	}

	public void recordTransaction(Customer c, Product p, Machine m, int round){
		System.out.println("Customer: "+ c.getName() + " bought product: " + p.getProductName() + " from MachineID:" + m.getMachineID() + " at location: " + m.getMachineLocation().getLocationName() );
	}

}
