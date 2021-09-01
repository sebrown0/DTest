/**
 * 
 */
package loaders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.InputWriter;
import object_models.element.InputText;
import object_models.panels.employee_creation.WizardStepExecutor;

/**
 * @author Steve Brown
 *
 */
public class ZZZ_WizardLoaderText extends WizardLoader {
	private InputText input;	
	
	public ZZZ_WizardLoaderText(WebDriver driver, WizardStepExecutor stepExecutor) {
		super(driver, stepExecutor, By.xpath(".//input[contains(@id, 'ST_')]"));	
	}	
	
	@Override
	public InputWriter getControlObject(WebElement e) {
		String inputIdentifier = e.getAttribute("placeholder");
		input = new InputText(driver, e, inputIdentifier)		;
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
