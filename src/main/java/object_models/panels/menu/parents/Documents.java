/**
 * 
 */
package object_models.panels.menu.parents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.TextInOut;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class Documents extends JSPanelWithIFrame {
	public static final String PANEL_TITLE = "Employee Document Management";
	public static final String MENU_TITLE = "Documents";
	
	public Documents(WebDriver driver) {
		super(driver, PANEL_TITLE);
	}
	
	// Elements
	public TextInOut employeeCode() {
		return new TextInOut(driver, By.id("FORM_ID"));
	}
}
