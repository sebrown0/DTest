package driver_tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.FireFoxDriver;
import drivers.GoogleDriver;

class DriverTests {
	private WebDriver driver;
	private DriverGetter driverGetter;
	
	@Test
	void google() {
		driverGetter = new GoogleDriver();		
		driver = driverGetter.getDriver();
		assertNotNull(driver);
		driver.quit();
	}
	
	@Test
	void firefox() {
		driverGetter = new FireFoxDriver();
		driver = driverGetter.getDriver();
		assertNotNull(driver);		
		driver.quit();
	}

}
