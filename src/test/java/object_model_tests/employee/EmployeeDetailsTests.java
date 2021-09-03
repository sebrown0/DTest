/**
 * 
 */
package object_model_tests.employee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import listeners.TestResultLogger;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.pages.UserLoginPage;
import object_models.panels.EmployeeDetails;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

/**
 * @author Steve Brown
 *
 */
//@ExtendWith(TestResultLogger.class)
class EmployeeDetailsTests {
	private static WebDriver driver;
//	private static UserLoginPage userLogin;
//	private static ConfigReader configReader;	
//	private static LeftMenu menu;
	private static EmployeeDetails empDetails;
	
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
		LeftMenu menu = new LeftMenu(driver);
		// Get the employee details page.
		empDetails = (EmployeeDetails) menu.clickParent("Employees").clickChild("Employee Details");
	}
	
	
		
	@Test
	void clickEmployees_and_get_EmployeeDetails() {
		
		
	}

	@AfterAll
	static void tearDown() {
		empDetails.closePanel();
//		driver.quit();
	}
}
