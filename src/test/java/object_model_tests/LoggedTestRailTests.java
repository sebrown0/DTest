package object_model_tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.GoogleDriver;
import listners.TestResultLogger;

@ExtendWith(TestResultLogger.class)
class LoggedTestRailTests {
	protected static WebDriver driver;
	protected static DriverGetter dg = new GoogleDriver();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {				
		driver = dg.getDriver();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
