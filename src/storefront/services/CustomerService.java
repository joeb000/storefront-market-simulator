package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import storefront.entities.Customer;

public class CustomerService {

	private static CustomerService instance = null;
	protected CustomerService() {
		// Exists only to defeat instantiation.
	}
	public static CustomerService getInstance() {
		if(instance == null) {
			instance = new CustomerService();
		}
		return instance;
	}
	
	public int customerIDCounter = 0;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();

	public ArrayList<Customer> readCustomersFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			customerList.add(parseStringToCustomer(line));
			customerIDCounter++;
		}

		return customerList;
	}

	private Customer parseStringToCustomer(String customerString){
		ProductService.getInstance();
		Customer customer = new Customer();
		String[] split = customerString.split("\"");
		String[] attributes = split[0].split(",");
		String[] products = split[1].split(",");
		String[] locations = split[3].split(",");

		customer.setCustomerID(customerIDCounter);
		customer.setName(attributes[0]);
		customer.setAge(Integer.parseInt(attributes[1]));
		customer.setGender(attributes[2]);

		for (int i = 0; i < products.length; i++) {
			customer.addProuductPreference(ProductService.getInstance().getProductsByID(Integer.parseInt(products[i])));
		}
		for (int i = 0; i < locations.length; i++) {
			customer.addLocationPreference(LocationService.getInstance().getLocationByID(Integer.parseInt(locations[i])));
		}
		
		
		System.out.println(customer.toString());
		return customer;
	}
	
	public void randomlyAssignCustomerLocation(){
		Random rand = new Random();
		for (Customer customer : customerList){
			int randInt = rand.nextInt(LocationService.getInstance().getLocationList().size());
			customer.setCurrentLocationID(randInt +1);
			System.out.println("## CUSTOMER: " + customer.getName() + " locationID:" + randInt);
		}
	}
	
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	

}
