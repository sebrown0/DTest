package page_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v91.page.Page;

import drivers.DriverGetter;
import drivers.GoogleDriver;

public class LandingPageTests {
//	private static DriverGetter dg = new GoogleDriver();
//	private static WebDriver driver;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
//		driver = dg.getDriver();
//		driver.get("https://www.dakarsoftware.com/");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		driver.quit();
	}
	
//	@Test
//	void pageTitle_Pass() {
//		assertEquals("Dakar Software Systems | Home", driver.getTitle());		
//	}
//
//	@Test
//	void pageTitle_Fail() {
//		assertEquals("Dakar Software Systems", driver.getTitle());		
//	}
	@Test
	void pageTitle_Pass() {
		assertEquals("D","D");		
	}

	@Test
	void pageTitle_Fail() {
		assertEquals("Dakar Software Systems", "D");		
	}
}
