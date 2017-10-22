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
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;

import java.sql.*;




/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

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
	String getEvent() {		
		return "This is the event ID";
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
	@RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

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
	String entryPassword() {
		return "To enter the secret base, you must first say the secret password.";
	}
	
	@RequestMapping(value = "/cs480/colorChoice", method = RequestMethod.GET)
	String chooseColor() {
		return "The default color is black, sorry you can't change it!";
		
	}
	@RequestMapping(value = "/cs480/jsoup", method = RequestMethod.GET)
	String jsoup() throws IOException {
		Document doc = Jsoup.connect("http://target.com/").get();
		Elements e = doc.select("a");
		return ("Target Finds: " + "\n" + e);
		
	}

	@RequestMapping(value = "/cs480/maps", method = RequestMethod.GET)
	String pomonaMap() {
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
	
	//
	@Autowired 
	JdbcTemplate jt;

	@RequestMapping(value = "/cs480/jdbcTestFindUser", method = RequestMethod.GET)
	String findUser() {
		//Deletes table if previously exists, creates new one afterwords.
		jt.execute("DROP TABLE users IF EXISTS");
	    jt.execute("CREATE TABLE users(id SERIAL, name VARCHAR(255), major VARCHAR(255))");
	    
	    //Dummy data for database
	    jt.execute("INSERT INTO users (id, name, major) values (1, 'John Adams', 'Computer Science')");
	    jt.execute("INSERT INTO users (id, name, major) values (2, 'Alex Cremmins', 'Engineer')");
	    jt.execute("INSERT INTO users (id, name, major) values (3, 'Joseph Mendoza', 'Liberal Arts')");
	    
	    //Determines how the query will search
	    String sql = "SELECT NAME FROM users WHERE id = ?";
	    
	    //Query search: Simply looks for the object with an id == 2
	    String searching = (String)jt.queryForObject(sql, new Object[] { 2 }, String.class);
	    
	    //Returns the string of the object found
	    return searching;
	}
	
	@RequestMapping(value = "/cs480/sqlTest", method = RequestMethod.GET)
	String testConnection() {
		String returnThis = "Testing\n";
		try {
			//Get a connection to database
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "SystemBobaDrink321");
			
			//Create a statement
			Statement myState = myCon.createStatement();
			
			//Execute SQL Query
			ResultSet myRs = myState.executeQuery("select * from users");
			
			//Process the Results
			while (myRs.next()) {
				returnThis += myRs.getString("firstName") + ", " + myRs.getString("lastName") + ", " + 
								   myRs.getString("major") + ".\n";
			}
			
		}
		catch (Exception e) {

		}
		return returnThis;
	}
}
