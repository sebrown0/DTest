/**
 * 
 */
package object_model_tests.homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import factories.DriverFactory;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

/**
 * @author Steve Brown
 *
 */
class HomepageElementsTest {
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	
	@BeforeAll	
	static void setUpBeforeClass() throws Exception {				
		// Get a web driver as specified in the config.xml		
		driver = DriverFactory.getDriver(new ConfigReader());
		// Get a login page
		userLogin = new UserLoginPage(driver);
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@Test
	void homepageLoadedOk() {
		// Check that the home page has the correct title.
		assertTrue(hp.isPageTitleCorrect());
	}

	@Test
	void topRightNavBarElementsOk() {
		// Check that the nav bar's elements are correct.
		assertTrue(hp.getTopRightNavBar().isAllElementsCorrect());
	}
}
