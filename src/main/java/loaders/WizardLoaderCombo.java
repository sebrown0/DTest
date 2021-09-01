/**
 * 
 */
package loaders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ComboSelect;
import object_models.element.InputWriter;
import object_models.panels.employee_creation.WizardStepExecutor;

/**
 * @author Steve Brown
 *
 */
public class WizardLoaderCombo extends WizardLoader {
	private ComboSelect input;	
	
	public WizardLoaderCombo(WebDriver driver, WizardStepExecutor stepExecutor) {
		super(driver, stepExecutor, By.className("select2-selection__rendered"));	
	}	
	
	@Override
	public InputWriter getControlObject(WebElement e) {
		WebElement placeholder = e.findElement(By.className("select2-selection__placeholder"));
		String inputIdentifier = placeholder.getAttribute("textContent");
		input = new ComboSelect(driver, e, inputIdentifier);
		super.logger.debug("Found control [" + input.getMyIdentifier() + "]");
		return input;
	}
		
	@Override
	public String getIdentifier() {
		return input.getMyIdentifier();
	}

	@Override
	public String getLoaderName() {
		return this.getClass().getSimpleName();
	}
}
