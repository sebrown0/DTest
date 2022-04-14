package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import helpers.MenuChecker;
import library.dakar_hr.left_menu.LeftMenu;
import logging.TestResultLogger;
import object_models.modules.payroll.left_menu.LeftMenuPayroll;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0 
 * 
 * Check the left menu in the context of Payroll.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class LeftMenuVailidation_Payroll_Tests {	
	private static HomePage homepagePayroll;
	private static LeftMenuPayroll menuPayroll;
	private static LeftMenu leftMenu;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		leftMenu = homepagePayroll.getLeftMenu();
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
		homepagePayroll.close();
	}
	
}
