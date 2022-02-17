/**
 * 
 */
package controls;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class Tab implements Control, DisplayedText {
	private WebDriver driver;
	private By locator;
	private WebElement tab;
	
	public Tab(WebDriver driver, WebElement tab) {
		this.driver = driver;
		this.tab = tab;
	}
	
	public Tab(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
	}
	
	@Override //DisplayedText
	public String getText() {
		if(isAvailable()) {
			return tab.getText().trim();	
		}else {
			return null;
		}		
	}
	
	@Override //Control
	public boolean isAvailable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			if(tab == null && locator != null) {
				tab = wait.until(ExpectedConditions.elementToBeClickable(locator));	
			}else if(tab != null){
				tab = wait.until(ExpectedConditions.elementToBeClickable(tab));
			}else {
				return false;
			}			
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to find tab [" + e + "]");
			return false;
		}		
	}

	public boolean click() {		
		if(isAvailable()) {
			tab.click();
			return true;
		}
		return false;
	}
}
