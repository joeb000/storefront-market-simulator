package storefront.dao;

import java.sql.SQLException;

import storefront.helpers.DBConnection;

public class CustomerDAO {
	
	public static String TABLE_CUSTOMER = "customer";
	public static String TABLE_CUSTOMER_LOCATION = "customer_location";
	public static String CUSTOMER_ID = "customer_id";

	
	public int insertNewCustomer(String name, int age, char gender){
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_CUSTOMER);
		sb.append(" (fname, age, gender) ");
		
		sb.append(" VALUES (");
		sb.append(name + ", ");
		sb.append(age + ", ");
		sb.append(gender + "); ");
		
		try {
			DBConnection.getInstance().executeStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public void insertCustomerLocationRelation(int customerID, int locationID){
		String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_CUSTOMER_LOCATION);
		sb.append(" (customer_id, loc_id) ");
		
		sb.append(" VALUES (");
		sb.append(customerID + ", ");
		sb.append(locationID + "); ");

		
	}
	
	public void insertCustomerProductRelation(int customerID, int productID){
		
	}
	

}
