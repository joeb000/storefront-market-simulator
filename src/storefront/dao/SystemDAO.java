package storefront.dao;

import java.sql.SQLException;

import storefront.helpers.DBConnection;

public class SystemDAO {
	
	public static String TABLE_PURCHASE = "purchases";
	public static String TABLE_REQUESTS = "requests";

	
	public int insertNewPurchase(int purchaseDate, int productID, int machineID, float price, int customerID){
		int retval=0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_PURCHASE);
		sb.append(" (purchase_date, product_id, machine_id, pos_price, customer_id) ");

		sb.append(" VALUES (");
		sb.append(purchaseDate + ", ");
		sb.append(productID + ", ");
		sb.append(machineID + ", ");
		sb.append(price + ", ");
		sb.append(customerID + "); ");

		try {
			retval=DBConnection.getInstance().executeAutoIncrementingStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return retval;
	}
	
	public int insertNewRequest(int purchaseDate, int productID, int machineID, int customerID){
		int retval=0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_REQUESTS);
		sb.append(" (request_date, product_id, machine_id, customer_id) ");

		sb.append(" VALUES (");
		sb.append(purchaseDate + ", ");
		sb.append(productID + ", ");
		sb.append(machineID + ", ");
		sb.append(customerID + "); ");

		try {
			retval=DBConnection.getInstance().executeAutoIncrementingStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return retval;
	}

}
