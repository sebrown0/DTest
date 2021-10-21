/**
 * 
 */
package object_models.employee_creation;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import context_manager.states.State;
import context_manager.states.StateHeaderPanel;
import controls.ControlCombo;
import controls.ControlText;
import controls.MapControl;
import controls.MappingStrategy;
import controls.PageMap;
import controls.PageMapper;
import dto.Employee;
import object_models.forms.FormFadeShow;
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
	
	public EmployeeCreationWizard(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
		
		mapper = new PageMapper(new MappingStrategyWizard(driver));
		pageMap = mapper.mapControls().getPageMap();			
	}
	
	public FormFadeShow createEmployee(Employee emp)  {
		logger.debug("Creating employee with wizard");
		WizardStepExecutor step1 = new WizardStepOne(pageMap, driver, 1);
		WizardStepExecutor step2 = step1.writeValues(emp).getNext();
		WizardStepExecutor step3 = step2.writeValues(emp).getNext();		
		WizardStepExecutor step4 = step3.writeValues(emp).getNext();
		WizardStepExecutor step5 = step4.writeValues(emp).getNext();
		step5.writeValues(emp).getNext();
		
		return getConfirmationForm();
	}
		
	private FormFadeShow getConfirmationForm() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//		WebElement frm = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(
//						By.cssSelector("div[class='modal fade show']")));
//		
//		WebElement title = frm.findElement(By.className("modal-body"));
//		WebElement btn = frm.findElement(By.className("close"));
		
		return new FormFadeShow(driver, contextManager);
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
	
	@Override
	public void close() {
		Optional<State> state = getContextManager().switchToStateInCurrentContext(StateHeaderPanel.class);
		System.out.println("Closing Wizard" ); // TODO - remove or log 	
		state.ifPresentOrElse(
				s -> { s.close(); }, 
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Could not close panel [" + PANEL_TITLE + "]");
					}
				});
	}
}
