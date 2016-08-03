package storefront.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static Connection c = null;
	private Statement stmt = null;

	public DBConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = c.createStatement();
	}

	private static DBConnection instance = null;
	public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public void executeCreate() throws SQLException{
		stmt = c.createStatement();
		String sql = "CREATE TABLE COMPANY " +
				"(ID INT PRIMARY KEY     NOT NULL," +
				" NAME           TEXT    NOT NULL, " + 
				" AGE            INT     NOT NULL, " + 
				" ADDRESS        CHAR(50), " + 
				" SALARY         REAL)"; 
		stmt.executeUpdate(sql);

		commitAndClose();
	}


	public void executeInsert() throws SQLException{
		stmt = c.createStatement();
		String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
		stmt.executeUpdate(sql);

		sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
		stmt.executeUpdate(sql);

		sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
		stmt.executeUpdate(sql);

		sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
				"VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
		stmt.executeUpdate(sql);

		commitAndClose();
	}

	public void executeDBScripts(String aSQLScriptFilePath) throws IOException,SQLException {
		try {
			BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = in.readLine()) != null) {
				sb.append(str + "\n ");
			}
			in.close();
			stmt.executeUpdate(sb.toString());
		} catch (Exception e) {
			System.err.println("Failed to Execute " + aSQLScriptFilePath +". The error is "+ e.getMessage());
			e.printStackTrace();
		} 
		commitAndClose();
	}

	void commitAndClose() throws SQLException{
		stmt.close();
		//c.commit();
		c.close();
	}
}
