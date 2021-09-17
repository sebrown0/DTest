/**
 * 
 */
package object_model_tests.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import dto.Employee;
import exceptions.NullDriverException;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.modules.payroll.PayrollModuleLoader;
import object_models.pages.UserLoginPage;
import providers.EmployeeFromXml;
import providers.EmployeeProvider;
import providers.XMLFileProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Verify and employee loaded by EmployeeProvider.
 */
@ExtendWith(TestResultLogger.class)
class EmployeeDetailsTests {
	private static WebDriver driver;	
	private static LeftMenu menu;
	private static EmployeeDetails empDetails;
	private static Employee emp;
	
	@BeforeAll	
	static void setup() throws NullDriverException, InterruptedException, ExecutionException {	
		ConfigReader configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		UserLoginPage userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Login.
		userLogin.loginValidUser(UserProvider.userPortal());
		// Load the menu.
		menu = new LeftMenu(driver);
		// Load the employee details page.
		empDetails = (EmployeeDetails) menu.clickParent("Employees").clickAndLoad(EmployeeDetails.MENU_TITLE).get();
		// Get the employee we're going to use with required fields from the provider.
		EmployeeProvider empProvider = new EmployeeFromXml();
		emp = empProvider.getEmployeeRequired("1");
	}
		
	@Test	
	void checkCode() {
		empDetails.tab().basicDetails().click();
		assertEquals(emp.getEmpCode(), empDetails.employeeCode().getTextByValue());		
	}

	@Test
	void checkIdCardNo() {
		empDetails.tab().basicDetails().click();
		assertEquals(emp.getIdCardNumber(), empDetails.iDCardNumber().getTextByValue());
	}
	
	@Test
	void set_and_check_PartTimerHoursPerDay() {
		empDetails.tab().settings().click();
		empDetails.partTimerHoursPerDay().clear();
		empDetails.partTimerHoursPerDay().writeText("5");
		assertEquals("5", empDetails.partTimerHoursPerDay().getTextByValue());
	}
	
	@AfterAll
	static void tearDown() {		
		driver.quit();
	}
}
