package edu.csupomona.cs480;

import edu.csupomona.cs480.controller.RestWebController;
import junit.framework.TestCase;

public class EricTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testJSoup() {
		try {
			String expectedVal = "Target Finds: " + "\n" +"<a href=\"javascript:void(0)\" class=\"link link-grayDarkest\" rel=\"nofollow\">terms</a>\n" +
					"<a href=\"javascript:void(0)\" class=\"link link-grayDarkest\" rel=\"nofollow\">privacy</a>\n" +
					"<a href=\"javascript:void(0)\" class=\"link link-grayDarkest\" rel=\"nofollow\">interest-based ads</a>\n" +
					"<a href=\"javascript:void(0)\" class=\"link link-grayDarkest\" rel=\"nofollow\">ca privacy rights</a>\n" +
					"<a href=\"javascript:void(0)\" class=\"link link-grayDarkest\" rel=\"nofollow\">ca supply chain act</a>\n" +
					"<a href=\"javascript:void(0)\" class=\"js-privacy-update link link-orange\" data-value=\"privacyPolicy\" data-ref=\"/spot/terms/privacy-policy\"></a>";
			String returnVal = RestWebController.jsoup();
			assertEquals(returnVal, expectedVal);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}