package object_models.modules.payroll.left_menu.payroll;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;

import control_builder.control_getters.ControlGetterGrid;
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
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelControl;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author SteveBrown
 * @since 1.0
 * @version 1.0
 */
public final class GlobalAdjustments extends JsPanelWithIFrame implements JsPanelControl, PayrollElement {
	private ControlDataFactory controlFactory;
	private DkGrid<?> grid;
	
	public static final String MENU_TITLE = "Global Adjustments";
	public static final String PANEL_TITLE = "Global Payroll Adjustments";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public GlobalAdjustments(CoreData coreData) {
		super(coreData, PANEL_TITLE);
		
		controlFactory = new factories.ControlDataFactory(coreData);		
		buildMyControls();
	}
	
	private void buildMyControls() {
		var myControls = 
				List.of(
						controlFactory.buildDefaultComboWriteAndSelect(
								CommonControlNames.COMPANY, 
								By.cssSelector("span[aria-labelledby='select2-COMP_SELx-container']"), 
								By.id("select2-COMP_SELx-results")).getControlData(),
						
						controlFactory.buildDefaultComboWriteAndSelect(
								PayrollControlNames.PAY_PERIODS,
								By.cssSelector("span[aria-labelledby='select2-PAYS_SELx-container']"), 
								By.id("select2-PAYS_SELx-results")).getControlData(),
						
						controlFactory.buildDefaultComboWriteAndSelect(
								CommonControlNames.DEPARTMENT,								
								By.cssSelector("span[data-select2-id='13']"), 
								By.id("select2-DEPS_SELx-results")).getControlData(),
						
						controlFactory.buildDefaultComboWriteAndSelect(
								GlobalAdjustmentControlNames.VIEW_ADJUSTMENT_TYPE,
								By.id("select2-VIEW2_SELx-container"), 
								By.id("select2-VIEW2_SELx-results")).getControlData(),
						
						controlFactory.buildDefaultComboWriteAndSelect(
								PayrollControlNames.PAY_GROUP,
								By.id("select2-PAYGS_SELx-container"), 
								By.id("select2-PAYGS_SELx-results")).getControlData(),
						
						controlFactory.buildDefaultComboWriteAndSelect(
								EmployeeControlNames.FULL_OR_PART_TIME,
								By.id("select2-EMPL_SELx-container"), 
								By.id("select2-EMPL_SELx-results")).getControlData(),
						
						controlFactory.buildDefaultComboWriteAndSelect(
								EmployeeControlNames.EMPLOYEES,
								By.cssSelector("span[data-select2-id='16']"), 
								By.id("select2-EMPS_SELx-results")).getControlData(),
						
						controlFactory
							.buildButton(
									GlobalAdjustmentControlNames.ACCEPT_CRITERIA, 
									By.id("SEARCH"))
							.getControlData(),
							
						/*
						 *  TODO - Other btns
						 */
					
						controlFactory
							//TODO - Is the Locator correct. It doesn't include the toolbar.
							.buildGrid(new FindRowByEmpCode(), manager, By.id("dkrGrid"))		
							.getControlData()
															
		);			
		super.buildPanelControls(myControls);				
	}
	
	// Actions
	public Optional<Control> clickButton(ControlName cntrlName){
		Optional<Control> btn = ElementClicker.clickButton(cntrlName, this);
		
		btn.ifPresent(b -> {			
			if(cntrlName.getName().equalsIgnoreCase(GlobalAdjustmentControlNames.ACCEPT_CRITERIA.getName())) {
				super.logger.debug("Reloading grid after updating criteria");
				super.updateControl(
						CommonControlNames.DK_GRID, 
						new ControlGetterGrid<FindRowByEmpCode>(
								//Have to pass locator but is null for now.
								CommonControlNames.DK_GRID.getName(), null, coreData, new FindRowByEmpCode()));
				loadGrid();
			}
		});
		
		return btn;		
	}
	
	public Optional<Integer> getRowNumForKeyValue(String key) {
		return loadGridIfNull().getLoadedContent().getRowNumForKeyValue(key);
	}
	public Optional<Row<?>> getRowForRowIndex(Integer rowIdx){		
		return loadGridIfNull().getLoadedContent().getRowForRowIndex(rowIdx);
	}
	
	public void filterGridColumn(String filterCol, String filterTxt) {
		loadGridIfNull().getGridHeader().filterColumn(filterCol, filterTxt);
		grid.reloadAllContent();
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