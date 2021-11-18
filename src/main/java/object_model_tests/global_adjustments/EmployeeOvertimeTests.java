package object_model_tests.global_adjustments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.ComboWriteAndSelect;
import controls.PopupComboSelect;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GlobalAdjustmentControlNames;
import logging.TestResultLogger;
import object_models.dk_grid.Cell;
import object_models.dk_grid.CellChecker;
import object_models.dk_grid.DkGrid;
import object_models.dk_grid.Row;
import object_models.left_menu.payroll.GlobalAdjustments;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/*
 * Test the grid in Global Payroll Adjustments.
 * 
 * The tests should be run in order as they go
 * thru the process of creating the grid to getting
 * and using grid elements.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class EmployeeOvertimeTests {	
	private static HomePage homepagePayroll;	
	private static GlobalAdjustments globalAdjustments;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		globalAdjustments =	(GlobalAdjustments) homepagePayroll.getLeftMenu().clickAndLoad(GlobalAdjustments.class).get();
	}		
				
	@Test @Order(1)
	void createEmployee() {
	}
	
	@Test @Order(2)
	void loadEmployee_implictPass_ifCompletes() {
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(EmployeeControlNames.EMPLOYEES).get();
		cmbComp.click();		
		cmbComp.selectFullText("Borg Joey");
		cmbComp.click();		
		
		globalAdjustments.clickButton(GlobalAdjustmentControlNames.ACCEPT_CRITERIA);		
	}

	@Test @Order(3)
	void createNewRecord() {
		DkGrid<?> grid = globalAdjustments.getGrid();
		assertEquals(-1, grid.getContent().getLastRowNum()); // Should be nothing in the grid
		grid.addRecord();
		assertEquals(0, grid.getContent().getLastRowNum()); // Should be 1 record in the grid
	}
	
	@Test @Order(4)
	void enterExtraHoursCode() {		
		Row<?> row = globalAdjustments.getRowForRowIndex(0).get();		
		Cell cell = globalAdjustments.getGrid().getCell(row, "Extra Hours");
				
		CellChecker checker = new CellChecker(homepagePayroll.getWebDriver(), cell);
		PopupComboSelect combo = (PopupComboSelect) checker.getPopupType();		
		combo.writeText("Overtime @ 1.5");
		
		String cellTxt = cell.getCurrentValue().get();
		assertTrue(cellTxt.contains("Overtime @ 1.5"));
	}
	
	@Test @Order(5)
	void enterAmount() {
		
	}
	
	@Test @Order(6)
	void saveRecord() {
		
	}
	
	@Test @Order(6)
	void check_dateFrom() {
		
	}
	
	@AfterAll
	public static void tearDown() {			
//		homepagePayroll.close();
	}
	
}