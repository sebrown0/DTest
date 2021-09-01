/**
 * 
 */
package object_models.panels.employee_creation;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controls.ControlCombo;
import controls.ControlText;
import controls.MapControl;
import controls.MappingStrategy;
import controls.PageMap;
import controls.PageMapper;
import dto.Employee;
import object_models.helpers.ChildElement;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCreationWizard implements ChildElement {	
	private WebDriver driver;	
	private IFrame iFrame;	
	private Logger logger = LogManager.getLogger();
	private PageMapper mapper;
	private PageMap pageMap;
	
	public static final String PANEL_TITLE = "Employee Creation Wizard";
		
	public EmployeeCreationWizard(WebDriver driver) {
		this.driver = driver;
		
		iFrame = new IFrame(driver, PANEL_TITLE);		
		waitForLoad();
		switchToIFrame();
		mapper = new PageMapper(new MappingStrategyWizard(driver));
		pageMap = mapper.mapControls().getPageMap();
				
	}
	
	public void createEmployee(Employee emp)  {
		logger.debug("Creating elements");
		WizardStepExecutor stepOne = new WizardStepOne(pageMap, driver, 1);
		stepOne.writeValues(emp);
//		WizardStepExecutor stepTwo = stepOne.writeValues(emp).getNext();
//		stepTwo.writeValues(emp);
	}
		
	public void switchToIFrame() {
		iFrame.switchUsingTitle();
	}

	public void waitForLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.className("jsPanel-title"), "innerHTML", PANEL_TITLE));		
	}
	
	/*
	 * This object's mapping strategy.
	 */
	private class MappingStrategyWizard implements MappingStrategy{
		private WebDriver driver;
		
		public MappingStrategyWizard(WebDriver driver) {
			this.driver = driver;
		}

		@Override
		public List<MapControl> getList() {
			MapControl[] objs = {
					new ControlText(driver, By.cssSelector("input[type='text']"), "placeholder"),
					new ControlCombo(driver, By.cssSelector("span[class='select2-selection__placeholder']"), "textContent")};
			return List.of(objs);
		}		
	}	
}
