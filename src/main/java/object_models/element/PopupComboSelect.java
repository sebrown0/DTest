/**
 * 
 */
package object_models.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.ComboWriteAndSelect;
import exceptions.ElementDoesNotExistException;
import object_models.dk_grid.Popup;
import object_models.helpers.text_writer.TextWriterComboDefault;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public final class PopupComboSelect extends ComboWriteAndSelect implements Popup {

	public PopupComboSelect(WebDriver driver, WebElement select) {
		super(
				driver, 
				By.cssSelector("span[class='select2-container select2-container--default select2-container--open']"), 
				By.className("select2-results"), 
				new TextWriterComboDefault(driver));
	
		openSelectionContainer(driver, select);
	}
	
	/*
	 * When a cell is double clicked it provides a selection box that
	 * must be clicked again to show the 'dropdown' selection, thus
	 * open the 'select2-container'.
	 */
	private void openSelectionContainer(WebDriver driver, WebElement select) {
		try {
			WebElement arrow = select.findElement(By.className("select2-selection__arrow"));
			arrow.click();	
		} catch (Exception e) {
			new ElementDoesNotExistException("Could not get selection arrow").run();
		}		
	}
	
}
