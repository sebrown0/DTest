/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.getters.ElementGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * This was added for InitialisePayroll.
 * Where there are select boxes that cannot select,
 * or don't have a list to select from.
 */
public class TextSelect implements Control, DisplayedText {
	private WebElement elTextSelect;
	private WebDriver driver;
	private By locator;
		
	public TextSelect(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
		
		setContainer();		
	}
	
	private void setContainer() {
		try {
			this.elTextSelect = driver.findElement(locator);	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not find [TextSelect] using [" + locator + "]");
		}
	}
	
	public void setText(String text) {
		elTextSelect.sendKeys(text);
	}
		
	public void hitEnter() {
		elTextSelect.sendKeys(Keys.ENTER);
	}
	
	public String getText() {
		try {
			WebElement option = elTextSelect.findElement(By.cssSelector("option[value*='java']"));			
			return option.getText();
		} catch (Exception e) {
			LogManager.getLogger().error("Could not get text by value");
		}
		return "";
	}	
	
	@Override //Control
	public boolean isAvailable() {
		elTextSelect = new ElementGetter(driver).getElementIfClickable(this);
		return (elTextSelect != null) ? true : false;
	}	
	
	@Override //Control
	public By getLocator() {
		return locator;
	}

	@Override //Control
	public WebDriver getDriver() {
		return driver;
	}

	@Override //Control
	public WebElement getElement() {
		return elTextSelect;
	}
	
}
