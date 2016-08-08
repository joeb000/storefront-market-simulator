package storefront.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Area;
import storefront.helpers.DBConnection;

public class AreaDAO {

	public static String TABLE_AREA = "area";

	public int insertNewArea(String name){
		int retval=0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_AREA);
		sb.append(" (name) ");

		sb.append(" VALUES (");

		sb.append("\"" + name + "\"); ");

		try {
			retval=DBConnection.getInstance().executeAutoIncrementingStatement(sb.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return retval;
	}
	
	public ArrayList<Area> readAllAreasFromDB(){
		ArrayList<Area> areaList = new ArrayList<Area>();
		String sql = "SELECT * FROM " + TABLE_AREA;
		ResultSet rs = null;
		try {
			rs = DBConnection.getInstance().executeSelectStatement(sql);
			while (rs.next()) {
				Area area = new Area();
				area.setAreaID(rs.getInt("area_id"));
				area.setName(rs.getString("area_name"));
				areaList.add(area);
				System.out.println("AREA RETRIEVED:"+area);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return areaList;
	}

}
