/**
 * 
 */
package object_models.left_menu.parents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.element.TextInOut;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Documents extends JSPanelWithIFrame {	
	public static final String MENU_PARENT_NAME = "";
	public static final String PANEL_TITLE = "Employee Document Management";
	public static final String MENU_TITLE = "Documents";
	
	public Documents(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}
	
	// Elements
	public TextInOut employeeCode() {
		return new TextInOut(driver, By.id("FORM_ID"));
	}
}
