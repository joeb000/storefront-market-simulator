package storefront.dao;

import java.sql.SQLException;

import storefront.helpers.DBConnection;

public class ProductDAO {

	public static String TABLE_PRODUCT = "product";
	public static String TABLE_PRODUCT_LOCATION = "product_location";
	public static String TABLE_CUSTOMER_PRODUCT = "customer_product";
	
	
	public int insertNewProduct(String name, float price){
		int retval=0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_PRODUCT);
		sb.append(" (product_name, price) ");

		sb.append(" VALUES (");
		sb.append("\""+ name + "\", ");
		sb.append(price + "); ");
		System.out.println(sb.toString());

		try {
			retval=DBConnection.getInstance().executeAutoIncrementingStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return retval;
	}

	public void insertProductLocationRelation(int productID, int locationID){

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_PRODUCT_LOCATION);
		sb.append(" (product_id, loc_id) ");

		sb.append(" VALUES (");
		sb.append(productID + ", ");
		sb.append(locationID + "); ");

		try {
			DBConnection.getInstance().executeStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
