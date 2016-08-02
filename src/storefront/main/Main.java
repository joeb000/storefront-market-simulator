package storefront.main;

import java.io.File;
import java.io.FileNotFoundException;

import storefront.services.CustomerService;
import storefront.services.LoactionService;
import storefront.services.ProductService;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Main theMain = new Main();
		theMain.initServices();
	}
	
	public void initServices(){
		ProductService theProductService = ProductService.getInstance();
		CustomerService theCustomerService = CustomerService.getInstance();
		LoactionService theLoactionService = LoactionService.getInstance();
		try {
			theProductService.readProductsFromFile(new File("input/Products.txt"));		
			System.out.println(theProductService.getProductsByID(1));
			theCustomerService.readCustomersFromFile(new File("input/Customers.txt"));
			theLoactionService.readLocationsFromFile(new File("input/Locations.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
