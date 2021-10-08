/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	
	public ControlGetterDkGridEmployeeDetails(WebDriver driver, By findBy, Reload reloadEmpDetails) {
		this.driver = driver;
		this.findBy = findBy;
		this.reloadEmpDetails = reloadEmpDetails;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DkGridEmployeeDetails(driver, reloadEmpDetails);
	}

}
