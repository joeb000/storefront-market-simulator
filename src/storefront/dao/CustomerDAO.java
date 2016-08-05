package storefront.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Customer;
import storefront.helpers.DBConnection;

public class CustomerDAO {

	public static String TABLE_CUSTOMER = "customer";
	public static String TABLE_CUSTOMER_LOCATION = "customer_location";
	public static String CUSTOMER_ID = "customer_id";
	public static String TABLE_CUSTOMER_PRODUCT = "customer_product";

	public int insertNewCustomer(String name, int age, String gender){
		int retval=0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_CUSTOMER);
		sb.append(" (fname, age, gender) ");

		sb.append(" VALUES (");
		sb.append("\"" + name + "\", ");
		sb.append(age + ", ");
		sb.append("\"" + gender + "\"); ");

		try {
			retval=DBConnection.getInstance().executeAutoIncrementingStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return retval;
	}

	public void insertCustomerLocationRelation(int customerID, int locationID){

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_CUSTOMER_LOCATION);
		sb.append(" (customer_id, loc_id) ");

		sb.append(" VALUES (");
		sb.append(customerID + ", ");
		sb.append(locationID + "); ");

		try {
			DBConnection.getInstance().executeStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertCustomerProductRelation(int customerID, int productID){			
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_CUSTOMER_PRODUCT);
		sb.append(" (customer_id, product_id) ");

		sb.append(" VALUES (");
		sb.append(customerID + ", ");
		sb.append(productID + "); ");

		try {
			DBConnection.getInstance().executeStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Customer> readAllCustomersFromDB(){
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		String sql = "select * from " + TABLE_CUSTOMER;
		ResultSet rs = null;

		try {
			rs = DBConnection.getInstance().executeSelectStatement(sql);
			while (rs.next()) {
				Customer cust = new Customer();

				cust.setCustomerID(rs.getInt("customer_id"));
				cust.setName(rs.getString("fname"));
				cust.setAge(rs.getInt("age"));
				cust.setGender(rs.getString("gender"));
				customerList.add(cust);
				System.out.println("CUSTOMER RETRIEVED:"+cust);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		sql = "select * from " + TABLE_CUSTOMER_LOCATION;
		rs = null;

		try {
			rs = DBConnection.getInstance().executeSelectStatement(sql);
			while (rs.next()) {
				int cid = rs.getInt("customer_id");
				int lid = rs.getInt("loc_id");
				customerList.get(cid-1).addLocation(lid);
				
				System.out.println("CUSTOMER RETRIEVED:"+customerList.get(cid-1));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return customerList;
	}

}
