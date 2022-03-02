/**
 * 
 */
package controls.with_text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.getters.ElementGetter;
import controls.getters.TextGetter;
import controls.interfaces.Control;
import controls.interfaces.DisplayedText;
import controls.reset.ReloadContainer;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public abstract class ControlWithText implements Control, DisplayedText, ReloadContainer {

	private WebDriver driver;
	private By locator;	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	protected WebElement elContainer;
			
	public ControlWithText(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
		
		setElementContainer();		
	}	
	
	private void setElementContainer() {
		try {
			this.elContainer = driver.findElement(locator);	
		} catch (Exception e) {
			logger.error("Could not find control using [" + locator + "]");
		}
	}
	
	@Override //DisplayedText
	public String getText() {
		return new TextGetter(elContainer, this).getText();
	}
		
	@Override //Control
	public boolean isAvailable() {
		elContainer = new ElementGetter(driver).getElementIfClickable(this);
		return (elContainer != null) ? true : false;
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
		return elContainer;
	}

	@Override //ReloadContainer
	public WebElement reloadContainer() {
		setElementContainer();
		return elContainer;
	}
}
