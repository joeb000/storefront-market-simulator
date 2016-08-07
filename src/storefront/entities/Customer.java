package storefront.entities;


public class Customer {
	
	private int customerID;
	private String name;
	private int age;
	private String gender;
	private int[] products = {0,0,0,0,0,0};
	private int[] locations = {0,0,0,0,0,0};

	
	public Customer(int cID){
		customerID=cID;
	}
	
	public Customer(){

	}
	
	
	private int currentLocationID;
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public int getCurrentLocationID() {
		return currentLocationID;
	}
	public void setCurrentLocationID(int currentLocationID) {
		this.currentLocationID = currentLocationID;
	}
	public int[] getProducts() {
		return products;
	}
	public void addProduct(int pID) {
		products[products.length] = pID;
	}
	
	public int[] getLocations() {
		return locations;
	}
	
	public void addLocation(int locID) {
		for (int i = 0; i < locations.length; i++) {
			if (locations[i]==0){
				locations[i]=locID;
				break;
			}
		}
	}
	
	

}
