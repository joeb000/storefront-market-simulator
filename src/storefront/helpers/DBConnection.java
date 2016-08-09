package storefront.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static Connection c = null;
	private Statement stmt = null;

	public DBConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:storefront.db");
	}

	private static DBConnection instance = null;
	public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public void executeStatements(String[] sql) throws SQLException{
		stmt = c.createStatement();

		for (int i = 0; i < sql.length; i++) {
			stmt.executeUpdate(sql[i]);
		}
		
	}
	
	public int executeAutoIncrementingStatement(String sql) throws SQLException{
	    PreparedStatement statement = null;

	    statement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    statement.executeUpdate();
	    ResultSet rs = stmt.getGeneratedKeys();
	    rs.next();
	    int i = rs.getInt(1);
		return i;
	}
	
	public ResultSet executeSelectStatement(String sql) throws SQLException{
		stmt = c.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public int executeSingleValueStatement(String sql) throws SQLException{
		stmt = c.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
	    return rs.getInt(0);
	}
	
	public float executeSingleValueFloatStatement(String sql) throws SQLException{
		stmt = c.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);
	    return rs.getFloat(1);
	}
	
	public void executeStatement(String sql) throws SQLException{
		stmt = c.createStatement();

		stmt.executeUpdate(sql);
	 
	}

	public void executeDBScripts(String aSQLScriptFilePath) throws IOException,SQLException {
		try {
			stmt = c.createStatement();
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
	}

	public void commitAndClose() throws SQLException{
		stmt.close();
		//c.commit();
		c.close();
	}
}
