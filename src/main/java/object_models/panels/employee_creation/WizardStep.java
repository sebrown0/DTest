package object_models.panels.employee_creation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.element.ComboSelect;
import object_models.element.TextInput;

public abstract class WizardStep implements WizardStepExecutor, WizardMove{
	private int stepNumber;
	
	protected Map<String, TextInput> textBoxes;
	protected Map<String, ComboSelect> combos;	
	protected WebDriver driver;
	protected By byNext = By.xpath("/html/body/div/div/form/div[3]/ul/li[2]/a");
		
	public WizardStep(WebDriver driver, int stepNumber) {
		this.stepNumber = stepNumber;
		this.driver = driver;
	}

	@Override
	public int getStepNumber() {
		return stepNumber;
	}

	@Override
	public void setTextBoxes(Map<String, TextInput> textBoxes) {
		this.textBoxes = textBoxes;
	}

	@Override
	public void setTextCombos(Map<String,ComboSelect> combos) {
		this.combos = combos;
	}
		
	@Override
	public void goGack() {
		// TODO Auto-generated method stub		
	}
	@Override
	public WizardStepExecutor goNext() {		
		return null;	
	}
	
}
