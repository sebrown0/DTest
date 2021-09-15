package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import logging.TestResultLogger;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarDakarIntelligence;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmpGridView;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCVPayroll;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;
import object_models.navigation.top_right_nav_bar.elements.NavBarMyCoLastViewed;
import object_models.navigation.top_right_nav_bar.elements.NavBarNewEmployments;
import object_models.navigation.top_right_nav_bar.elements.NavBarNotifications;
import object_models.navigation.top_right_nav_bar.elements.NavBarTerminations;
import object_models.navigation.top_right_nav_bar.elements.NavBarUserManagment;
import object_models.navigation.top_right_nav_bar.elements.NavBarVisualReports;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith(TestResultLogger.class)
class TopRightNavBar_Personnel_ElementsTests {	
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	
	@BeforeAll	
	static void setup() throws NullDriverException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@AfterAll
	static void teardown() {
		driver.quit();
	}
	
	@Test
	@Order(1)
	void homepageLoadedOk() {
//		assertTrue(hp.isPageTitleCorrect());
	}	

	@Test
	@Order(2)
	void openQuickLinks() {
		QuickLinks ql = new QuickLinks(driver);
		ql.clickQuickLinks();
	}
	
	@Test
	@Order(3)
	void clickEmployeeCreation() {
		// Does not work. But leave for know as it is an example of a failing test.
		NavBarElement empCr = hp.getTopRightNavBar().getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
		empCr.clickElement();
		assertEquals(NavBarEmployeeCreation.ORIGINAL_NAME, empCr.getOriginalName());
	}
	
	@Test
	@Order(4)
	void clickEmployeeCV() {
		NavBarElement empCV = hp.getTopRightNavBar().getNavBarElement(NavBarEmployeeCVPayroll.ORIGINAL_NAME).get();
		empCV.clickElement();
		assertEquals(NavBarEmployeeCVPayroll.ORIGINAL_NAME, empCV.getOriginalName());
	}
	
	@Test
	@Order(5)
	void clickEmployeeGridView() {
		NavBarElement empGrid = hp.getTopRightNavBar().getNavBarElement(NavBarEmpGridView.ORIGINAL_NAME).get();
		empGrid.clickElement();
		assertEquals(NavBarEmpGridView.ORIGINAL_NAME, empGrid.getOriginalName());
	}
	
	@Test
	@Order(6)
	void clickVisualReports() {
		NavBarElement rep = hp.getTopRightNavBar().getNavBarElement(NavBarVisualReports.ORIGINAL_NAME).get();
		rep.clickElement();
		assertEquals(NavBarVisualReports.ORIGINAL_NAME, rep.getOriginalName());
	}
	
	@Test
	@Order(7)
	void clickDakarIntelligence() {
		NavBarElement dak = hp.getTopRightNavBar().getNavBarElement(NavBarDakarIntelligence.ORIGINAL_NAME).get();
		dak.clickElement();
		assertEquals(NavBarDakarIntelligence.ORIGINAL_NAME, dak.getOriginalName());
	}
	
	@Test
	@Order(8)
	void clickCompanyLastViewed() {
		NavBarElement myCo = hp.getTopRightNavBar().getNavBarElement(NavBarMyCoLastViewed.ORIGINAL_NAME).get();
		myCo.clickElement();
		assertEquals(NavBarMyCoLastViewed.ORIGINAL_NAME, myCo.getOriginalName());
	}
	
	@Test
	@Order(9)
	void clickNotifications() {
		NavBarElement not = hp.getTopRightNavBar().getNavBarElement(NavBarNotifications.ORIGINAL_NAME).get();
		not.clickElement();
		assertEquals(NavBarNotifications.ORIGINAL_NAME, not.getOriginalName());
	}

	@Test
	@Order(10)
	void clickNewEmployments() {
		NavBarElement newEmps = hp.getTopRightNavBar().getNavBarElement(NavBarNewEmployments.ORIGINAL_NAME).get();
		newEmps.clickElement();
		assertEquals(NavBarNewEmployments.ORIGINAL_NAME, newEmps.getOriginalName());
	}

	@Test
	@Order(11)
	void clickTerminations() {
		NavBarElement term = hp.getTopRightNavBar().getNavBarElement(NavBarTerminations.ORIGINAL_NAME).get();
		term.clickElement();
		assertEquals(NavBarTerminations.ORIGINAL_NAME, term.getOriginalName());
	}
	
	@Test
	@Order(12)
	void clickUserManagement() {
		NavBarElement user = hp.getTopRightNavBar().getNavBarElement(NavBarUserManagment.ORIGINAL_NAME).get();
		user.clickElement();
		assertEquals(NavBarUserManagment.ORIGINAL_NAME, user.getOriginalName());
	}
}
