/**
 * 
 */
package controls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.DriverWait;
import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 *
 */
public abstract class Combo implements Control {
	private WebElement combo;
	private By comboLocator;
	
	
	protected boolean isOpen = false;	
	protected WebDriver driver;
	
	public Combo(WebDriver driver, WebElement combo) {
		this.combo = combo;
		this.driver = driver;		
	}
	
	public Combo(WebDriver driver, By findBy) {
		this.driver = driver;
		this.comboLocator = findBy;
	}
	
	public void setComboLocator(By comboLocator) {
		this.comboLocator = comboLocator;
	}

	public WebElement getComboElement() {
		if(combo == null) {
			combo = DriverWait.getElementAfterWait(driver, comboLocator);
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
}
