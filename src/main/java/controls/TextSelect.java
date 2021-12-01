/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * This was added for InitialisePayroll.
 * Where there are select boxes that cannot select,
 * or don't have a list to select from.
 */
public class TextSelect implements Control {
	private WebElement text;

	public TextSelect(WebDriver driver, By findBy) {
		setTextOut(driver, findBy);		
	}
	
	private void setTextOut(WebDriver driver, By findBy) {
		try {
			this.text = driver.findElement(findBy);	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not find [TextOut] using [" + findBy + "]");
		}
	}
		
	public String getText() {
		try {
			WebElement option = text.findElement(By.cssSelector("option[value*='java']"));			
			return option.getText();
		} catch (Exception e) {
			LogManager.getLogger().error("Could not get text by value");
		}
		return "";
	}	
	
	@Override
	public boolean isAvailable() {
		LogManager.getLogger().error("NOT IMPLEMENTED");		
		return false;
	}	
}
