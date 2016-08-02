package storefront.main;

import java.io.File;
import java.io.FileNotFoundException;

import storefront.services.CustomerService;
import storefront.services.LocationService;
import storefront.services.MachineService;
import storefront.services.ProductService;

public class Main {
	ProductService theProductService = ProductService.getInstance();
	CustomerService theCustomerService = CustomerService.getInstance();
	LocationService theLoactionService = LocationService.getInstance();
	MachineService theMachineService = MachineService.getInstance();
	
	public static void main(String[] args) throws FileNotFoundException {
		Main theMain = new Main();
		theMain.initServices();
	}
	
	public void initServices(){

		try {
			theProductService.readProductsFromFile(new File("input/Products.txt"));		
			System.out.println(theProductService.getProductsByID(1));
			theCustomerService.readCustomersFromFile(new File("input/Customers.txt"));
			theLoactionService.readLocationsFromFile(new File("input/Locations.txt"));
			theMachineService.createOneMachinePerLocation();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
