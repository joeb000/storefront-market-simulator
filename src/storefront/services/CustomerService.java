package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import storefront.dao.CustomerDAO;
import storefront.entities.Customer;
import storefront.entities.Product;

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
	
	CustomerDAO dao = new CustomerDAO();
	

	public void readCustomersFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			parseStringToCustomer(line);
		}
	}

	private void parseStringToCustomer(String customerString){
		Customer customer = new Customer();
		String[] split = customerString.split("\"");
		String[] attributes = split[0].split(",");
		String[] products = split[1].split(",");
		String[] locations = split[3].split(",");

		customer.setName(attributes[0]);
		customer.setAge(Integer.parseInt(attributes[1]));
		customer.setGender(attributes[2]);

		int customerid = commitNewCustomer(customer);
		System.out.println("CustID: "+ customerid + " assigned to " + customer.getName());

		for (int i = 0; i < products.length; i++) {
			commitCustomerProductRelation(customerid,Integer.parseInt(products[i]));
		}
		
		for (int i = 0; i < locations.length; i++) {
			commitCustomerLocationRelation(customerid,Integer.parseInt(locations[i]));
		}
	}
	
	public void randomlyAssignCustomerLocation(ArrayList<Customer> cList){
		Random rand = new Random();
		for (Customer customer : cList){
			//TODO
			int randInt = rand.nextInt(2) +1;
			customer.setCurrentLocationID(randInt);
			System.out.println("## CUSTOMER: " + customer.getName() + " locationID:" + randInt);
		}
	}

	
	public int commitNewCustomer(Customer c){
		return dao.insertNewCustomer(c.getName(), c.getAge(),c.getGender());
	}
	
	public void commitCustomerProductRelation(int cID, int pID){
		dao.insertCustomerProductRelation(cID, pID);
	}
	
	public void commitCustomerLocationRelation(int cID, int lID){
		dao.insertCustomerLocationRelation(cID, lID);
	}
	
	public ArrayList<Customer> retrieveAllCustomers() {
		return dao.readAllCustomersFromDB();
	}
}
