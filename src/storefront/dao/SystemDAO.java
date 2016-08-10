package storefront.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import storefront.helpers.DBConnection;

public class SystemDAO {
	
	final static Logger log = Logger.getLogger(SystemDAO.class);

	
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
	
	public int getTotalSales(){
		String sql = "SELECT COUNT(*) FROM purchases";
		try {
			return DBConnection.getInstance().executeSingleValueStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		log.error("Error retrieving total sales");
		return 0;
	}
	
	public int getTotalRequests(){
		String sql = "SELECT COUNT(*) FROM requests";
		try {
			return DBConnection.getInstance().executeSingleValueStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		log.error("Error retrieving total requests");
		return 0;
	}

	public int getMachineTotalSales(int machineID) {
		String sql = "SELECT COUNT(*) FROM purchases WHERE machine_id=" + machineID;
		try {
			return DBConnection.getInstance().executeSingleValueStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		log.error("Error retrieving machine total sales");
		return 0;
	}

	public int getMachineTotalRequests(int machineID) {
		String sql = "SELECT COUNT(*) FROM requests WHERE machine_id=" + machineID;
		try {
			return DBConnection.getInstance().executeSingleValueStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		log.error("Error retrieving machine total requests");
		return 0;
	}

	public int getTotalSalesForArea(int areaID) {
		String sql = "SELECT DISTINCT COUNT(*) FROM purchases p, machine m WHERE m.machine_id = p.machine_id AND m.area_id = " + areaID;
		try {
			return DBConnection.getInstance().executeSingleValueStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		log.error("Error retrieving total sales for area " + areaID);
		return 0;
	}

	public int getProductSalesForArea(int productID, int areaID) {
		String sql = "SELECT DISTINCT COUNT(*) FROM purchases p, machine m WHERE m.machine_id = p.machine_id AND p.product_id ="
				+ productID + " AND m.area_id = " + areaID;
		try {
			return DBConnection.getInstance().executeSingleValueStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		log.error("Error retrieving product " + productID +" sales for area " + areaID);
		return 0;
	}

	
}
