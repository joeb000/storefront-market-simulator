package storefront.services;

import storefront.dao.SystemDAO;
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

	SystemDAO dao = new SystemDAO();
	
	
	public int commitPurchase(Customer c, Product p, Machine l, int dateStamp){
		return dao.insertNewPurchase(dateStamp, p.getProductID(), l.getMachineID(), p.getPrice(), c.getCustomerID());
	}
	public int commitRequest(Customer c, Product p, Machine l, int dateStamp){
		return dao.insertNewRequest(dateStamp, p.getProductID(), l.getMachineID(), c.getCustomerID());
	}
}
