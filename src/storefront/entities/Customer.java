package storefront.entities;

import java.util.ArrayList;

public class Customer {
	
	private int customerID;
	private String name;
	private int age;
	private String gender;
	private ArrayList purchaseHistory;
	private ArrayList<Product> preferences = new ArrayList<Product>();
	
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
	public ArrayList getPurchaseHistory() {
		return purchaseHistory;
	}
	public void setPurchaseHistory(ArrayList purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}
	public ArrayList<Product> getPreferences() {
		return preferences;
	}
	public void setPreferences(ArrayList<Product> preferences) {
		this.preferences = preferences;
	}
	public void addProuductPreference(Product product){
		preferences.add(product);
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", purchaseHistory=" + purchaseHistory + ", preferences=" + preferences + "]";
	}
	

}
