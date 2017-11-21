package edu.csupomona.cs480.controller;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.Events;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.util.JDBCUtil;

import org.json.*;

import java.sql.*;






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
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

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

	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs480/user/user101
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}
	

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs480/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
//	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
//	User updateUser(
//			@PathVariable("userId") String id,
//			@RequestParam("name") String name,
//			@RequestParam(value = "major", required = false) String major) {
//		User user = new User();
//		user.setId(id);
//		user.setMajor(major);
//		user.setName(name);
//		userManager.updateUser(user);
//		return user;
//	} 
// I commented this out because I edited the USER class and it messed this up. It's not really needed anyways though

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
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
			@RequestParam(value = "major", required = false) String major){
		
		
		JDBCUtil util = new JDBCUtil();
		util.addUser(firstName, lastName);
	}
	
	@RequestMapping(value = "/sqlGetUserByID/", method = RequestMethod.GET)
	public User getUserID( @RequestParam("id") String id) {		
		JDBCUtil util = new JDBCUtil();
		
		User user = new User();
		user = util.getUserByID(id);
		System.out.println(user.toString());
		
		return user;
		
	}
	
	@RequestMapping(value = "/sqlAddEvent/", method = RequestMethod.POST)
	void addEvent(
			@RequestParam(value = "eventName") String eventName,
			@RequestParam(value = "hostID") String hostID,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "priv") String priv,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "eventTime") String eventTime,
			@RequestParam(value = "eventDate") String eventDate){
		System.out.println(priv);
		System.out.println(eventTime);
		System.out.println(eventDate);
//		Integer myInt = priv;
		JDBCUtil util = new JDBCUtil();
		util.addEvent(eventName, hostID, description, priv, location, eventTime, eventDate);
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
}