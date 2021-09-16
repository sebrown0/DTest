package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import app.test_runner.ConfigParameterResolver;
import exceptions.NullDriverException;
import logging.TestResultLogger;
import object_models.helpers.MenuChecker;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith(ConfigParameterResolver.class)
@ExtendWith(TestResultLogger.class)
public class LeftMenuVailidation_Payroll_Tests {	
	private static WebDriver driver;
	private static UserLoginPage userLogin;	
	private static LeftMenuPayroll menuPayroll;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader) throws NullDriverException, InterruptedException, ExecutionException {
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Login.
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the payroll elements.
		menuPayroll = (LeftMenuPayroll) hp.getLeftMenu().getElements();
	}		

	@Test
	public void checkForMissing() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), LeftMenu.getActualMenu(driver));
		checker.checkMenu();
		int missing = checker.getMissingItems().size();
		if(missing > 0) {
			fail("Menu has [" + missing + "] missing elements -> " + checker.getMissingItems().toString());
		}
	}
	
	@Test	
	public void checkForNew() {		
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), LeftMenu.getActualMenu(driver));
		checker.checkMenu();
		int nu = checker.getNewMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] new elements -> " + checker.getNewMenuItems().toString());
		}
	}
	
	@Test
	public void checkForAdditional() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), LeftMenu.getActualMenu(driver));
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
//private ConfigReader configReader;

//public LeftMenuVailidation_Payroll_Tests(ConfigReader configReader)	throws NullDriverException, InterruptedException, ExecutionException {
//	this.configReader = configReader;
//	// Get a web driver as specified in the config.xml		
//	driver = configReader.getDriver();
//	// Get a login page, with the required module loaded.
//	userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
//	// Login.
//	HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
//	// Get the payroll elements.
//	menuPayroll = (LeftMenuPayroll) hp.getLeftMenu().getElements();		
//}