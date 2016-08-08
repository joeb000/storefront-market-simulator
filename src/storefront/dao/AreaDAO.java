package storefront.dao;

import java.sql.SQLException;

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
}
