/**
 * 
 */
package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author SteveBrown
 *
 */
public class FireFoxDriver implements DriverGetter{
	
	@Override
	public WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		return new FirefoxDriver();
	}
	
}
