/**
 * 
 */
package object_model_tests.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.top_right_nav_bar.ElementChecker;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinksPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.ModuleNames;
import providers.XMLFileProvider;
import test_data.UserProvider;
import xml_reader.ConfigReader;

/**
 * @author Steve Brown
 *
 * Test the elements of the home page for payroll.
 */
@ExtendWith(TestResultLogger.class)
class PayrollHomepageElementsTest {
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;
	
	@BeforeAll	
	static void setUpBeforeClass() throws Exception {		
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@Test
	@Order(1)
	void homepageLoadedOk() {
//		assertTrue(hp.isPageTitleCorrect());
	}

	@Test 
	@Order(2)
	void checkModuleName() {
		assertTrue(ModuleNames.isValidName(hp.getActualModuleName()));
		assertTrue(hp.getActualModuleName().equals(ModuleNames.PAYROLL_NAME));
	}
	
	@Test
	@Order(3)
	void topRightNavBarElementsOk() {
		TopRightNavBar topRightNavBar = hp.getTopRightNavBar();
		ElementChecker elementChecker = topRightNavBar;
		assertTrue(elementChecker.checkElementTitles());
	}

	@Test
	@Order(4)
	void loadPersonnel() {
		QuickLinksPayroll links = (QuickLinksPayroll) hp.getTopRightNavBar().getQuickLinks();
		links.getPersonnel().clickMe();
	}
}
