package storefront.entities;

import java.util.HashMap;

public class Machine {
	
	private int machineID;
	private Location machineLocation;
	//private ArrayList<Product> productList;
	private HashMap<Integer,Integer> productUnits = new HashMap<Integer,Integer>();
	
	private int capacity;
	
	public int getMachineID() {
		return machineID;
	}
	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
	public Location getMachineLocation() {
		return machineLocation;
	}
	public void setMachineLocation(Location machineLocation) {
		this.machineLocation = machineLocation;
	}
//	public ArrayList<Product> getProductList() {
//		return productList;
//	}
//	public void setProductList(ArrayList<Product> productList) {
//		this.productList = productList;
//	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public HashMap<Integer, Integer> getProductUnits() {
		return productUnits;
	}
	public void setProductUnits(HashMap<Integer, Integer> productUnits) {
		this.productUnits = productUnits;
	}
	public void addProductUnits(int pID, int amount){
		productUnits.put(pID, amount);
	}
	
	
	public void updateProductUnits(int pID, int toSubtract){
		int existing = productUnits.get(pID);
		productUnits.put(productUnits.get(pID), existing - toSubtract);
	}
	
	public boolean hasProductStocked(int pID){
		if (productUnits.containsKey(pID)){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Machine [machineID=" + machineID + ", machineLocation=" + machineLocation 
				 + ", capacity=" + capacity + "]";
	}
	
	

}
