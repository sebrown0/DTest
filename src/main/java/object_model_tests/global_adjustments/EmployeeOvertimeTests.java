package object_model_tests.global_adjustments;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.ComboWriteAndSelect;
import logging.TestResultLogger;
import object_models.dk_grid.Cell;
import object_models.dk_grid.CellChecker;
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
				
//	@Test @Order(1)
//	void loadEmployee_implictPass_ifCompletes() {
//		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(EmployeeControlNames.EMPLOYEES).get();
//		cmbComp.click();		
//		cmbComp.selectFullText("F F");
//		cmbComp.click();		
//		
//		globalAdjustments.clickButton(GlobalAdjustmentControlNames.ACCEPT_CRITERIA);		
//	}

	@Test @Order(2)
	void enterExtraHoursCode() {
		Optional<Integer> rowIdx = globalAdjustments.getRowNumForKeyValue("F F - (F)");		
		Row<?> row = globalAdjustments.getRowForRowIndex(rowIdx.get()).get();
		
//		Cell cell = globalAdjustments.getGrid().getCell(row, "Hours / Amount");
//		Cell cell = globalAdjustments.getGrid().getCell(row, "Extra Hours");
//		Cell cell = globalAdjustments.getGrid().getCell(row, "Date To");
		Cell cell = globalAdjustments.getGrid().getCell(row, "Absences");
		
		WebDriver driver = homepagePayroll.getWebDriver();
		WebElement e = cell.getMyElement();
		
		CellChecker checker = new CellChecker(driver, e);

		ComboWriteAndSelect combo = (ComboWriteAndSelect) checker.getPopupType();		
		combo.writeText("Arm");

		 	

	}
	
	@AfterAll
	public static void tearDown() {			
//		homepagePayroll.close();
	}
	
}