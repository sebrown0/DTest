/**
 * 
 */
package object_model_tests.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import drivers.DriverGetter;
import drivers.GoogleDriver;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import resources.test_data.UserProvider;

/**
 * @author SteveBrown
 *
 */
class HomepageElementsTest {
	private static HomePage hp;
	private static WebDriver driver;
	private static DriverGetter dg = new GoogleDriver();
	private static UserLoginPage userLogin;
	
	@BeforeAll	
	static void setUpBeforeClass() throws Exception {				
		driver = dg.getDriver();
		userLogin = new UserLoginPage(driver);
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@Test
	void homepageLoadedOk() {
		assertTrue(hp.isPageTitleCorrect());
	}

	@Test
	void topRightNavBarElementsOk() {
		assertTrue(hp.getTopRightNavBar().isAllElementsCorrect());
	}
}
