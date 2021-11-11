package object_model_tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import entities.User;
import logging.TestResultLogger;
import object_models.modules.payroll.PayrollModuleLoader;
import object_models.pages.HomePage;
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
	
	@ParameterizedTest
	@MethodSource("test_data.UserProvider#validPortalUser")
	@Tag("R20")
	@Tag("T3834")	
	void validUserLogin(User user) {		
		// Supply valid user, login and check home page is loaded.
		UserLoginPage userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));		
		HomePage hp = userLogin.loginValidUser(user); 
		assertTrue(hp != null);
	}
		
	@ParameterizedTest
	@MethodSource("test_data.UserProvider#invalidUser")
	@Tag("R20")
	@Tag("T3835")
	void invalidUserLogin(User user) {
		// Supply invalid user, login (fail) and home page is NOT loaded.
		UserLoginPage userLogin = new UserLoginPage(driver);
		HomePage hp = userLogin.loginValidUser(user); 
		assertTrue(hp == null);
	}
}
