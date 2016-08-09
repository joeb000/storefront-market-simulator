package storefront.services;

import java.util.ArrayList;

import storefront.dao.AreaDAO;
import storefront.dao.CustomerDAO;
import storefront.dao.MachineDAO;
import storefront.dao.ProductDAO;
import storefront.dao.SystemDAO;
import storefront.entities.Area;
import storefront.entities.Customer;
import storefront.entities.Machine;
import storefront.entities.Product;

public class SystemService {
	
	private static SystemService instance = null;
	protected SystemService() {
		// Exists only to defeat instantiation.
	}
	public static SystemService getInstance() {
		if(instance == null) {
			instance = new SystemService();
		}
		return instance;
	}

	private SystemDAO dao = new SystemDAO();
	private ProductDAO pdao = new ProductDAO();
	private MachineDAO mdao = new MachineDAO();
	private CustomerDAO cdao = new CustomerDAO();
	private AreaDAO adao = new AreaDAO();

	
	
	public int commitPurchase(Customer c, Product p, Machine l, int dateStamp){
		return dao.insertNewPurchase(dateStamp, p.getProductID(), l.getMachineID(), p.getPrice(), c.getCustomerID());
	}
	public int commitRequest(Customer c, Product p, Machine l, int dateStamp){
		return dao.insertNewRequest(dateStamp, p.getProductID(), l.getMachineID(), c.getCustomerID());
	}
	
	
	
	public ArrayList<Customer> retrieveAllCustomers() {
		return cdao.readAllCustomersFromDB();
	}
	
	public ArrayList<Machine> retrieveAllMachines() {
		return mdao.readAllMachinesFromDB();
	}
	
	public void initStockMachine(int machineID, int productID, int amount){
		mdao.insertMachineProductAmount(machineID,productID,amount);
	}
	
	public void stockMachine(int machineID, int productID, int amount){
		mdao.updateMachineStockAddProductAmount(machineID,productID,amount);
	}
	
	public void updateProductBought(int machineID, int productID, int amountBought){
		mdao.updateProductBoughtInMachine(productID,machineID,amountBought);
	}
	
	public void updateCapacityForMachine(int machineID, int amountBought){
		mdao.updateOpenCapacity(machineID,amountBought);
	}
	
	public ArrayList<Product> retrieveAllProducts() {
		ArrayList<Product> productList = new ArrayList<Product>();
		//TODO
		productList = pdao.readAllProductsFromDB();
		return productList;
	}
	
	public ArrayList<Area> retrieveAllAreas() {
		return adao.readAllAreasFromDB();
	}
}
