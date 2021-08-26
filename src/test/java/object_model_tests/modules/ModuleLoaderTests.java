package object_model_tests.modules;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import object_models.modules.PayrollModuleLoader;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

class ModuleLoaderTests {
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
//		driver.quit();
	}
	
	@Test
	@Order(1)
	void homepageLoadedOk() {
		assertTrue(hp.isPageTitleCorrect());
	}
	
//	@Test
//	void test() {		
//		ModuleChecker checker = new ModuleChecker(driver);
//		DakarModule payrollModule = checker.getModule(ModuleNames.PAYROLL_NAME);
//		assertEquals(payrollModule.getName(), ModuleNames.PAYROLL_NAME);
////		System.out.println("-->>" + checker.getCurrentModule());
//	}

	@Test
	void openQuickLinks() {
		QuickLinks ql = new QuickLinks(driver);
		ql.clickQuickLinks();
	}
	
	@Test
	void trrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr() {
//		QuickLink link = new QuickLinkPayroll(driver);
//		link.clickMe();
	}
}
