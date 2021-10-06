/**
 * 
 */
package object_model_tests.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import dto.Employee;
import logging.TestResultLogger;
import object_models.element.TextInOut;
import object_models.employee.EmployeeSelection;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.EmployeeFromXml;
import providers.EmployeeProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Verify and employee loaded by EmployeeProvider.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeDetailsTests {
	private static HomePage homepagePayroll;	
	private static LeftMenu menu;
	private static EmployeeDetails empDetails;
	private static Employee emp;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		// Load the menu.
		menu = homepagePayroll.getLeftMenu();
		// Load the employee details page.
		empDetails = (EmployeeDetails) menu.clickParent("Employees").clickAndLoad(EmployeeDetails.MENU_TITLE).get();
		// Get the employee we're going to use with required fields from the provider.
		EmployeeProvider empProvider = new EmployeeFromXml();
		emp = empProvider.getEmployeeRequired("1");
	}
		
	@Test	
	void checkCode() {
		empDetails.tab().basicDetails();
		assertEquals(emp.getEmpCode(), empDetails.employeeCode().getTextByValue());		
	}

	@Test
	void checkIdCardNo() {		
		assertEquals(emp.getIdCardNumber(), empDetails.tab().basicDetails().iDCardNumber().getTextByValue());
	}
	
	@Test
	void set_and_check_PartTimerHoursPerDay() {
		TextInOut partTimerHoursPerDay = empDetails.tab().settings().partTimerHoursPerDay();		
		partTimerHoursPerDay.clear();
		partTimerHoursPerDay.writeText("5");
		assertEquals("5", partTimerHoursPerDay.getTextByValue());
	}
	
	// Employee selection start		
	@Test
	void selectEmployee() {
		EmployeeSelection empSelection = empDetails.showEmployeeSelection();		
		empSelection.clickRow("3");		
		assertEquals("475070M", empDetails.tab().basicDetails().iDCardNumber().getTextByValue());				
	}	
	// Employee selection end
	
	@AfterAll
	static void tearDown() {		
//		homepagePayroll.close();
	}
}
