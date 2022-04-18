package object_model_tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import library.dakar_hr.entities.company.Company;
import library.dakar_hr.entities.user.User;
import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.object_models.modules.payroll.PayrollModuleElements;
import library.dakar_hr.pages.homepage.HomePage;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.1
 * @since 1.0
 *
 * Login for a given user from resources.test_data.UserProvider
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class 
})
class UserLoginTest { // <- TEST SUITE
	private static WebDriver driver;
	private static String uri;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader) throws Exception {
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver(); // <- APP CONFIG
		uri = configReader.getUri();
	}
	
	@ParameterizedTest
//	/DTest/src/main/resources/test_data/UserProvider.java
	@MethodSource("DTest.src.main.resources.test_data.UserProvider#validPortalUser") // <- TEST DATA
	@Tag("R20")
	@Tag("T3834")	
	void validUserLogin(User user) {		
		// Supply valid user, login and check home page is loaded.
		UserLoginPage userLogin = 
				new UserLoginPage(
						driver, 
						uri, 
						new PayrollModuleElements(new Company("Mars Incorporated Ltd"))); // <- POM		
		HomePage hp = userLogin.loginValidUser(user); // <- FLUENT API
		assertTrue(hp != null); // <- TEST VERIFICATION & TEST LOGGING
	}
		
	@ParameterizedTest
	@MethodSource("test_data.UserProvider#invalidUser")
	@Tag("R20")
	@Tag("T3835")
	void invalidUserLogin(User user) {
		// Supply invalid user, login (fail) and home page is NOT loaded.
		UserLoginPage userLogin = new UserLoginPage(driver, "http://deploy.dakarhr.com/DakarHR.php");
		HomePage hp = userLogin.loginValidUser(user); 
		assertTrue(hp == null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
}
