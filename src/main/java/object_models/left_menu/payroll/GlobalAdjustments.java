package object_models.left_menu.payroll;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import control_builder.ControlGetterGrid;
import controls.Control;
import controls.ControlName;
import enums.control_names.CommonControlNames;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GlobalAdjustmentControlNames;
import enums.control_names.PayrollControlNames;
import factories.ControlDataFactory;
import object_models.dk_grid.DkGrid;
import object_models.dk_grid.FindRowByEmpCode;
import object_models.dk_grid.Row;
import object_models.helpers.ElementClicker;
import object_models.helpers.text_writer.TextWriterComboDefault;
import object_models.helpers.text_writer.TextWriterComboMulti;
import object_models.panels.JsPanelControl;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public final class GlobalAdjustments extends JsPanelWithIFrame implements JsPanelControl {
	private ControlDataFactory controlFactory;
	private DkGrid<?> grid;
	
	public static final String MENU_TITLE = "Global Adjustments";
	public static final String PANEL_TITLE = "Global Payroll Adjustments";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalAdjustments(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
		
		controlFactory = new factories.ControlDataFactory(driver);		
		buildMyControls();
	}
	
	private void buildMyControls() {
		var myControls = 
				List.of(
						controlFactory.buildComboWriteAndSelect(
								CommonControlNames.COMPANY, 
								By.cssSelector("span[aria-labelledby='select2-COMP_SELx-container']"), 
								By.id("select2-COMP_SELx-results"), 
								new TextWriterComboDefault(driver)).getControlData(),
						
						controlFactory.buildComboWriteAndSelect(
								PayrollControlNames.PAY_PERIODS,
								By.cssSelector("span[aria-labelledby='select2-PAYS_SELx-container']"), 
								By.id("select2-PAYS_SELx-results"), 
								new TextWriterComboDefault(driver)).getControlData(),
						
						controlFactory.buildComboWriteAndSelect(
								CommonControlNames.DEPARTMENT,
								By.cssSelector("body > form > div:nth-child(17) > div:nth-child(2) > span > span.selection > span"), 
								By.id("select2-DEPS_SELx-results"),
								new TextWriterComboMulti(driver.findElement(By.cssSelector("body > form > div:nth-child(17) > div:nth-child(2) > span > span.selection > span")))).getControlData(),
						
						controlFactory.buildComboWriteAndSelect(
								GlobalAdjustmentControlNames.VIEW_ADJUSTMENT_TYPE,
								By.id("select2-VIEW2_SELx-container"), 
								By.id("select2-VIEW2_SELx-results"), 
								new TextWriterComboDefault(driver)).getControlData(),
						
						controlFactory.buildComboWriteAndSelect(
								PayrollControlNames.PAY_GROUP,
								By.id("select2-PAYGS_SELx-container"), 
								By.id("select2-PAYGS_SELx-results"),
								new TextWriterComboDefault(driver)).getControlData(),
						
						controlFactory.buildComboWriteAndSelect(
								EmployeeControlNames.FULL_OR_PART_TIME,
								By.id("select2-EMPL_SELx-container"), 
								By.id("select2-EMPL_SELx-results"), 
								new TextWriterComboDefault(driver)).getControlData(),
						
						controlFactory.buildComboWriteAndSelect(
								EmployeeControlNames.EMPLOYEES,
								By.cssSelector("body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span"), 
								By.id("select2-EMPS_SELx-results"), 
								new TextWriterComboMulti(driver.findElement(By.cssSelector("body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span"))))
						.getControlData(),
						
						controlFactory
							.buildButton(
									GlobalAdjustmentControlNames.ACCEPT_CRITERIA, 
									By.id("SEARCH"))
							.getControlData(),
							
						/*
						 *  TODO - Other btns
						 */
							
						controlFactory.buildGrid(new FindRowByEmpCode()).getControlData()
															
		);			
		super.buildPanelControls(myControls);				
	}
		
	// Actions
	public Optional<Control> clickButton(ControlName cntrlName){
		Optional<Control> btn = ElementClicker.clickButton(cntrlName, this);
		
		btn.ifPresent(b -> {			
			if(cntrlName.getName().equalsIgnoreCase(GlobalAdjustmentControlNames.ACCEPT_CRITERIA.getName())) {
				super.logger.debug("Reloading grid after updating criteria");
				super.updateControl(CommonControlNames.DK_GRID, new ControlGetterGrid<FindRowByEmpCode>(driver, new FindRowByEmpCode()));
				loadGrid();
			}
		});
		
		return btn;		
	}
	
	public Optional<Integer> getRowNumForKeyValue(String key) {
		return loadGridIfNull().getContent().getRowNumForKeyValue(key);
	}
	public Optional<Row<?>> getRowForRowIndex(Integer rowIdx){		
		return loadGridIfNull().getContent().getRowForRowIndex(rowIdx);
	}
	
	public void filterGridColumn(String filterCol, String filterTxt) {
		loadGridIfNull().getGridHeader().filterColumn(filterCol, filterTxt);
		grid.reloadContent();
	}
	
	// Helpers
	private DkGrid<?> loadGridIfNull() {
		if(grid == null) {
			loadGrid();
		}
		return this.grid;
	}
	private void loadGrid() {
		grid = (DkGrid<?>) getControl(CommonControlNames.DK_GRID).get();
		grid.loadGridIfNecessary();
	}

	// Elements
	public DkGrid<?> getGrid(){
		return loadGridIfNull();
	}
	
	// Tabs
}