package object_model_tests.global_adjustments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import dto.Employee;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GlobalAdjustmentControlNames;
import enums.control_names.PayrollControlNames;
import logging.TestResultLogger;
import object_models.dialog.DialogOkCancel;
import object_models.dk_grid.Cell;
import object_models.dk_grid.CellChecker;
import object_models.dk_grid.DkGrid;
import object_models.dk_grid.Row;
import object_models.element.ElementButton;
import object_models.left_menu.payroll.GlobalAdjustments;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.employee.EmployeeFromXml;
import providers.employee.EmployeeProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 *
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
	private static Employee emp;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		globalAdjustments =	(GlobalAdjustments) homepagePayroll.getLeftMenu().clickAndLoad(GlobalAdjustments.class).get();
		
		// Get an employee with random code
//		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
//		emp = empProvider.getAnyEmpWithRandomCode();
		
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getEmployeeWithRequiredFields("1");
		
//		String randomEmpCode = emp.getEmpCode();
		
//		emp.setIdCardNumber(randomEmpCode);
//		emp.setFirstName("Clint");
//		emp.setLastName("Eastwood" + "_" + randomEmpCode.substring(0,4));
//		emp.setLastName("Eastwood");
	}		
				
	@Test @Order(1)
	void createEmployee() {		
		// Open the wizard and create the emp
//		TopRightNavBar navBar = homepagePayroll.getTopRightNavBar();
//		NavBarElement empCr = navBar.getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
//		EmployeeCreationWizard wizard = (EmployeeCreationWizard) empCr.clickElement();		
//		wizard.createEmployeeIgnoreConfirmation(emp);	
//		wizard.close();
	}
	
	@Test @Order(2)
	void setCriteriaForEmployee_implictPass_ifCompletes() {
		ComboWriteAndSelect cmbPayGroup = (ComboWriteAndSelect) globalAdjustments.getControl(PayrollControlNames.PAY_GROUP).get();
		cmbPayGroup.click();		
		cmbPayGroup.selectFullText(emp.getPayGroup());
		
		ComboWriteAndSelect cmbFullPartTime = (ComboWriteAndSelect) globalAdjustments.getControl(EmployeeControlNames.FULL_OR_PART_TIME).get();
		cmbFullPartTime.click();		
		cmbFullPartTime.selectFullText("Both");
		
		globalAdjustments.clickButton(GlobalAdjustmentControlNames.ACCEPT_CRITERIA);		 	
	}

	@Test @Order(3)
	void createNewRecord_implictPass_ifCompletes() {
		DkGrid<?> grid = globalAdjustments.getGrid();
		grid.addRecord();
	}
	
	@Test @Order(4)
	void enterEmployee() {
		Row<?> row = globalAdjustments.getRowForRowIndex(0).get();		
		Cell cell = globalAdjustments.getGrid().getCell(row, "Employee");
		
		CellChecker checker = new CellChecker(homepagePayroll, cell);
		PopupComboSelect combo = (PopupComboSelect) checker.getPopupType();
//		combo.writeText("F F - (F)");
//		combo.writeText(emp.getFormalName() + " - " + emp.getEmpCode());
		combo.writeText(emp.getFormalName() + " - (0134213A)");
//		combo.writeText(emp.getFormalName());
	}
	
	@Test @Order(5)
	void enterExtraHoursCode() {		
		Row<?> row = globalAdjustments.getRowForRowIndex(0).get();		
		Cell cell = globalAdjustments.getGrid().getCell(row, "Extra Hours");
				
		CellChecker checker = new CellChecker(homepagePayroll, cell);
		PopupComboSelect combo = (PopupComboSelect) checker.getPopupType();		
		combo.writeText("Overtime @ 1.5");
		
		String cellTxt = cell.getCurrentValue().get();
		assertTrue(cellTxt.contains("Overtime @ 1.5"));
	}
	
	@Test @Order(6)
	void enterAmount() {
		Row<?> row = globalAdjustments.getRowForRowIndex(0).get();		
		Cell cell = globalAdjustments.getGrid().getCell(row, "Hours / Amount");
		cell.writeText("2");
		
		String cellTxt = cell.getCurrentValue().get();
		assertEquals("2", cellTxt.substring(0, 1));
	}
	
	@Test @Order(7)
	void saveRecord_implictPass_ifCompletes() {
		DkGrid<?> grid = globalAdjustments.getGrid();
		DialogOkCancel frm = grid.saveRecord();		
		assertFalse(frm == null);
		
		ElementButton ok = frm.getBtnOk().get();
		ok.click();
	}
	
	@AfterAll
	public static void tearDown() {			
//		homepagePayroll.close();
	}
	
}