/**
 * 
 */
package object_models.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import controls.Control;
import object_models.element.ComboSelect;
import object_models.forms.FormWithIFrame;

/**
 * @author Steve Brown
 *
 * Employee selection form. Usually part of page control.
 * When the control button is clicked this form appears
 * and the user can select an employee from a list.
 * 
 */
public final class EmployeeSelection extends FormWithIFrame implements Control {
	private WebElement topLevelContainer;
	private WebElement table;	
	
	public static final String MENU_TITLE = "Select from a list of employees within the chosen company";
	public static final String PANEL_TITLE = "Employees";

	public EmployeeSelection(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT", contextManager);

		super.switchToIFrame();		
		setMyContainers();		
	}
		
	public void clickRow(String rowNum) {				
		WebElement rw = table.findElement(By.id("RIZZ" + rowNum));
		rw.click();
		contextManager.closeCurrentContextAndRevertToCallingContext();
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

	@Override
	public void setMyContainers() {
		// These are in the form's iFrame, so will have to have switched to iFrame.
		topLevelContainer = driver.findElement(By.id("corners"));
		table = driver.findElement(By.id("employeeListTable"));
	}
}
