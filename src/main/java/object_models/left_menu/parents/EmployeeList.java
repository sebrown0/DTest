/**
 * 
 */
package object_models.left_menu.parents;

import org.openqa.selenium.By;

import controls.ComboSelectFromOptions;
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
		return new ComboSelectFromOptions(
				coreData, 
				driver.findElement(By.cssSelector("#corners > div:nth-child(3) > div.col-5 > select")));		
	}	
			
	// Tabs
}
