/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.dk_grid.DkGridEmployeeDetails;
import object_models.helpers.Reload;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterDkGridEmployeeDetails implements ControlGetter {
	private WebDriver driver;
	private By findBy;
	private Reload reloadEmpDetails;
	private ContextManager contextManager;
	
	public ControlGetterDkGridEmployeeDetails(WebDriver driver, By findBy, Reload reloadEmpDetails, ContextManager contextManager) {
		this.driver = driver;
		this.findBy = findBy;
		this.reloadEmpDetails = reloadEmpDetails;
		this.contextManager = contextManager;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DkGridEmployeeDetails(driver, reloadEmpDetails, contextManager);
	}

}
