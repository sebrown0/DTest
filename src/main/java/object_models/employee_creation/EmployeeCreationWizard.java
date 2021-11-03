/**
 * 
 */
package object_models.employee_creation;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
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
		WizardStepExecutor step2 = writeValuesForStepAndMoveNext(step1, emp);
		WizardStepExecutor step3 = writeValuesForStepAndMoveNext(step2, emp);		
		WizardStepExecutor step4 = writeValuesForStepAndMoveNext(step3, emp);
		WizardStepExecutor step5 = writeValuesForStepAndMoveNext(step4, emp);
		step5.writeValues(emp).getNext();
		
		return getConfirmationForm();
	}
	private WizardStepExecutor writeValuesForStepAndMoveNext(WizardStepExecutor step, Employee emp) {
		step.writeValues(emp);	
//		try {
//			step.writeValues(emp);	
//		} catch (StaleElementReferenceException e) {
//			System.out.println("*************** IS STALE ***************" ); // TODO - remove or log 	
//		}		
		return step.getNext();
	}
	
	private FormFadeShow getConfirmationForm() {		
		return new FormFadeShow(driver, manager);
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
//					new ControlCombo(driver, By.cssSelector("span[role='combobox']"), "textContent")};
					new ControlCombo(driver, By.cssSelector("span[class='select2-selection__placeholder']"), "textContent")};

			return List.of(objs);
		}		
	}
	
	@Override
	public void close() {
//		Optional<State> state = Optional.ofNullable(getContextManager().getCurrentContext().getState());
		System.out.println("Closing Wizard" ); // TODO - remove or log
		
		getContextManager().removeAndCloseContext(getMyContext());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[href='#previous']")));
		
//		state.ifPresentOrElse(
//				s -> { 
//					s.close(); 
//				}, 
//				new Runnable() {					
//					@Override
//					public void run() {
//						logger.error("Could not close panel [" + PANEL_TITLE + "]");
//					}
//				});
	}
//	@Override
//	public void close() {
//		Optional<State> state = getContextManager().switchToStateInCurrentContext(StateHeaderPanel.class);
//		System.out.println("Closing Wizard" ); // TODO - remove or log 	
//		state.ifPresentOrElse(
//				s -> { 
//					s.close(); 
//				}, 
//				new Runnable() {					
//					@Override
//					public void run() {
//						logger.error("Could not close panel [" + PANEL_TITLE + "]");
//					}
//				});
//	}
}
