package object_models.left_menu.payroll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ComboSelect;
import object_models.forms.FormWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class InitialisePayroll extends FormWithIFrame {	
	private WebElement container;
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";	
	
	public InitialisePayroll(WebDriver driver) {
		super(driver, PANEL_TITLE, "_iframex-DEFAULT");
		container = driver.findElement(By.cssSelector("body > form > div"));
	}
		
	public String getIframeTitle() {
		WebElement e = driver.findElement(By.cssSelector("body > form > div > div:nth-child(1) > div"));
		return e.getText();
	}
	
	// Elements
	public ComboSelect getSelectCompany() {
		return new ComboSelect(container.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(2) > select")));		
	}
	public ComboSelect getSelectPayGroup() {
		return new ComboSelect(container.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(2) > select")));		
	}	
	public ComboSelect getSelectPayPeriod() {
		return new ComboSelect(container.findElement(By.cssSelector("div:nth-child(5) > div:nth-child(2) > select")));		
	}

}