package edu.csupomona.cs480;

import edu.csupomona.cs480.controller.WebController;
import junit.framework.TestCase;

public class tanyaTest extends TestCase {
	private String eventName;

	protected void setUp() throws Exception {
		super.setUp();
		String eventName = "Event1";
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		String eventName = "";
	}

	public void testGetEvent() {
		String expectedVal = "Event ID";
		String returnVal = WebController.getEvent(eventName);
		assertEquals(returnVal, expectedVal);
	}

}