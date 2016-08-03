package storefront.entities;

import java.util.ArrayList;

public class Location {
	
	private int locationID;
	private String locationName;
	private double latitude;
	private double longitude;
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<Integer> machineIDList = new ArrayList<Integer>();

	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
		return "Location [locationID=" + locationID + ", locationName=" + locationName + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", productList=" + productList + ", machineIDList=" + machineIDList
				+ "]";
	}


	
	

}
