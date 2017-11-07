package edu.csupomona.cs480;

import edu.csupomona.cs480.controller.RestWebController;
import junit.framework.TestCase;

public class tanyaTest extends TestCase {
	private String eventName;

	protected void setUp() throws Exception {
		super.setUp();
		eventName = "Event1";
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		eventName = "";
	}

	public void testGetEvent() {
		String expectedVal = "Event ID";
		String returnVal = RestWebController.getEvent(eventName);
		assertEquals(returnVal, expectedVal);
	}

}