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

import controls.ComboWriteAndSelect;
import object_models.date_picker.DatePickerPopup;
import object_models.helpers.text_writer.TextWriterComboDefault;

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
			openSelectionContainer(select.get());
			setPopupToCombo();			
			return true;
		}else {
			return false;
		}		
	}
	
	/*
	 * When a cell is double clicked it provides a selection box that
	 * must be clicked again to show the 'dropdown' selection, thus
	 * open the 'select2-container'.
	 */
	private void openSelectionContainer(WebElement select) {
		WebElement arrow = select.findElement(By.className("select2-selection__arrow"));
		arrow.click();
	}
	
	private void setPopupToCombo() {
		popupType = new ComboWriteAndSelect(
				driver, 
				By.cssSelector("span[class='select2-container select2-container--default select2-container--open']"), 
				By.className("select2-results"),
				new TextWriterComboDefault(driver));
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
