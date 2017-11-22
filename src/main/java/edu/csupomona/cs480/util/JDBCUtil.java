package edu.csupomona.cs480.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.api.jdbc.Statement;

import org.json.*;

import edu.csupomona.cs480.data.Events;
import edu.csupomona.cs480.data.User;

public class JDBCUtil {
	final static String DATABASE = "users";
	final static String SERVER   = "mydatabase.ckoxrzfooypv.us-east-2.rds.amazonaws.com"; //RDS dns 
	final static String USERNAME = "systembobadrink";
	final static String PASSWORD = "databasepassword";
	
	public Connection conn;
	
	public JDBCUtil() {
		//Basic constructor. Calls connectToDB to establish initial MySQL connection.
		conn = connectToDB();
	}
	
	public Connection connectToDB() {
		//Establishes connection to the MySQL server. Called upon creation of JDBC Util for use by all following methods.

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
		//Returns all users from the 'users' table.

		java.sql.Statement statement;
		
		String ret = "";
		
		try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM `basicDB`.`users`";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				ret += "ID: " + rs.getInt("id");
				ret += ", firstName: " + rs.getString("firstName");
				ret += ", lastName: " + rs.getString("lastName");
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
	
	public void addUser(String firstName, String lastName) {
		//Used with the addUser method found in RESTController.
		//Used to insert users into the users table, with parameters firstName and lastName.
		
		java.sql.Statement statement;
		
		try {
			statement = conn.createStatement();
			String sql = "INSERT INTO `basicDB`.`users` (`firstName`, `lastName`) VALUES ('" + firstName + "', '" + lastName + "');";			
			statement.executeUpdate(sql);
			
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUserByID(String id) {
		//Returns the information of the user with the specified ID.
		
		java.sql.Statement statement;
				
		User user = new User();
		
		
		try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM `basicDB`.`users` WHERE id= " + id + ";";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Integer myInt = rs.getInt("id");
				user.setId(myInt.toString());
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				System.out.println("end of while loop");
			}		
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}
	
	public void addEvent(String eventName, String hostID, String description, String priv, String location, String eventTime, String eventDate, String eventCategory) {
		//Used with the addEvent method found in RESTController.
		//Used to insert events into the event table, with the above parameters.
		
		java.sql.Statement statement;
		System.out.println(eventCategory);
		try {
			statement = conn.createStatement();
			String sql = "INSERT INTO `basicDB`.`events` (`name`, `host`, `description`, `location`, `private`, `eventDate`, `eventTime`, `category`) VALUES "
					   + "('" + eventName + "', '" + hostID + "', '" + description + "', '" + location + "', '" + priv + "', '" + eventDate + "', '" + eventTime + "', '" + eventCategory + "');";			
			statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Events getEventByID(String id) {
		//Returns the information of the event with the specified ID.
		
		java.sql.Statement statement;
		
		Events event = new Events();
		
		try {
			statement = conn.createStatement();
			String sql = "SELECT * FROM `basicDB`.`events` WHERE id= " + id + ";";
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Integer myInt = rs.getInt("id");
				event.setEventID(myInt.toString());
				event.setName(rs.getString("name"));
				event.setHostID(rs.getString("host"));
				event.setDescription(rs.getString("description"));
				event.setLocation(rs.getString("location"));
				event.setPriv(rs.getString("private"));
				event.setEventDate(rs.getString("eventDate"));
				event.setEventTime(rs.getString("eventTime"));
				event.setCategory(rs.getString("category"));
			}
			rs.close();
			statement.close();
			conn.close();
			
		}catch (SQLException e) {
				e.printStackTrace();
		}
			return event;
	} 
	
	public String getNumberOfEvents() {
		java.sql.Statement statement;
		
		Integer ret = -1;
		try {
			statement = conn.createStatement();
			String sql = "SELECT COUNT(*) FROM basicDB.events;";
			ResultSet rs = statement.executeQuery(sql);
			
			rs.next(); //Needed to bring cursor to first row in returned table.

			ret = rs.getInt("COUNT(*)");
			
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toString();
		
	}
	
	public void addUserEventLink(String userID, String eventID) {
		java.sql.Statement statement;
		
		try {
			statement = conn.createStatement();
			String sql = "INSERT INTO `basicDB`.`user_event_link` (`userID`, `eventID`) VALUES ('" + userID + "', '" + eventID +"');";
			statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public JSONObject getEventsUserIsApartOf(String userID) {
		//Returns the events a user is a part of.
		java.sql.Statement statement;
		
		ResultSet rs = null;
		JSONObject json = new JSONObject();
		try {
			statement = conn.createStatement();
			String sql = "SELECT eventID FROM `basicDB`.`user_event_link` WHERE userID= " + userID + ";";
			rs = statement.executeQuery(sql);
			System.out.println(rs);

			Integer counter = 1;
			while(rs.next()) {
				try {
					json.put(counter.toString(), rs.getInt(1));
				} catch(Exception e){
					e.getMessage();
				}
				counter++;
			}
			rs.close();
			statement.close();
			conn.close();			
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return json;
	}

	public JSONObject getUsersApartOfEvent(String eventID) {
		//Returns the events a user is a part of.
		java.sql.Statement statement;
		
		ResultSet rs = null;
		JSONObject json = new JSONObject();
		try {
			statement = conn.createStatement();
			String sql = "SELECT userID FROM `basicDB`.`user_event_link` WHERE eventID= " + eventID + ";";
			rs = statement.executeQuery(sql);
			System.out.println(rs);

			Integer counter = 1;
			while(rs.next()) {
				try {
					json.put(counter.toString(), rs.getInt(1));
				} catch(Exception e){
					e.getMessage();
				}
				counter++;
			}
			rs.close();
			statement.close();
			conn.close();			
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return json;
	}
}