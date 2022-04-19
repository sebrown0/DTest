/**
 * 
 */
package object_model_tests.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import library.helpers.login.UserLoginPage;
import library.nav.quick_links.QuickLinksPayroll;
import library.pages.homepage.HomePage;
import library.providers.ModuleNames;
import library.top_right_nav_bar.ElementChecker;
import library.top_right_nav_bar.TopRightNavBar;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Test the elements of the home page for payroll.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class PayrollHomepageElementsTest {
	private static HomePage homepagePayroll;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		homepagePayroll = userLoginPayroll.loginValidUser(UserProvider.userPortal());
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		homepagePayroll.close();
	}
	
	@Test
	void checkModuleName() {
		assertTrue(ModuleNames.isValidName(homepagePayroll.getActualModuleName()));
		assertTrue(homepagePayroll.getActualModuleName().equals(ModuleNames.PAYROLL_NAME));
	}
	
	@Test
	void topRightNavBarElementsOk() {
		TopRightNavBar topRightNavBar = homepagePayroll.getTopRightNavBar();
		ElementChecker elementChecker = topRightNavBar;
		assertTrue(elementChecker.checkElementTitles());
	}

	@Test
	void loadPersonnel() {
		QuickLinksPayroll links = (QuickLinksPayroll) homepagePayroll.getTopRightNavBar().getQuickLinks();
		links.getPersonnel().clickMe();
	}
}
