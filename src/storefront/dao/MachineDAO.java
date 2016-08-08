package storefront.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Machine;
import storefront.helpers.DBConnection;

public class MachineDAO {

	public static String TABLE_MACHINE = "machine";

	public int insertNewMachine(String name, double lat, double lon){
		int retval=0;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_MACHINE);
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
	
	public ArrayList<Machine> readAllMachinesFromDB(){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		String sql = "SELECT * FROM " + TABLE_MACHINE;
		ResultSet rs = null;
		try {
			rs = DBConnection.getInstance().executeSelectStatement(sql);
			while (rs.next()) {
				Machine machine = new Machine();
				machine.setMachineID(rs.getInt("machine_id"));
				machine.setMachineName(rs.getString("loc_name"));
				machine.setLatitude(rs.getDouble("latitude"));
				machine.setLatitude(rs.getDouble("longitude"));
				machineList.add(machine);
				System.out.println("MACHINE RETRIEVED:"+machine);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return machineList;
	}



}
