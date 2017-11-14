package edu.csupomona.cs480.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.api.jdbc.Statement;

public class JDBCUtil {
	final static String DATABASE = "users";
//	final static String SERVER = "ec2-18-216-62-215.us-east-2.compute.amazonaws.com"; //EC2 dns
	final static String SERVER = "mydatabase.ckoxrzfooypv.us-east-2.rds.amazonaws.com"; //RDS dns 
	final static String USERNAME = "systembobadrink";
	final static String PASSWORD ="databasepassword";
	
	public Connection conn;
	
	public JDBCUtil() {
		conn = connectToDB();
	}
	
	public Connection connectToDB() {

	    System.out.println("----MySQL JDBC Connection Testing -------");
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("Where is your MySQL JDBC Driver?");
	        e.printStackTrace();
	        return null;
	    }

	    System.out.println("MySQL JDBC Driver Registered!");
	    Connection connection = null;

	    try {
	        connection = DriverManager.
	                getConnection("jdbc:mysql://" + SERVER + ":" + 3306 + "/" + DATABASE, USERNAME, PASSWORD);
	    } catch (SQLException e) {
	        System.out.println("Connection Failed!:\n" + e.getMessage());
	    }

	    if (connection != null) {
	        System.out.println("it works");
	    } else {
	        System.out.println("something went wrong during the connection");
	    }

	    return connection;
	}
	

	public String returnAll() {
//		Connection connect = connectToDB();
		java.sql.Statement statement;
		
		String ret = "";
		
		try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM testTable";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				ret += "ID: " + rs.getInt("id");
				ret += ", name: " + rs.getString("name");
			}
			
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
		
	}
	
	public void AddUser(String firstName, String lastName) {
		java.sql.Statement statement;
		
		try {
			statement = conn.createStatement();
			String sql = "INSERT INTO `basicDB`.`users` (`firstName`, `lastName`) VALUES (" + firstName +", " + lastName + ");";
			statement.executeQuery(sql);
			
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
