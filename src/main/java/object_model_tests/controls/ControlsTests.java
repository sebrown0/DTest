/**
 * 
 */
package object_model_tests.controls;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import context_manager.ContextManager;
import control_builder.PageControl;
import controls.TextOut;
import dto.Employee;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GroupControlNames;
import logging.TestResultLogger;
import object_models.controls.DropdownCombo;
import object_models.controls.EmployeeSelection;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.employee.EmployeeFromXml;
import providers.employee.EmployeeProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Verify and employee loaded by EmployeeProvider.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class ControlsTests {
	private static HomePage homepagePayroll;	
	private static LeftMenu menu;
	private static EmployeeDetails empDetails;
	private static Employee emp;
	private static PageControl empDetailsPageControl;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		// Load the menu.
		menu = homepagePayroll.getLeftMenu();
		// Load the employee details page.
		empDetails = (EmployeeDetails) menu.clickParent("Employees").clickAndLoad(EmployeeDetails.class).get();
		// Get the employee we're going to use with required fields from the provider.
		EmployeeProvider empProvider = new EmployeeFromXml();
		emp = empProvider.getEmployeeWithRequiredFields("1");
		// Get PageControl for EmployeeDetails
		empDetailsPageControl = empDetails.getPanelControl();
	}
		
	// Controls - Start
	@Test @Order(1)
	void employeeSelection_selectEmployee() {
		EmployeeSelection empSelection = (EmployeeSelection) empDetailsPageControl.getControl(GroupControlNames.SELECT_EMP).get();
		empSelection.clickRow("3");		
		assertEquals("0134213A", empDetails.tab().basicDetails().iDCardNumber().getTextByValue());				
	}	
	@Test @Order(2)
	void textOut_checkCode() {
		TextOut textOut = (TextOut) empDetailsPageControl.getControl(EmployeeControlNames.EMP_CODE).get();
		assertEquals(emp.getEmpCode(), textOut.getTextByValue());		
	}	
	@Test @Order(3)
	void openCombos() {		
		DropdownCombo combos = (DropdownCombo) empDetailsPageControl.getControl(GroupControlNames.COMBOS).get();		
		combos.close(); 
		ContextManager cm = combos.getContextManager();
		assertEquals(cm.getCurrentContext().getContextId().getExpectedName(), "Employee Details");
	}		
	@Test @Order(4)
	void openGrid() {
//		PageControl control = empDetails.getEmployeeControl();		
//		DkGridEmployeeDetails grid = (DkGridEmployeeDetails) control.getControl(EmployeeControlNames.GRID_VIEW).get();
//		grid.close();
	}


	@AfterAll
	static void tearDown() {		
//		homepagePayroll.close();
	}
}
