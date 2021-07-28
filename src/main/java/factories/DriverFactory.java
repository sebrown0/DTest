/**
 * 
 */
package factories;

import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.GoogleDriver;
import xml_reader.WebDriverNameGetter;

/**
 * @author SteveBrown
 * @Comment
 * Return a driver for the name specified.
 * If the name does not match then a default google driver is returned.
 */
public class DriverFactory {
	public static WebDriver getDriver(WebDriverNameGetter nameGetter) {
		DriverGetter dg = null;
		if(nameGetter.getTypeName().equalsIgnoreCase("XXXX")) {
			// ANOTHER DRIVER
		}else {
			dg = new GoogleDriver();
		}			
		return dg.getDriver();
	}
}
