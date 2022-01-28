/**
 * 
 */
package factories;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.GoogleDriver;

/**
 * @author SteveBrown
 * @Comment
 * Return a driver for the name specified.
 * If the name does not match then a default google driver is returned.
 * 
 * An implicit wait is added. Meaning that the driver will wait for all
 * elements to be 'loaded' before throwing an error.
 * 
 */
public class DriverFactory {
	
	public static WebDriver getDriver(String driverName) {
		DriverGetter dg = null;
		WebDriver driver;
		Logger logger = LogManager.getLogger(DriverFactory.class);
		
		if(driverName.equalsIgnoreCase("XXXX")) {
			// ANOTHER DRIVER
		}else {
			// Default
			dg = new GoogleDriver();
			logger.info("Using Google driver");
		}			
		driver = dg.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		return driver;
	}
}
