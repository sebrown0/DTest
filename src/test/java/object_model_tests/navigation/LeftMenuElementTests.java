package object_model_tests.navigation;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.pages.UserLoginPage;
import object_models.panels.EmployeeDetails;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

class LeftMenuElementTests {	
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	private static LeftMenu menu;
	
	@BeforeAll	
	static void setup() throws NullDriverException, InterruptedException, ExecutionException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Login.
		userLogin.loginValidUser(UserProvider.userPortal());
		// Load the menu.
		menu = new LeftMenu(driver);
	}
	
	@AfterAll
	static void tearDown() {
//		driver.quit();
	}
	
	@Test
	void clickEmployees_then_EmployeeOthers() {
		menu.clickParent("Employees");
		menu.clickParent("Employee Others");
	}
	
	@Test
	void clickEmployees_EmployeeDetails() {
		EmployeeDetails emp = (EmployeeDetails) menu.clickParent("Employees").clickChild("Employee Details");;
	}

}
