package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.helpers.MenuChecker;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll_only.LeftMenuPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class LeftMenuVailidation_Payroll_Tests {	
	private static WebDriver driver;
	private static LeftMenuPayroll menuPayroll;
	private static LeftMenu leftMenu;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		driver = hp.getWebDriver();
		leftMenu = hp.getLeftMenu();
		menuPayroll = (LeftMenuPayroll) leftMenu.getElements(); 
	}		

	@Test
	public void checkForMissing() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), leftMenu.getActualMenu());
		checker.checkMenu();
		int missing = checker.getMissingItems().size();
		if(missing > 0) {
			fail("Menu has [" + missing + "] missing elements -> " + checker.getMissingItems().toString());
		}
	}
	
	@Test	
	public void checkForNew() {		
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), leftMenu.getActualMenu());
		checker.checkMenu();
		int nu = checker.getNewMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] new elements -> " + checker.getNewMenuItems().toString());
		}
	}
	
	@Test
	public void checkForAdditional() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), leftMenu.getActualMenu());
		checker.checkMenu();
		int nu = checker.getAdditonalMenuAndSubMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] additional elements -> " + checker.getAdditonalMenuAndSubMenuItems().toString());
		}
	}
	
	@AfterAll
	public static void tearDown() {			
		driver.quit();
	}
	
}
