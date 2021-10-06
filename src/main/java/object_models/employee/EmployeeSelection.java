/**
 * 
 */
package object_models.employee;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.element.ComboSelect;
import object_models.forms.FormModal;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public final class EmployeeSelection extends FormModal {
	private IFrame iFrame;
	private WebElement container;
	private WebElement table;
	
	public static final String MENU_TITLE = "Select from a list of employees within the chosen company";
	public static final String PANEL_TITLE = "Employees";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public EmployeeSelection(WebDriver driver) {
		super(driver, PANEL_TITLE);
		
		switchToIframe();
		loadTopLevelContainer();
	}
	
	private void switchToIframe() {
		iFrame = new IFrame(driver, By.cssSelector("iframe[name='_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT']"));
		iFrame.switchUsingLocator();		
	}
	
	private void loadTopLevelContainer() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("corners")));
		container = driver.findElement(By.id("corners"));
		table = container.findElement(By.id("employeeListTable"));
	}
	
	public void clickRow(String rowNum) {		
		WebElement rw = table.findElement(By.id("RIZZ" + rowNum));
		rw.click();
	}
	
	// Elements
	public ComboSelect companySelect() {
		return getSelectBox("SelectURLNC1");
	}
	
	public ComboSelect departmentSelect() {
		return getSelectBox("SelectURLNC2");
	}
	
	public ComboSelect filterSelect() {
		return getSelectBox("SelectURLNCA2");
	}
	
	
	private ComboSelect getSelectBox(String id) {
		WebElement select = container.findElement(By.cssSelector("select[name='" + id + "']")); 
		return new ComboSelect(select); 
	}
}