package object_model_tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import factories.DriverFactory;
import listeners.TestResultLogger;
import object_models.helpers.User;
import object_models.pages.HomePage;
import object_models.pages.LoadablePage;
import object_models.pages.UserLoginPage;
import xml_reader.ConfigReader;

@ExtendWith(TestResultLogger.class)
class UserLoginTest {
	private static WebDriver driver;
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Get a web driver as specified in the config.xml		
		driver = DriverFactory.getDriver(new ConfigReader());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@Test
	@Tag("R20")
	@Tag("T3833")
	void createUserLoginModel() {
		// Check that a user login page is created.
		LoadablePage userLogin = new UserLoginPage(driver);		
		assertTrue(userLogin.isPageTitleCorrect());		
	}

	@ParameterizedTest
	@MethodSource("resources.test_data.UserProvider#validPortalUser")
	@Tag("R20")
	@Tag("T3834")
	void validUserLogin(User user) {		
		// Supply valid user, login and check home page is loaded.
		UserLoginPage userLogin = new UserLoginPage(driver);		
		HomePage hp = userLogin.loginValidUser(user);		
		assertTrue(hp.isPageTitleCorrect());
	}
		
	@ParameterizedTest
	@MethodSource("resources.test_data.UserProvider#invalidUser")
	@Tag("R20")
	@Tag("T3835")
	void invalidUserLogin(User user) {
		// Supply invalid user, login (fail) and check home page is NOT loaded.
		UserLoginPage userLogin = new UserLoginPage(driver);		
		HomePage hp = userLogin.loginValidUser(user);		
		assertTrue(!hp.isPageTitleCorrect());
	}
		
}
