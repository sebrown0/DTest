/**
 * 
 */
package object_models.module_personnel.left_menu.employee;

import org.openqa.selenium.By;

import object_models.element.TextInOut;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Documents extends JsPanelWithIFrame {	
	public static final String MENU_PARENT_NAME = "";
	public static final String PANEL_TITLE = "Employee Document Management";
	public static final String MENU_TITLE = "Documents";
	
	public Documents(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}
	
	// Elements
	public TextInOut employeeCode() {
		return new TextInOut(driver, By.id("FORM_ID"));
	}
}
