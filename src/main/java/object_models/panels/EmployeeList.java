/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.WebDriver;

import object_models.helpers.TitlePanel;
import object_models.pages.Page;

/**
 * @author Steve Brown
 *
 */
public class EmployeeList extends Page {
	public static final String PANEL_TITLE = "Employee List";
	
	public EmployeeList(WebDriver driver) {
		super(driver, new TitlePanel(PANEL_TITLE, driver));
	
	}
}
