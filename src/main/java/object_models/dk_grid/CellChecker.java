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

import controls.PopupComboSelect;
import object_models.date_picker.DatePickerPopup;

/**
 * <p>
 * Given a cell element, check if it has a pop-up element.
 * If it does, return the pop-up control.
 * </p> 
 * <p>
 * For v1.0 the available controls are:
 *  1. DatePickerPopup
 *  2. PopupComboSelect
 * </p>
 * 
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public class CellChecker {
	private WebDriver driver;	
	private WebElement cellElement;
	private Actions actions;		
	private Optional<WebElement> popupTopLevel = Optional.empty();
	private Popup popupType = null;
	private Cell cell;
	
	public CellChecker(WebDriver driver, Cell cell) {
		this.driver = driver;
		this.cell = cell;
		this.cellElement = cell.getMyElement();
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
			setPopupToCombo(select.get());			
			return true;
		}else {
			return false;
		}		
	}
	
	private void setPopupToCombo(WebElement select) {
		popupType = new PopupComboSelect(driver, select, cell);
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
