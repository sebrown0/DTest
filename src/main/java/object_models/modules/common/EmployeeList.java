/**
 * 
 */
package object_models.modules.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.combos.ComboSelectFromOptions;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeList extends JsPanelWithIFrame {		
	public static final String MENU_PARENT_NAME = "";
	public static final String PANEL_TITLE = "Employee List";
	public static final String MENU_TITLE = PANEL_TITLE;
	
	public EmployeeList(CoreData coreData) {
		super(coreData, PANEL_TITLE);		
	}
		
	// Elements
	public ComboSelectFromOptions company() {
		final By locator = By.cssSelector("#corners > div:nth-child(3) > div.col-5 > select");
		final WebElement el = driver.findElement(locator);
		return new ComboSelectFromOptions(coreData,	el, locator);		
	}	
			
	// Tabs
}
