package edu.csupomona.cs480.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import edu.csupomona.cs480.data.Events;
import edu.csupomona.cs480.util.JDBCUtil;








/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class RestWebController {

	
	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs480/ping
	 */
	@RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK";
	}
	
	@RequestMapping(value = "/cs480/event", method = RequestMethod.GET)
	public static String getEvent(String name) {		
		return "Event ID";
	}
	
	@RequestMapping(value = "/cs480/guava", method = RequestMethod.GET)
	String guava() {		
		Table<String, String, Integer> eventsInfoTable = HashBasedTable.create();
		eventsInfoTable.put("Activity1", "User1", 1);
		eventsInfoTable.put("Activity2", "User2", 2);
		eventsInfoTable.put("Activity3", "User3", 3);
		eventsInfoTable.put("Activity4", "User4", 4);
		
		return eventsInfoTable.toString();
	}

	

	

	@RequestMapping(value = "/cs480/timecheck", method = RequestMethod.GET)
	String liveCheck() {
		String time = java.time.LocalDateTime.now().toString();
		return time;
	}
	
	@RequestMapping(value = "/cs480/secretbase", method = RequestMethod.GET)
	public static String entryPassword() {
		return "To enter the secret base, you must first say the secret password.";
	}
	
	@RequestMapping(value = "/cs480/colorChoice", method = RequestMethod.GET)
	String chooseColor() {
		return "The default color is black, sorry you can't change it!";
		
	}
	@RequestMapping(value = "/cs480/jsoup", method = RequestMethod.GET)
	public static String jsoup() throws IOException {
		Document doc = Jsoup.connect("http://target.com/").get();
		Elements e = doc.select("a");
		return ("Target Finds: " + "\n" + e);
		
	}

	@RequestMapping(value = "/cs480/maps", method = RequestMethod.GET)
	public static String pomonaMap() {
		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey("AIzaSyDcBW_7Xk8gwoyVKlQ3F91thCRtmaDP8ng")
				.build();
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context,
					"13144 Arabella Dr Cerritos, CA 90703").await();
			String thing = results[0].addressComponents[0].longName;
			return thing;

		} catch(Exception e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	@RequestMapping(value = "/cs480/sqlTest", method = RequestMethod.GET)
	String testConnection() {
		String returnThis = "Testing\n";
		try {
			returnThis += "Past Try";
			
			//Get a connection to database
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "SystemBobaDrink321");
			returnThis += "Past Conn";
			
			//Create a statement
			Statement myState = myCon.createStatement();
			returnThis += "Past state";
			
			//Execute SQL Query
			ResultSet myRs = myState.executeQuery("select * from userstable");
			
			//Process the Results
			while (myRs.next()) {
				returnThis += myRs.getString("firstName") + ", " + myRs.getString("lastName") + ", " + 
								   myRs.getString("major") + ".\n";
			}
			
		}
		catch (Exception e) {
			returnThis += e.getCause();
		}
		return returnThis;
	}
	
	@RequestMapping(value = "/cs480/sqlTest2", method = RequestMethod.GET)
	String testJDBC2() {
		JDBCUtil util = new JDBCUtil();
		return util.returnAll();
	}
	
	@RequestMapping(value = "/sqlAddUser/", method = RequestMethod.POST)
	void addUser(
			@RequestParam("firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "major", required = false) String major,
			@RequestParam(value = "email") String email){
		
		
		JDBCUtil util = new JDBCUtil();
		util.addUser(firstName, lastName, email);
	}
	
	
	
	@RequestMapping(value = "/sqlAddEvent/", method = RequestMethod.POST)
	void addEvent(
			@RequestParam(value = "eventName") String eventName,
			@RequestParam(value = "hostEmail") String hostEmail,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "priv") String priv,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "eventTime") String eventTime,
			@RequestParam(value = "eventDate") String eventDate,
			@RequestParam(value = "eventCategory") String eventCategory){
		JDBCUtil util = new JDBCUtil();
		util.addEvent(eventName, hostEmail, description, priv, location, eventTime, eventDate, eventCategory);
	}
	
	@RequestMapping(value = "/sqlGetEventByID/", method = RequestMethod.GET)
	public Events getEventByID(
			@RequestParam(value = "id") String id){
		System.out.println(id);
		
		JDBCUtil util = new JDBCUtil();
		Events event = new Events();
		event = util.getEventByID(id);
		
		return event;
		
//		util.addEvent(eventName, hostID, description, priv, location, eventTime, eventDate);
	}

	@RequestMapping(value = "/sqlGetEventNum/", method = RequestMethod.GET)
	public String getNumberOfEvents() {
		
		JDBCUtil util = new JDBCUtil();
		
		String returnThis = util.getNumberOfEvents();
		
		return returnThis;
	}
	
	@RequestMapping(value="/sqlAddUserEventLink/", method = RequestMethod.POST)
	void addUserEventLink(

		@RequestParam(value = "userID") String userID,
		@RequestParam(value = "eventID") String eventID){
			System.out.println(userID + eventID);
			JDBCUtil util = new JDBCUtil();
			util.addUserEventLink(userID, eventID);
	}
	
	@RequestMapping(value = "/sqlGetUserEventLinkUsers/", method = RequestMethod.GET)
	public String getEventsUserIsApartOf(
			@RequestParam(value = "userID") String userID) {
		JDBCUtil util = new JDBCUtil();
		JSONObject rs = util.getEventsUserIsApartOf(userID);
		
		return rs.toString();
	}
	
	@RequestMapping(value = "/sqlGetUserEvenLinkEvents/", method = RequestMethod.GET)
	public String getUsersApartOfEvent(
			@RequestParam(value = "eventID") String eventID) {
		JDBCUtil util = new JDBCUtil();
		JSONObject rs = util.getUsersApartOfEvent(eventID);
		
		return rs.toString();
	}
	
	@RequestMapping(value = "/sqlGetAllEvents/", method = RequestMethod.GET)
	public ArrayList<Events> getAllEvents() {
		JDBCUtil util = new JDBCUtil();
		
		ArrayList<Events> returnThis = util.getAllEvents();

		return returnThis;
	}
	
	@RequestMapping(value = "/sqlGetEventsUserIsApartOfReturnObject/", method = RequestMethod.GET)
	public ArrayList<Events> sqlGetEventsUserIsApartOfReturnObject(
			@RequestParam(value = "userID") String userID) {
		JDBCUtil util = new JDBCUtil();
		
		ArrayList<Events> rs = util.getEventsUserIsApartOfReturnObject(userID);
		
		return rs;
	}
	
	@RequestMapping(value ="/sqlGetUserIDFromEmail/", method = RequestMethod.GET)
	public String sqlGetUserIDFromEmail(
			@RequestParam(value = "email") String email) {
		JDBCUtil util = new JDBCUtil();
		
		String rs = util.getUserIDFromEmail(email);
		
		return rs;
	}
		
}