package storefront.services;

import java.util.ArrayList;

import org.apache.log4j.Logger;

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

	final static Logger log = Logger.getLogger(SystemService.class);

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



	public int commitPurchase(int cID, int pID, int mID, int dateStamp){
		return dao.insertNewPurchase(dateStamp, pID, mID, pdao.getProductPrice(pID), cID);
	}
	public int commitRequest(int cID, int pID, int mID, int dateStamp){
		return dao.insertNewRequest(dateStamp, pID, mID, cID);
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


	public float calculateOverallEfficiencyRatio(){
		int sales = dao.getTotalSales();
		int reqs = dao.getTotalRequests();

		return (float)sales/(sales + reqs);
	}

	public float calculateMachineEfficiencyRatio(int machineID){
		int machineSales = dao.getMachineTotalSales(machineID);
		int machineReqs = dao.getMachineTotalRequests(machineID);

		return (float)machineSales/(machineSales + machineReqs);
	}
	
	public int calculateMachineTotalSales(int machineID){
		return dao.getMachineTotalSales(machineID);
	}
	
	public float calculateProductScoreForArea(int productID, int areaID){
		int totalAreaSales = dao.getTotalSalesForArea(areaID);
		int productAreaSales = dao.getProductSalesForArea(productID,areaID);

		return  (float)productAreaSales/totalAreaSales;
	}
}
