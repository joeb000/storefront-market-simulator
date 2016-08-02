package storefront.entities;

import java.util.ArrayList;

public class Machine {
	
	private int machineID;
	private Location machineLocation;
	private ArrayList<Product> productList;
	
	
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
	public ArrayList<Product> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	@Override
	public String toString() {
		return "Machine [machineID=" + machineID + ", machineLocation=" + machineLocation + ", productList="
				+ productList + "]";
	}
	

}
