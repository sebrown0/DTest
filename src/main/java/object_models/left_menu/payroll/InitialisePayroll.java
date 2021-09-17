package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import object_models.forms.FormWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class InitialisePayroll extends FormWithIFrame {	
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public InitialisePayroll(WebDriver driver) {
		super(driver, PANEL_TITLE, "_iframex-DEFAULT");
	}
		
	public String getVal() {
		System.out.println("->getVal");
		return "";
	}
//	public String getVal() {
//		WebElement e = super.driver.findElement(By.cssSelector("select[name='SelectPays']"));
//		return e.getText();
//	}
	// Elements

	// Tabs
}