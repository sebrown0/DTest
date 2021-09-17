/**
 * 
 */
package object_models.helpers.closers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class CloserPanel extends Closer {
	private static final By LOCATOR = 
			By.cssSelector(".jsPanel-btn.jsPanel-btn-close");
	
	public CloserPanel(WebDriver driver) {
		super(driver, LOCATOR);
	}
}
