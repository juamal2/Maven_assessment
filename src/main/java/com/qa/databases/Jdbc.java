package com.qa.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.log4j.Logger;




public class Jdbc {
	
	public static final Logger LOGGER = Logger.getLogger(Jdbc.class);

	private Connection connection;
	private Statement stmt = null;
	private ResultSet resultSet = null;

	
	/**
	 * init Jdbc instance, returns true if connection was complete
	 * @param url database URl eg: jdbc:mysql://localhost:3306/?user=root
	 * @param username database username
	 * @param password database password
	 */
	public boolean init(String url, String username, String password) {
		LOGGER.info("Connecting database...");

		try {
			this.connection = DriverManager.getConnection(url, username, password);
			LOGGER.info("Database connected!");
		    return true;
		} catch (SQLException e) {
			LOGGER.info("Cannot connect to database please contact juamal at juamal27@gmail.com");
			return false;
		}
	}

	/**
	 * Used for selectQuerys returns results to a string ready for console output
	 * @param query SQL Statement to be performed on database Eg: SELECT * FROM TABLE;
	 * @return String("failed") if query fails or result of query : String( id = 1 name = "name")
	 */
	public String selectQuery(String query) {
		String result = "failed";
		if (Boolean.TRUE.equals(isValid())) {
			try {
				this.stmt = this.connection.createStatement();
					this.resultSet = stmt.executeQuery(query);
					result = resultSetToString(resultSet);
			}catch (SQLException ex) {
				LOGGER.info("failed to change result to string");
			}
			finally {
				this.clear();
			}
		}
		return result;
	}
	
	/**
	 * Performs non select query, if primary keys are generated they will be returned
	 * @param query SQL Statement to be performed on database Eg: DELETE FROM table WHERE x = y;
	 * @return value of primary key if key is not generated returns -1
	 */
	public int query(String query) {
		int result =-1;
		if (Boolean.TRUE.equals(isValid())) {
			try {
				this.stmt = this.connection.createStatement();
				stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				String keys = resultSetToString(stmt.getGeneratedKeys());
				return Integer.parseInt(keys.replace("GENERATED_KEY = ", "").trim());
			}catch (SQLException ex) {
				LOGGER.info("Failed to create order invalid inputs try again");
			}catch (NumberFormatException ex) {
			}
			finally {
				this.clear();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	public String resultSetToString(ResultSet rs){
		String result = "";
		try {
			ResultSetMetaData meta = rs.getMetaData();
			while(rs.next()) {
				String row = "";
				for (int i = 1; i <=  meta.getColumnCount(); i++) {
					row +=  meta.getColumnLabel(i) + " = " + rs.getString(i) + "	 ";
				}
				result += "\n" + row ;
			}
		}
		catch(SQLException e) {
			result = "null";
		}

		return result;
	}
	
	
	public void clear() {
		if (this.resultSet != null) {
			try {this.resultSet.close();
			}catch(SQLException sqlEx) {LOGGER.info("failed to close resultset");}
			this.resultSet = null;
		}
		if (this.stmt != null) {
			try {this.stmt.close();
			}catch(SQLException sqlEx) {LOGGER.info("failed to close statement");}
			this.stmt = null;
		}
	}
	

	/**
	 * Returns true if connection to database is still valid and exists
	 * @return 
	 */
	public Boolean isValid() {
		try {
			if (this.connection.isValid(0)) {
				return true;
			}
		}catch (SQLException e) {
			return false;
		}
		return false;
	}
	
}
