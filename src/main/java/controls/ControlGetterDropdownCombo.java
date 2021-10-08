/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.controls.DropdownCombo;
import object_models.helpers.Reload;

/**
 * @author Steve Brown
 *
 */
public final class ControlGetterDropdownCombo implements ControlGetter {
	private WebDriver driver;
	private By findBy;
	private Reload reloadEmpDetails;
	
	public ControlGetterDropdownCombo(WebDriver driver, By findBy, Reload reloadEmpDetails) {
		this.driver = driver;
		this.findBy = findBy;
		this.reloadEmpDetails = reloadEmpDetails;
	}

	@Override
	public Control getControl() {
		driver.findElement(findBy).click();
		return new DropdownCombo(driver, reloadEmpDetails);
	}

}
