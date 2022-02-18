/**
 * 
 */
package object_models.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.ComboSelectFromOptions;
import controls.Control;
import controls.getters.ElementGetter;
import object_models.forms.FormWithIFrame;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Employee selection form. Usually part of page control.
 * When the control button is clicked this form appears
 * and the user can select an employee from a list.
 * 
 */
public final class EmployeeSelection extends FormWithIFrame implements Control {
	private WebElement topLevelContainer;
	private WebElement table;	
	private By locator;
	
	public static final String MENU_TITLE = "Select from a list of employees within the chosen company";
	public static final String PANEL_TITLE = "Employees";

	public EmployeeSelection(CoreData coreData, By locator) {
		super(coreData, PANEL_TITLE, "_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT");

		this.locator = locator;
//		super.switchToIFrame();		
		setMyContainers();		
	}
		
	public void clickRow(String rowNum) {				
		WebElement rw = table.findElement(By.id("RIZZ" + rowNum));
		rw.click();
		contextManager.deleteCurrentContextAndRevertToCallingContext();
	}
		
	// Elements
	public ComboSelectFromOptions companySelect() {
		return getSelectBox("SelectURLNC1");
	}
	
	public ComboSelectFromOptions filterSelect() {
		return getSelectBox("SelectURLNCA2");
	}	
	
	private ComboSelectFromOptions getSelectBox(String id) {
		WebElement select = topLevelContainer.findElement(By.cssSelector("select[name='" + id + "']")); 
		return new ComboSelectFromOptions(super.coreData, select, locator); 
	}

	@Override
	public void setMyContainers() {
		// These are in the form's iFrame, so will have to have switched to iFrame.
		topLevelContainer = driver.findElement(By.id("corners"));
		table = driver.findElement(By.id("employeeListTable"));
	}
	
	@Override // Control
	public boolean isAvailable() {
		table = new ElementGetter(driver).getElementIfClickable(this);
		return (table != null) ? true : false;
	}	
	
	@Override //Control
	public By getLocator() {
		return locator;
	}

	@Override //Control
	public WebDriver getDriver() {
		return driver;
	}	

	@Override //Control
	public WebElement getElement() {
		return table;
	}
}
