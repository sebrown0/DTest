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
		driver = DriverFactory.getDriver(new ConfigReader());
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
