/**
 * 
 */
package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Steve Brown
 *
 */

public class GoogleDriver implements DriverGetter{
	
	@Override
	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\WebDrivers\\bin\\chromedriver.exe");
		return new ChromeDriver();
	}
}
