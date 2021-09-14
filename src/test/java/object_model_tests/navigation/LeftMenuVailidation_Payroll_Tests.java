package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import listeners.TestResultLogger;
import object_models.helpers.MenuChecker;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

@ExtendWith(TestResultLogger.class)
class LeftMenuVailidation_Payroll_Tests {	
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;
	private static LeftMenuPayroll menuPayroll;

	@BeforeAll	
	static void setup() throws NullDriverException, InterruptedException, ExecutionException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
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
	void checkForMissing() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), LeftMenu.getActualMenu(driver));
		checker.checkMenu();
		int missing = checker.getMissingItems().size();
		if(missing > 0) {
			fail("Menu has [" + missing + "] missing elements -> " + checker.getMissingItems().toString());
		}
	}
	
	@Test
	void checkForNew() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), LeftMenu.getActualMenu(driver));
		checker.checkMenu();
		int nu = checker.getNewMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] new elements -> " + checker.getNewMenuItems().toString());
		}
	}
	
	@Test
	void checkForAdditional() {
		MenuChecker checker = new MenuChecker(menuPayroll.getAll(), LeftMenu.getActualMenu(driver));
		checker.checkMenu();
		int nu = checker.getAdditonalMenuAndSubMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] additional elements -> " + checker.getAdditonalMenuAndSubMenuItems().toString());
		}
	}
	
	@AfterAll
	static void tearDown() {
		driver.quit();
	}	
	
}
