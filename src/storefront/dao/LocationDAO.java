package storefront.dao;

import java.sql.SQLException;

import storefront.helpers.DBConnection;

public class LocationDAO {

	public static String TABLE_LOCATION = "loc";
	public static String TABLE_CUSTOMER_PRODUCT = "customer_product";

	public int insertNewLocation(String name, double lat, double lon){
		int retval=0;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_LOCATION);
		sb.append(" (loc_name, latitude, longitude) ");

		sb.append(" VALUES (");
		sb.append("\"" + name + "\", ");
		sb.append(lat + ", ");
		sb.append(lon + "); ");

		try {
			retval=DBConnection.getInstance().executeAutoIncrementingStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return retval;

	}


}
