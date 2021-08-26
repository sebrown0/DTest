package object_model_tests.navigation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

class QuickLinkTests {	
	private static WebDriver driver;
	private static TopRightNavBar navBar;
	
	@BeforeAll	
	static void setup() throws NullDriverException {
		ConfigReader configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		UserLoginPage userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
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
