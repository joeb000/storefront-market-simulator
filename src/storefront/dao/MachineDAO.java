package storefront.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storefront.entities.Machine;
import storefront.helpers.DBConnection;

public class MachineDAO {

	public static String TABLE_MACHINE = "machine";

	public int insertNewMachine(String name, double lat, double lon, int areaID){
		int retval=0;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(TABLE_MACHINE);
		sb.append(" (loc_name, latitude, longitude, area_id) ");

		sb.append(" VALUES (");
		sb.append("\"" + name + "\", ");
		sb.append(lat + ", ");
		sb.append(lon + ", ");

		sb.append(areaID + "); ");

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
				machine.setLongitude(rs.getDouble("longitude"));
				machine.setAreaID(rs.getInt("area_id"));

				machineList.add(machine);
				System.out.println("MACHINE RETRIEVED:"+machine);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return machineList;
	}

	public void updateOpenCapacity(int machineID, int amountBought) {
		String sql = "UPDATE machine SET capacity=(capacity+" + amountBought + ") WHERE machine_id=" +machineID;
		try {
			DBConnection.getInstance().executeStatement(sql);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateProductBoughtInMachine(int productID, int machineID, int amountBought) {
		String sql = "UPDATE product_machine SET stock=(stock-" + amountBought + ") WHERE machine_id=" +machineID + " AND product_id="+productID;
		try {
			DBConnection.getInstance().executeStatement(sql);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateMachineStockAddProductAmount(int machineID, int productID, int amount) {
		String sql = "UPDATE product_machine SET stock=(stock+" + amount + ") WHERE machine_id=" +machineID + " AND product_id="+productID;
		try {
			DBConnection.getInstance().executeStatement(sql);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	public void insertMachineProductAmount(int machineID, int productID, int amount) {
		String sql = "INSERT INTO product_machine (product_id,machine_id,stock) VALUES (" + productID +"," + machineID +"," + amount + ")";
		try {
			DBConnection.getInstance().executeStatement(sql);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}

}
