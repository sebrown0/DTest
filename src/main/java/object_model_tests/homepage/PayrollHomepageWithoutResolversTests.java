/**
 * 
 */
package object_model_tests.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import factories.DriverFactory;
import library.dto.entites.company.Company;
import library.dto.entites.user.User;
import library.helpers.login.UserLoginPage;
import library.object_models.modules.common.nav.quick_links.QuickLinksPayroll;
import library.object_models.modules.payroll.PayrollModuleElements;
import library.pages.homepage.HomePage;
import library.providers.ModuleNames;
import library.top_right_nav_bar.ElementChecker;
import library.top_right_nav_bar.TopRightNavBar;

/**
 * @author Steve Brown
 *
 * Test the elements of the home page for payroll.
 */
class PayrollHomepageWithoutResolversTests {
	private static HomePage homepagePayroll;
	
	@BeforeAll	
	public static void setup() {
		Company co = new Company("A Comp");
		UserLoginPage userLoginPayroll = 
			new UserLoginPage(
					DriverFactory.getDriver(""), "http://deploy.dakarhr.com/DakarHR.php", new PayrollModuleElements(co));
		homepagePayroll = userLoginPayroll.loginValidUser(new User("portal2", "123"));
		System.out.println("->"); // TODO - remove or log 	
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
