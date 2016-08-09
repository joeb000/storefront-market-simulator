package storefront.entities;

import java.util.ArrayList;

public class Machine {
	
	private int machineID;
	private String machineName;
	private double latitude;
	private double longitude;
	private int areaID;
	public int getAreaID() {
		return areaID;
	}
	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}

	private ArrayList<Integer> productList = new ArrayList<Integer>();

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
	
	public ArrayList<Integer> getProductList() {
		return productList;
	}

	public void addProuduct(Integer productid){
		productList.add(productid);
	}


	
	

}
