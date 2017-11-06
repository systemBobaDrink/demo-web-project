package edu.csupomona.cs480;

import org.junit.Test;

import edu.csupomona.cs480.controller.RestWebController;
import junit.framework.TestCase;

public class WaylonTest extends TestCase{
	public String secretMessage = "To enter the secret base, you must first say the secret password.";
	
	protected void setup() throws Exception{
//		System.out.println("Test printing" + secretMessage); //Not being run for some reason. ??
		super.setUp();
	}
	
	protected void tearDown() throws Exception{
//		System.out.println("Test printing3" + secretMessage);
		secretMessage = "";
	}
	
	@Test
	public void testSecretMessage() {
//		System.out.println("Test printing2" + secretMessage);
		assertEquals(secretMessage, RestWebController.entryPassword());
	}
	

}
