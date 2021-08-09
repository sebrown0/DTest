/**
 * 
 */
package object_models.helpers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class DriverWait {
	public static void implicitilyFor(WebDriver driver, Duration d) {
		driver.manage().timeouts().implicitlyWait(d);
	}
}
