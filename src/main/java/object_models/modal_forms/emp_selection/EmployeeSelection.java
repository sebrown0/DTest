/**
 * 
 */
package object_models.modal_forms.emp_selection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.combos.ComboSelectFromOptions;
import controls.getters.ElementGetter;
import controls.interfaces.Control;
import controls.with_text.TextSelect;
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
public final class EmployeeSelection extends FormWithIFrame implements Control, SelectEmployee {
	private WebElement topLevelContainer;
	private WebElement table;	
	private By locator;
	
	public static final String MENU_TITLE = "Select from a list of employees within the chosen company";
	public static final String PANEL_TITLE = "Employees";

	public EmployeeSelection(CoreData coreData, By locator) {
		super(coreData, PANEL_TITLE, "_iframex-IPORTAL_HR_EMPLOYEEDETAILS_EXT");

		this.locator = locator;
		setMyContainers();		
	}
		
	public void selectRow(String rowNum) {
		// Have to load the table again as the results may have changed.
		setTable(); 
		if(!rowSelected(rowNum)) {			
			closeForm();
		}
		contextManager.deleteCurrentContextAndRevertToCallingContext();
	}
		
	private boolean rowSelected(String rowNum) {
		return new RowSelector().selectRow(table, rowNum); 
	}
	
	private void closeForm() {
		super.close();
	}
	
	// Elements
	public ComboSelectFromOptions companySelect() {
		return getSelectBox("SelectURLNC1");
	}
	
	public ComboSelectFromOptions filterSelect() {
		return getSelectBox("SelectURLNCA2");
	}	
	 	
	public TextSelect getSearchThisCompany() { 
		return 
			new TextSelect(
					driver, By.cssSelector("input[name='PERF_NAME_SEARCH2']"));
	}
		
	private ComboSelectFromOptions getSelectBox(String id) {
		WebElement select = topLevelContainer.findElement(By.cssSelector("select[name='" + id + "']")); 
		return new ComboSelectFromOptions(super.coreData, select, locator); 
	}

	@Override
	public void setMyContainers() {
		// These are in the form's iFrame, so will have to have switched to iFrame.
		setTopLevel();
		setTable();
	}	
	private void setTopLevel() {
		topLevelContainer = driver.findElement(By.id("corners"));
	}
	private void setTable() {
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

	@Override //SelectEmployee
	public void UsingSelector(EmpSelectorBy selectBy) {
		selectBy.selectEmployee();
	}
}
