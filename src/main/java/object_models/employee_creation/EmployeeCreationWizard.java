/**
 * 
 */
package object_models.employee_creation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.ControlCombo;
import controls.ControlText;
import controls.MapControl;
import controls.MappingStrategy;
import controls.PageMap;
import controls.PageMapper;
import dto.Employee;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCreationWizard extends JSPanelWithIFrame {
	private Logger logger = LogManager.getLogger();
	private PageMapper mapper;
	private PageMap pageMap;
	
	public static final String PANEL_TITLE = "Employee Creation Wizard";		
	
	public EmployeeCreationWizard(WebDriver driver) {
		super(driver, PANEL_TITLE);
		
		mapper = new PageMapper(new MappingStrategyWizard(driver));
		pageMap = mapper.mapControls().getPageMap();			
	}
	
	public void createEmployee(Employee emp)  {
		logger.debug("Creating employee with wizard");
		WizardStepExecutor step1 = new WizardStepOne(pageMap, driver, 1);
		WizardStepExecutor step2 = step1.writeValues(emp).getNext();
		WizardStepExecutor step3 = step2.writeValues(emp).getNext();		
		WizardStepExecutor step4 = step3.writeValues(emp).getNext();
		WizardStepExecutor step5 = step4.writeValues(emp).getNext();
		step5.writeValues(emp).getNext();
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
