/**
 * 
 */
package object_models.left_menu.parents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.element.ComboSelect;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeList extends JSPanelWithIFrame {	
	public static final String PANEL_TITLE = "Employee List";
	public static final String MENU_TITLE = PANEL_TITLE;
	
	public EmployeeList(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}
		
	// Elements
	public ComboSelect company() {
		return new ComboSelect(driver, By.cssSelector("#corners > div:nth-child(3) > div.col-5 > select"));		
	}	
			
	// Tabs
}
