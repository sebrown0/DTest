/**
 * 
 */
package object_models.dk_grid;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import object_models.date_picker.DatePickerPopup;

/**
 * @author Steve Brown
 *
 */
public class CellChecker {
	private WebDriver driver;	
	private WebElement cellElement;
	private Actions actions;		
	private Optional<WebElement> popupTopLevel = Optional.empty();
	private Popup popupType = null;
	
	public CellChecker(WebDriver driver, WebElement cellElement) {
		this.driver = driver;
		this.cellElement = cellElement;
		this.actions = new Actions(driver);
	}

	public boolean hasPopup() {		
		actions.doubleClick(cellElement).perform();
		popupTopLevel = Optional.ofNullable(getElement(By.cssSelector("div[class='ag-theme-balham ag-popup']")));
		if(popupTopLevel.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Popup getPopupType() {
		popupTopLevel.ifPresentOrElse(
				p -> {
					Optional<WebElement> popupChild = Optional.ofNullable(getChildElement(By.cssSelector("div[class='ag-popup-editor ag-ltr ag-popup-child']"), p));
					popupChild.ifPresent(c -> {
						if(!isSelect(c)) {
							tryDatePicker(c);
						}
					});		
				}, 
				new Runnable() {					
					@Override
					public void run() {
						if(hasPopup()) {
							getPopupType();
						}
					}
				});		
		
		return popupType;
	}
		
	private boolean isSelect(WebElement chld) {
		Optional<WebElement> select = Optional.ofNullable(getChildElement(By.cssSelector("body > div.ag-theme-balham.ag-popup > div > span"), chld));
		if(select.isPresent()) {
			return true;
		}else {
			return false;
		}		
	}
	
	private boolean tryDatePicker(WebElement chld) {
		Optional<WebElement> picker = Optional.ofNullable(getChildElement(By.cssSelector("div[class='datepicker datepicker-inline']"), chld));
		if(picker.isPresent()) {
			popupType = new DatePickerPopup(driver);
			return true;
		}else {
			return false;
		}		
	}
	
	private WebElement getElement(By byLocator) {
		WebElement el = null;		
		try {
			el = driver.findElement(byLocator);
		} catch (NoSuchElementException e) { 	
			// Nothing - element doesn't exist. 	
		}	catch (Exception e) {
			LogManager.getLogger().error("Error getting element [" + e + "]"); 	
		}			
		return el;
	}
	
	private WebElement getChildElement(By byLocator, WebElement fromParent) {
		WebElement el = null;		
		try {
			el = fromParent.findElement(byLocator);
		} catch (NoSuchElementException e) { 	
			// Nothing - element doesn't exist. 	
		}	
		catch (Exception e) {
			LogManager.getLogger().error("Error getting element [" + e + "]");		 	
		}			
		return el;
	}
}

//public String getPopupType() {
//WebElement popupChild = null;
//String popupType = "none";
//try {
//// ADD WAIT IF PROBLEMS WITH LOCATING POPUP
//popupChild = popupTopLevel.findElement(By.cssSelector("div[class='ag-popup-editor ag-ltr ag-popup-child']"));
//
//WebElement select = popupChild.findElement(By.cssSelector("body > div.ag-theme-balham.ag-popup > div > span"));
////WebElement type = popupChild.findElement(By.cssSelector("span[class='select2 select2-container select2-container--default select2-container--focus']"));
//if(select != null && select.getAttribute("class").length() > 0) {
//	popupType = "select";
//}
//} catch (NoSuchElementException e) {
//System.out.println("->NOOOOOOOOOOOOooooo"); // TODO - remove or log 	
//// Nothing - popup doesn't exist. 	
//}	catch (Exception e) {
//LogManager.getLogger().error("Error checking if cell has popup [" + e + "]");
//System.out.println("->" + e); // TODO - remove or log 	
//}				
//return popupType;
//}

