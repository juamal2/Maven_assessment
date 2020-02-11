package com.qa.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.runner.Runner;


public class Jdbc {
	
	public static final Logger LOGGER = Logger.getLogger(Runner.class);
	private String url;
	private String username;
	private String password;
	private Connection connection;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ResultSet rstest = null;
	
	/**
	 * init Jdbc instance, returns true if connection was complete
	 * @param url database URl eg: jdbc:mysql://localhost:3306/?user=root
	 * @param username database username
	 * @param password database password
	 */
	public boolean init(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;

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
	 * 
	 * @param query SQL Statement to be performed on database Eg: SELECT * FROM TABLE;
	 * @return String("failed") if query fails or result of query : String( id = 1 name = "name")
	 */
	public String selectQuery(String query) {
		String Result = "failed";
		if (this.isValid()) {
			try {
				this.stmt = this.connection.createStatement();
					this.rs = stmt.executeQuery(query);
					Result = resultSet_toString(rs);
			}catch (SQLException ex) {
			}finally {
				this.clear();
			}
		}
		return Result;
	}
	
	public int Query(String query) {
		int result =-1;
		if (this.isValid()) {
			try {
				this.stmt = this.connection.createStatement();
				stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				if (null != stmt.getGeneratedKeys()) {
					String keys = resultSet_toString(stmt.getGeneratedKeys());
					try {
					return Integer.parseInt(keys.replace("GENERATED_KEY = ", "").trim());
					}catch(NumberFormatException e) {
					}
				}
			}catch (SQLException ex) {
				LOGGER.info("Failed to create order invalid inputs try again");
			}finally {
				this.clear();
			}
		}
		return result;
	}
	
	
	public String resultSet_toString(ResultSet rs){
		String Result = "";
		try {
			ResultSetMetaData meta = rs.getMetaData();
			while(rs.next()) {
				String row = "";
				for (int i = 1; i <=  meta.getColumnCount(); i++) {
					row +=  meta.getColumnLabel(i) + " = " + rs.getString(i) + "	 ";
				}
				Result += "\n" + row ;
			}
		}
		catch(SQLException e) {
			Result = "null";
		}

		return Result;
	}
	
	
	public void clear() {
		if (this.rs != null) {
			try {this.rs.close();
			}catch(SQLException sqlEx) {}
			this.rs = null;
		}
		if (this.stmt != null) {
			try {this.stmt.close();
			}catch(SQLException sqlEx) {}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
