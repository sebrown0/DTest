/**
 * 
 */
package controls.combos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.getters.ElementGetter;
import controls.interfaces.Control;
import object_models.helpers.DriverWait;
import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 *
 */
public abstract class Combo implements Control {
	private WebElement combo;
	private By locator;
	
	protected By findParentBy;	
	protected boolean isOpen = false;	
	protected WebDriver driver;
	
	public Combo(CoreData coreData, By locator) {
		this.driver = coreData.getWebDriver();
		this.locator = locator;
	}
	public Combo(CoreData coreData, WebElement combo,  By locator) {
		this.combo = combo;
		this.driver = coreData.getWebDriver();
		this.locator = locator;
	}	
	
	
	protected WebElement getComboElement() {
		if(combo == null) {
			if(findParentBy == null) {
				combo = DriverWait.getElementAfterWait(driver, locator);	
			}else {
				WebElement prnt = driver.findElement(findParentBy);
				prnt.click();
				combo = DriverWait.getElementAfterWait(driver, locator);	
			}			
		}
		return combo;
	}
	
	public void click() {
		try {
			getComboElement().click();
			isOpen = !isOpen;
		} catch (Exception e) {
			LogManager.getLogger().debug("Unable to click combo [" + e.getMessage() + "]");
		}				
	}
		
	public void clearAll() {
		getComboElement().findElement(By.cssSelector("span[class='select2-selection__clear']")).click();
	}
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(getComboElement(), sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	protected Logger getLogger() {
		return LogManager.getLogger();
	}
	
	@Override //Control
	public boolean isAvailable() {
		combo = new ElementGetter(driver).getElementIfClickable(this);
		return (combo != null) ? true : false;
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
		return combo;
	}
	public void setFindParentBy(By findParentBy) {
		this.findParentBy = findParentBy;
	}	
}
