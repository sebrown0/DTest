package object_models.left_menu.payroll;

import java.util.List;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import factories.ControlDataFactory;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalAdjustments extends JSPanelWithIFrame {
	private ControlDataFactory controlFactory;
	
	public static final String MENU_TITLE = "Global Adjustments";
	public static final String PANEL_TITLE = "Global Payroll Adjustments";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalAdjustments(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
		controlFactory = new factories.ControlDataFactory(driver);
		
		buildMyControls();
//		ControlFactory.ControlDataX.getCompanyData()
	}
	
	private void buildMyControls() {
		var myControls = 
				List.of(
						controlFactory.buildCompanyComboData().getData(),
						controlFactory.buildPayPeriodComboData().getData(),						
						controlFactory.buildDepartmentComboData().getData(),
						controlFactory.buildViewAdjustmentTypeComboData().getData(),
						controlFactory.buildPayGroupComboData().getData(),
						controlFactory.buildFullOrPartTimeComboData().getData(),
						controlFactory.buildEmployeesComboData().getData()													
		);			
		super.buildPanelControls(myControls);				
	}
	
	// Elements body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span

	// Tabs
}