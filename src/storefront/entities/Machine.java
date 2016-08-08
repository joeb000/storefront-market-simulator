package storefront.entities;

import java.util.ArrayList;

public class Machine {
	
	private int machineID;
	private String machineName;
	private double latitude;
	private double longitude;
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<Integer> machineIDList = new ArrayList<Integer>();

	public int getMachineID() {
		return machineID;
	}
	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public ArrayList<Product> getProductList() {
		return productList;
	}
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	public void addProuduct(Product product){
		productList.add(product);
	}
	public ArrayList<Integer> getMachineIDList() {
		return machineIDList;
	}

	public void addMachineID(int mID){
		machineIDList.add(mID);
	}
	@Override
	public String toString() {
		return "Machine [machineID=" + machineID + ", machineName=" + machineName + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", productList=" + productList + ", machineIDList=" + machineIDList
				+ "]";
	}


	
	

}
