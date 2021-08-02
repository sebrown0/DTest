/**
 * 
 */
package factories;

import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.GoogleDriver;

/**
 * @author SteveBrown
 * @Comment
 * Return a driver for the name specified.
 * If the name does not match then a default google driver is returned.
 */
public class DriverFactory {
	
	public static WebDriver getDriver(String driverName) {
		DriverGetter dg = null;
		
		if(driverName.equalsIgnoreCase("XXXX")) {
			// ANOTHER DRIVER
		}else {
			dg = new GoogleDriver();
		}			
		return dg.getDriver();
	}
}
