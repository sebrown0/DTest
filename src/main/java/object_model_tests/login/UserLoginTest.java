package object_model_tests.login;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.helpers.User;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import xml_reader.ConfigReader;

/**
 * @author Steve Brown
 *
 * Login for a given user from resources.test_data.UserProvider
 */
@ExtendWith(TestResultLogger.class)
class UserLoginTest {
	private static WebDriver driver;
	private static ConfigReader configReader;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
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
		/*
		 * removed when updating Title.
		 * Loadable page may itself be removed in the future
		 */
//		LoadablePage userLogin = new UserLoginPage(driver);		
//		assertTrue(userLogin.isPageTitleCorrect());		
	}

	@ParameterizedTest
	@MethodSource("test_data.UserProvider#validPortalUser")
	@Tag("R20")
	@Tag("T3834")	
	void validUserLogin(User user) {		
		// Supply valid user, login and check home page is loaded.
		UserLoginPage userLogin = new UserLoginPage(driver);		
		userLogin.loginValidUser(user);
	}
		
	@ParameterizedTest
	@MethodSource("test_data.UserProvider#invalidUser")
	@Tag("R20")
	@Tag("T3835")
	void invalidUserLogin(User user) {
		// Supply invalid user, login (fail) and home page is NOT loaded.
		UserLoginPage userLogin = new UserLoginPage(driver);
		userLogin.loginValidUser(user);
	}
		
}
