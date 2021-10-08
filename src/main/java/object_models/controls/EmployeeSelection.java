/**
 * 
 */
package object_models.controls;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controls.Control;
import object_models.element.ComboSelect;
import object_models.forms.FormModal;
import object_models.helpers.IFrame;
import object_models.helpers.Reload;

/**
 * @author Steve Brown
 *
 * Employee selection form. Usually part of page control.
 * When the control button is clicked this form appears
 * and the user can select an employee from a list.
 * 
 */
public final class EmployeeSelection extends FormModal implements Control {
	private IFrame iFrame;
	private Reload reloadEmpDetails;
	private WebElement topLevelContainer;
	private WebElement table;	
	
	public static final String MENU_TITLE = "Select from a list of employees within the chosen company";
	public static final String PANEL_TITLE = "Employees";
		
	public EmployeeSelection(WebDriver driver, Reload reloadEmpDetails) {
		super(driver, PANEL_TITLE);
		
		this.reloadEmpDetails = reloadEmpDetails;
		switchToIframe();
		loadContainers();
	}
		
	private void switchToIframe() {
		iFrame = new IFrame(driver, By.cssSelector("iframe[name='_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT']"));
		iFrame.switchUsingLocator();		
	}
	
	private void loadContainers() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("corners")));
		topLevelContainer = driver.findElement(By.id("corners"));
		table = topLevelContainer.findElement(By.id("employeeListTable"));
	}
	
	public void clickRow(String rowNum) {		
		WebElement rw = table.findElement(By.id("RIZZ" + rowNum));
		rw.click();
		reloadEmpDetails.reloadDefault();
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
		WebElement select = topLevelContainer.findElement(By.cssSelector("select[name='" + id + "']")); 
		return new ComboSelect(select); 
	}
}