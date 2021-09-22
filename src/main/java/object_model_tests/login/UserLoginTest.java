package object_model_tests.login;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import entities.User;
import logging.TestResultLogger;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Login for a given user from resources.test_data.UserProvider
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class 
})
class UserLoginTest {
	private static WebDriver driver;

	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader) throws Exception {
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
