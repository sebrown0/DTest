/**
 * 
 */
package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author Steve Brown
 *
 */

public class GoogleDriver implements DriverGetter{
	
	@Override
	public WebDriver getDriver() {
		WebDriver driver;		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\WebDrivers\\bin\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		driver = new ChromeDriver(options);
		return driver;
	}
}
