/**
 * 
 */
package factories;

import java.time.Duration;

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
		
		if(driverName.equalsIgnoreCase("XXXX")) {
			// ANOTHER DRIVER
		}else {
			// Default
			dg = new GoogleDriver();
		}			
		driver = dg.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		return driver;
	}
}
