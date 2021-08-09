/**
 * 
 */
package object_model_tests.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import object_models.modules.PayrollModuleLoader;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.ModuleNames;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

/**
 * @author Steve Brown
 *
 */
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
	
//	@Test
//	void homepageLoadedOk() {
//		// Check that the home page has the correct title.
//		assertTrue(hp.isPageTitleCorrect());
//	}
//
//	@Test 
//	void checkModuleName() {
//		assertTrue(ModuleNames.isValidName(hp.getModuleName()));
//		assertTrue(hp.getModuleName().equals(ModuleNames.PAYROLL_NAME));
//	}
	
	@Test
	void topRightNavBarElementsOk() {
		// Check that the nav bar's elements are correct.
		assertTrue(hp.getTopRightNavBar().isAllElementsCorrect());
	}
	
//	@Test
//	void useQuickLink_toLoadPayroll() {
//		QuickLinks links = hp.getTopRightNavBar().getNavBarElements().getQuickLinks();
//		links.clickPayroll();
//		assertTrue(hp.getModuleName().equals(ModuleNames.PAYROLL_NAME));
//	}
	
//	@Test
//	void loadQuickLinks() {
//		QuickLinks ql = hp.getTopRightNavBar().getNavBarElements().getQuickLinks();
//		ql.clickQuickLinks();
//		ql.clickPayroll();
//	}
	
//	@Test
//	void buildLeftMenu_loadEmployeeList() {
//		LeftMenu leftMenu = hp.getLeftMenu();
//		leftMenu.buildMenu();		
//		
//		JsPanel empList = leftMenu.loadEmployeeList();
//		String panelName = driver.findElement(By.className("jsPanel-title")).getAttribute("innerHTML");
//		assertEquals(empList.getTitle(), panelName);
//	}
	
}




//@Test
//void clickEmpCreationWizard() {		
//WebElement elem = driver.findElement(By.className("mobile-leftside-hider"));
//List<WebElement> refList = elem.findElements(By.tagName("a"));
//String href = null;
//String jsCode;
//
//for(WebElement we : refList) {
//	href = we.getAttribute("href");
//	assertTrue(href.contains("Employee List"));
//  System.out.println("href = " + href);
//}
//
//jsCode = href.substring(11,href.length());
//System.out.println("jsCode = " + jsCode);		
//
//JavascriptExecutor js = (JavascriptExecutor) driver;
//js.executeScript(jsCode);

//NavBarElement elem = new EmployeeGridView(driver);
//hp.getTopRightNavBar().clickNavbarElement(elem);
//}