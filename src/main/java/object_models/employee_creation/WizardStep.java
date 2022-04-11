package object_models.employee_creation;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import control_mapping.PageMap;
import object_models.element.InputWriter;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 */
public abstract class WizardStep implements WizardStepExecutor, WizardMove{
	private int stepNumber;
	
	protected PageMap pageMap;
	protected Map<String, InputWriter> textBoxes;
	protected Map<String, InputWriter> combos;	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected By byNext;
	
	public WizardStep(PageMap pageMap, WebDriver driver, int stepNumber) {
		this.pageMap = pageMap;
		this.stepNumber = stepNumber;
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.byNext = By.xpath("/html/body/div/div/form/div[3]/ul/li[2]/a");
	}

	protected void loadAndWaitForStep(int stepNum) {
		String strStepNum = String.valueOf(stepNum - 1);
		WebElement btnStep = driver.findElement(By.id("wizard-t-" + strStepNum));
		btnStep.click();
	
		System.out.println("->" + btnStep.getText()); // TODO - remove or log
		
		By stepLocator = By.id("wizard-p-" + strStepNum);
		new WebDriverWait(driver, Duration.ofSeconds(10))
							.ignoring(StaleElementReferenceException.class)
							.until(ExpectedConditions.visibilityOfElementLocated(stepLocator));
	}
	
	@Override
	public int getStepNumber() {
		return stepNumber;
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
