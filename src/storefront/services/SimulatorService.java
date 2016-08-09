package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import storefront.dao.AreaDAO;
import storefront.dao.CustomerDAO;
import storefront.dao.MachineDAO;
import storefront.dao.ProductDAO;
import storefront.entities.Area;
import storefront.entities.Customer;
import storefront.entities.Machine;
import storefront.entities.Product;

public class SimulatorService {

	private static SimulatorService instance = null;
	protected SimulatorService() {
		// Exists only to defeat instantiation.
	}
	public static SimulatorService getInstance() {
		if(instance == null) {
			instance = new SimulatorService();
		}
		return instance;
	}


	private ProductDAO pdao = new ProductDAO();
	private MachineDAO mdao = new MachineDAO();
	private CustomerDAO cdao = new CustomerDAO();
	private AreaDAO adao = new AreaDAO();



	/*
	 * PRODUCT
	 * 
	 */


	public void readProductsFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			Product aProduct = parseStringToProduct(line);
			System.out.println("Product ID: #"+commitNewProduct(aProduct)+" added to DB");
		}
	}

	private Product parseStringToProduct(String productString){
		Product product = new Product();
		String[] attributes = productString.split(",");
		product.setProductID(Integer.parseInt(attributes[0]));
		product.setProductName(attributes[1]);
		product.setPrice(Float.parseFloat(attributes[2]));
		System.out.println(product.toString());
		return product;

	}
	public int commitNewProduct(Product p){
		return pdao.insertNewProduct(p.getProductName(), p.getPrice());
	}


	/*
	 * MACHINE
	 * 
	 */


	public void readMachinesFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			Machine loc = parseStringToMachine(line);
			int machineid = commitNewMachine(loc);
			System.out.println("New Machine "+ loc.getMachineName() + "(#"+machineid + " ) added to DB");
		}
	}

	private Machine parseStringToMachine(String machineString){
		Machine machine = new Machine();
		String[] split = machineString.split("\"");
		String[] attributes = split[0].split(",");
		String[] pList = split[1].split(",");
		machine.setMachineID(Integer.parseInt(attributes[0]));
		machine.setMachineName(attributes[1]);
		machine.setLatitude(Double.parseDouble(attributes[2]));
		machine.setLongitude(Double.parseDouble(attributes[3]));
		machine.setAreaID(Integer.parseInt(attributes[4]));
		for (int i = 0; i < pList.length; i++) {
			machine.addProuduct(Integer.parseInt(pList[i]));
		}
		return machine;
	}



	public int commitNewMachine(Machine m){
		return mdao.insertNewMachine(m.getMachineName(),m.getLatitude(),m.getLongitude(),m.getAreaID());
	}


	/*
	 * CUSTOMER
	 * 
	 */


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

		customer.setName(attributes[0]);
		customer.setAge(Integer.parseInt(attributes[1]));
		customer.setGender(attributes[2]);
		customer.setAreaID(Integer.parseInt(attributes[3]));
		int customerid = commitNewCustomer(customer);
		System.out.println("CustID: "+ customerid + " assigned to " + customer.getName());

		for (int i = 0; i < products.length; i++) {
			commitCustomerProductRelation(customerid,Integer.parseInt(products[i]),50);
		}
	}

	public void randomlyAssignCustomerMachine(Customer customer){
		Random rand = new Random();
		int a = customer.getAreaID();
		ArrayList<Integer> intList = adao.machinesInArea(a);
		if (!intList.isEmpty()){
			int[] ints = new int[intList.size()];
			for(int i=0, len = intList.size(); i < len; i++)
				ints[i] = intList.get(i);
			int randInt = pickOne(ints);
			customer.setCurrentMachineID(randInt);

			System.out.println("## CUSTOMER: " + customer.getName() + " machineID:" + randInt);
		}
		else {
			customer.setCurrentMachineID(0);
			System.out.println("#!# CUSTOMER: " + customer.getName() + " machineID not found...setting to 0" );
		}
	}

	public int pickOne(int[] ints){
		System.out.println(ints.length);
		Random rand = new Random();
		return ints[rand.nextInt(ints.length)];
	}


	public int commitNewCustomer(Customer c){
		return cdao.insertNewCustomer(c.getName(), c.getAge(),c.getGender(),c.getAreaID());
	}
	public void commitCustomerProductRelation(int cID, int pID, int probability){
		cdao.insertCustomerProductRelation(cID, pID, probability);
	}

	/*
	 * AREA
	 * 
	 */

	public void readAreasFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			parseStringToArea(line);
		}
	}

	private void parseStringToArea(String customerString){
		Area area = new Area();
		String[] attributes = customerString.split(",");

		area.setName(attributes[1]);

		int areaID = commitNewArea(area);
		area.setAreaID(areaID);

		System.out.println("Area: "+ areaID + " created: " + area.getName());

	}

	public int commitNewArea(Area a){
		return adao.insertNewArea(a.getName());
	}

}
