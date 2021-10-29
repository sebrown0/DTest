/**
 * 
 */
package object_models.controls;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import controls.Control;
import object_models.element.ComboSelect;
import object_models.forms.FormWithIFrame;
import object_models.helpers.Reload;

/**
 * @author Steve Brown
 *
 * Employee selection form. Usually part of page control.
 * When the control button is clicked this form appears
 * and the user can select an employee from a list.
 * 
 */
public final class EmployeeSelection extends FormWithIFrame implements Control {
//	private IFrame iFrame;
	private Reload reloadEmpDetails;
	private WebElement topLevelContainer;
	private WebElement table;	
	
	public static final String MENU_TITLE = "Select from a list of employees within the chosen company";
	public static final String PANEL_TITLE = "Employees";

	public EmployeeSelection(WebDriver driver, Reload reloadEmpDetails, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT", contextManager);

		this.reloadEmpDetails = reloadEmpDetails;
		super.switchToIFrame();
		
		setMyContainers();		
	}
		
	public void clickRow(String rowNum) {		
		WebElement rw = table.findElement(By.id("RIZZ" + rowNum));
		rw.click();
		Optional<State> callingState = contextManager.closeCurrentContext();
		callingState.ifPresent(s -> {
			ContextState callingContext = s.getMyContext();
			callingContext.moveToState(s.getClass());
			s.switchToMe();
			System.out.println("Switched to state ->" + s + " in context ->" + callingContext.getContextId()); // TODO - remove or log 	
		});
		
		// TODO
		// This should be a context. 
		// When clicked the context should disappear and be replaced by the previous.
		/*
		 * CHECK & Remove Reload!!!!!!!!!!!!!!!!!!!!!!!!! 
		 */
//		reloadEmpDetails.reloadDefault();
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
//		topLevelContainer = super.formContainerElement.findElement(By.id("corners"));
//		table = topLevelContainer.findElement(By.id("employeeListTable"));
	}
}

//@Override
//public void waitForLoad() {
//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("corners")));
//}
//public EmployeeSelection(WebDriver driver, Reload reloadEmpDetails, ContextManager contextManager) {
//super(driver, PANEL_TITLE, contextManager);
//
//this.reloadEmpDetails = reloadEmpDetails;
//setMyContainers();
//switchToIframe();
//}

//private void switchToIframe() {
//iFrame = new IFrame(driver, By.cssSelector("iframe[name='_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT']"));
//iFrame.switchUsingLocator();		
//}

//private void loadContainers() {
//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("corners")));
//topLevelContainer = driver.findElement(By.id("corners"));
//table = topLevelContainer.findElement(By.id("employeeListTable"));
//}