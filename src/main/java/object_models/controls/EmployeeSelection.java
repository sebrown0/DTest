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

import context_manager.ContextId;
import context_manager.ContextManager;
import controls.Control;
import object_models.element.ComboSelect;
import object_models.forms.FormModal;
import object_models.helpers.Header;
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
		
	public EmployeeSelection(WebDriver driver, Reload reloadEmpDetails, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
		
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
		// TODO
		// This should be a context. 
		// When clicked the context should disappear and be replaced by the previous.
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
	
	@Override
	public void close() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void waitForLoad() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void setContextState() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void setContainer() {
		logger.error("NOT IMPLEMENTED");		
	}
//	@Override
//	public void setHeader() {
//		logger.error("NOT IMPLEMENTED");
//	}
	@Override
	public void setTitle() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public ContextId getContextId() {
		logger.error("NOT IMPLEMENTED");
		return null;
	}

	@Override
	public Header getHeader() {
		// TODO Auto-generated method stub
		logger.error("NOT IMPLEMENTED");
		return null;
	}
}