package object_model_tests.employee_creation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import dto.Employee;
import exceptions.NullDriverException;
import logging.TestResultLogger;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.modules.payroll.PayrollModuleLoader;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCreation;
import object_models.top_right_nav_bar.common.NavBarElement;
import providers.EmployeeFromXml;
import providers.EmployeeProvider;
import providers.XMLFileProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith(TestResultLogger.class)
class EmployeeCreationTests {
	private static WebDriver driver;	
	private static EmployeeCreationWizard wizard; 
	private static Employee emp;
	
	@BeforeAll	
	static void setup() throws NullDriverException {	
		ConfigReader configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		UserLoginPage userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Get a home page after successful login
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the employee creation wizard from the nav bar.
		NavBarElement navEmpWizard = hp.getTopRightNavBar().getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
		// Open the wizard.
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();
		// Get the employee we're going to use with required fields from the provider.
		EmployeeProvider empProvider = new EmployeeFromXml();
		emp = empProvider.getEmployeeRequired("1");
	}
	
	@AfterAll
	static void teardown() {
		driver.quit();
	}
	
	@Test
	void createEmployee() {
		wizard.createEmployee(emp);		
	}
}