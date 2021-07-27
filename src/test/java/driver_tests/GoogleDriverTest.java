package driver_tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.GoogleDriver;

class GoogleDriverTest {
	private WebDriver driver;
	
	@Test
	void test() {
		DriverGetter dg = new GoogleDriver();		
		driver = dg.getDriver();
		assertNotNull(driver);
		driver.quit();
	}

}
