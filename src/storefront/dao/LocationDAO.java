package storefront.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Location;
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
	
	public ArrayList<Location> readAllLocationsFromDB(){
		ArrayList<Location> locList = new ArrayList<Location>();
		String sql = "SELECT * FROM " + TABLE_LOCATION;
		ResultSet rs = null;
		try {
			rs = DBConnection.getInstance().executeSelectStatement(sql);
			while (rs.next()) {
				Location loc = new Location();
				loc.setLocationID(rs.getInt("loc_id"));
				loc.setLocationName(rs.getString("loc_name"));
				loc.setLatitude(rs.getDouble("latitude"));
				loc.setLatitude(rs.getDouble("longitude"));
				locList.add(loc);
				System.out.println("LOCATION RETRIEVED:"+loc);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return locList;
	}



}
