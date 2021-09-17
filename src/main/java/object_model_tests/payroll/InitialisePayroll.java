package object_model_tests.payroll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.left_side_menu.LeftMenuActions;
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class, 
	TestResultLogger.class })
public final class InitialisePayroll {
	private static WebDriver driver;
	private static LeftMenuActions leftMenu;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		driver = hp.getWebDriver();
		leftMenu = hp.getLeftMenu();
	}

	@Test
	void test() {
		leftMenu.clickParent("Payroll");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
