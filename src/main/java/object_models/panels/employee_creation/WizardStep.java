package object_models.panels.employee_creation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.PageMap;
import object_models.element.InputWriter;

public abstract class WizardStep implements WizardStepExecutor, WizardMove{
	private int stepNumber;
	
	protected PageMap pageMap;
	protected Map<String, InputWriter> textBoxes;
	protected Map<String, InputWriter> combos;	
	protected WebDriver driver;
	protected By byNext = By.xpath("/html/body/div/div/form/div[3]/ul/li[2]/a");
		
	//GIVE CONTROL MAPPER//
	public WizardStep(PageMap pageMap, WebDriver driver, int stepNumber) {
		this.pageMap = pageMap;
		this.stepNumber = stepNumber;
		this.driver = driver;
//		loadControls();
	}

	@Override
	public int getStepNumber() {
		return stepNumber;
	}
	
//	@Override
//	public void loadControls() {
//		loadTextBoxes();
//		loadTextCombos();
//	}
	
//	protected void loadTextBoxes() {
//		LoaderData text = new WizardLoaderText(driver, this);
//		ControlMapper loader = new ControlMapper(driver, text);
//		textBoxes = loader.loadControls().getControls();		
//	}
//	
//	protected void loadTextCombos() {
//		LoaderData text = new WizardLoaderCombo(driver, this);
//		ControlMapper loader = new ControlMapper(driver, text);
//		combos = loader.loadControls().getControls();		
//	}
	
	@Override
	public void goGack() {
		// TODO Auto-generated method stub		
	}
	@Override
	public WizardStepExecutor goNext() {		
		return null;	
	}

}
