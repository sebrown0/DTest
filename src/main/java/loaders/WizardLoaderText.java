/**
 * 
 */
package loaders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.InputWriter;
import object_models.element.TextInput;
import object_models.panels.employee_creation.WizardStepExecutor;

/**
 * @author Steve Brown
 *
 */
public class WizardLoaderText extends WizardLoader {
	private TextInput input;	
	
	public WizardLoaderText(WebDriver driver, WizardStepExecutor stepExecutor) {
		super(driver, stepExecutor, By.xpath(".//input[contains(@id, 'ST_')]"));	
	}	
	
	@Override
	public InputWriter getControlObject(WebElement e) {
		String inputIdentifier = e.getAttribute("placeholder");
		input = new TextInput(e, inputIdentifier);		
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
