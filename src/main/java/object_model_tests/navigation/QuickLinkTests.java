package object_model_tests.navigation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import entities.Company;
import exceptions.NullDriverException;
import object_models.modules.payroll.PayrollModuleElements;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import object_models.top_right_nav_bar.common.TopRightNavBar;
import providers.XMLFileProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

class QuickLinkTests {	
	private static WebDriver driver;
	@SuppressWarnings("unused")
	private static TopRightNavBar navBar;
	
	@BeforeAll	
	static void setup() throws NullDriverException {
		ConfigReader configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		
		/*
		 * ADD CONTEXT MANAGER
		 */
		UserLoginPage userLogin = new UserLoginPage(driver, new PayrollModuleElements(new Company("Mars Incorporated Ltd")));
		/*
		 * ADD CONTEXT MANAGER
		 */
		
		// Get a home page after successful login
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		// Get top-right-nav-bar
		navBar = hp.getTopRightNavBar();
	}
	
	@AfterAll
	static void teardown() {
		driver.quit();
	}
	
	@Test
	void test() {
//		navBar
	}

}
