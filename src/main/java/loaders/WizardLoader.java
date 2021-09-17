/**
 * 
 */
package loaders;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.employee_creation.WizardStepExecutor;

/**
 * @author Steve Brown
 *
 */
public abstract class WizardLoader implements LoaderData{
	protected final WebDriver driver;
	protected Logger logger = LogManager.getLogger();
	private String stepLevel;
	protected By by;
	
	public WizardLoader(final WebDriver driver, WizardStepExecutor stepExecutor, By by) {
		this.driver = driver;
		this.stepLevel = String.valueOf(stepExecutor.getStepNumber()-1);
		this.by = by;
	}
	
	//TOP LEVEL ELEMENT
	@Override
	public List<WebElement> getElements() {
		WebElement wizard = driver.findElement(By.id("wizard-p-" + stepLevel));
		List<WebElement> textBoxes = wizard.findElements(by);
		return textBoxes;
	}
}
