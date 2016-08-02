package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
		customer.setCustomerID(customerIDCounter);
		customer.setName(attributes[0]);
		customer.setAge(Integer.parseInt(attributes[1]));
		customer.setGender(attributes[2]);

		for (int i = 0; i < products.length; i++) {
			customer.addProuductPreference(ProductService.getInstance().getProductsByID(Integer.parseInt(products[i])));
		}
		
		System.out.println(customer.toString());
		return customer;
	}
	
	
	
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	

}
