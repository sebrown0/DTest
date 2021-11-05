package object_models.left_menu.payroll;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import control_builder.ControlData;
import control_builder.ControlGetterComboSelect;
import enums.control_names.CommonControlNames;
import enums.control_names.EmployeeControlNames;
import enums.control_names.PayrollControlNames;
import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public final class GlobalAdjustments extends JSPanelWithIFrame {
	public static final String MENU_TITLE = "Global Adjustments";
	public static final String PANEL_TITLE = "Global Payroll Adjustments";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalAdjustments(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
		buildMyControls();
	}
	
//new ControlData(EmployeeControlNames.EMP_NAME, new ControlGetterTextOut(driver, By.xpath("/html/body/form/div[3]/div[3]/div[2]/input"))),
//new ControlData(EmployeeControlNames.SELECT_EMP, new ControlGetterEmployeeSelection(driver, By.cssSelector("i[class='fa fa-list']"), manager)),
//new ControlData(EmployeeControlNames.COMBOS, new ControlGetterDropdownCombo(driver, By.cssSelector("i[class='fa fa-window-maximize']"), manager)),
//new ControlData(EmployeeControlNames.GRID_VIEW, new ControlGetterDkGridEmployeeDetails(driver, By.cssSelector("i[class='fa fw fa-table']"), manager))
	
	private void buildMyControls() {
		var myControls = 
				List.of(
						new ControlData(
							CommonControlNames.COMPANY,
								new ControlGetterComboSelect(driver, By.cssSelector("span[aria-labelledby='select2-COMP_SELx-container']"))),
						new ControlData(
							PayrollControlNames.PAY_PERIODS,
								new ControlGetterComboSelect(driver, By.cssSelector("span[aria-labelledby='select2-PAYS_SELx-container']"))),
						new ControlData(
							CommonControlNames.DEPARTMENT,
								new ControlGetterComboSelect(driver, By.cssSelector("body > form > div:nth-child(17) > div:nth-child(2) > span > span.selection > span"))),
						/*
						 *  TODO - View Adjustment Type (what type of control is it?)
						 */
						new ControlData(
								PayrollControlNames.PAY_GROUP,
									new ControlGetterComboSelect(driver, By.id("select2-PAYGS_SELx-container"))),
						new ControlData(
								EmployeeControlNames.FULL_OR_PART_TIME,
									new ControlGetterComboSelect(driver, By.id("select2-EMPL_SELx-container"))), //#select2-EMPL_SELx-results
						new ControlData(
							EmployeeControlNames.EMPLOYEES,	
								new ControlGetterComboSelect(driver, By.cssSelector("body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span")))
													
		);			
		super.buildPanelControls(myControls);				
	}

	// Elements body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span

	// Tabs
}