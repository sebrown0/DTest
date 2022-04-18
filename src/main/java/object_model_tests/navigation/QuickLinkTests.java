package object_model_tests.navigation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import library.dakar_hr.entities.company.Company;
import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.object_models.modules.payroll.PayrollModuleElements;
import library.dakar_hr.pages.homepage.HomePage;
import library.dakar_hr.top_right_nav_bar.TopRightNavBar;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

class QuickLinkTests {	
	private static WebDriver driver;
	@SuppressWarnings("unused")
	private static TopRightNavBar navBar;
	private static String uri;
	
	@BeforeAll	
	static void setup() throws NullDriverException {
		ConfigReader configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		uri = configReader.getUri();
		// Get a login page, with the required module loaded.
		
		/*
		 * ADD CONTEXT MANAGER
		 */
		UserLoginPage userLogin = new UserLoginPage(driver, uri, new PayrollModuleElements(new Company("Mars Incorporated Ltd")));
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
