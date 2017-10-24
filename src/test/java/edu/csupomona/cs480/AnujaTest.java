package edu.csupomona.cs480;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.csupomona.cs480.controller.WebController;
import junit.framework.TestCase;

public class AnujaTest extends TestCase{
	private String mapLocation; 
	protected void setUp() throws Exception{
		super.setUp();
		  mapLocation = "mapLoc";
		
	}
	protected void tearDown() throws Exception {
		super.tearDown();
		 mapLocation = "";
	}

	@Test 
	public void testMessage() {
		
		 assertEquals("TestMessageAnuja" , "13144", WebController.pomonaMap());
	}
}
