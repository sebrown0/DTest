/**
 * 
 */
package object_models.panels.employee_creation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ComboSelect;
import object_models.element.TextInput;

/**
 * @author Steve Brown
 *
 */
public class WizardStepLoader {	
	private WebDriver driver;	
	private WebElement wizard;
	private WizardStepExecutor stepExecutor;
	private Logger logger = LogManager.getLogger();
	
	public WizardStepLoader(WebDriver driver, WizardStepExecutor stepExecutor) {
		this.driver = driver;
		this.stepExecutor = stepExecutor;		
	}

	public void loadControls() {
		String stepLevel = String.valueOf(stepExecutor.getStepNumber()-1);
		logger.debug("Loading controls for step level " + stepLevel);
		wizard = driver.findElement(By.id("wizard-p-" + stepLevel));
		loadTextBoxes();
		loadTextCombos();
	}
	
	private void loadTextBoxes() {
		List<WebElement> textBoxes = wizard.findElements(By.xpath(".//input[contains(@id, 'ST_')]"));
		Map<String, TextInput> inputs = new HashMap<>();
		for (WebElement e : textBoxes) {
			TextInput input = getTextInput(e);
			inputs.put(input.getMyIdentifier(), input);
		}
		stepExecutor.setTextBoxes(inputs);		
	}
	
	private void loadTextCombos() {
		List<WebElement> combos = wizard.findElements(By.className("select2-selection__rendered"));		
		Map<String, ComboSelect> inputs = new HashMap<>();
		for (WebElement e : combos) {			
			ComboSelect input = getComboInput(e);
			inputs.put(input.getMyIdentifier(), input);			
		}
		stepExecutor.setTextCombos(inputs);
	}
	
	private TextInput getTextInput(WebElement e) {
		String inputIdentifier = e.getAttribute("placeholder");
		TextInput input = new TextInput(e, inputIdentifier);
		System.out.println("Text ->" + input.getMyIdentifier());
		return input;
	}
	
	private ComboSelect getComboInput(WebElement e) {
		WebElement placeholder = e.findElement(By.className("select2-selection__placeholder"));
		String inputIdentifier = placeholder.getAttribute("textContent");
		ComboSelect input = new ComboSelect(driver, e, inputIdentifier);
		System.out.println("Combo ->" + input.getMyIdentifier());
		return input;
	}
}
