/**
 * 
 */
package controls.with_text;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * This was added for InitialisePayroll.
 * Where there are select boxes that cannot select,
 * or don't have a list to select from.
 */
public class TextSelect extends ControlWithText {
	
	public TextSelect(WebDriver driver, By locator) {
		super(driver, locator);	
	}
	
	public void setText(String text) {
		elContainer.sendKeys(text);
	}
		
	public void hitEnter() {
		elContainer.sendKeys(Keys.ENTER);
	}
	
}
