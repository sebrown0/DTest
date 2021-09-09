package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import listeners.TestResultLogger;
import object_models.forms.ContainerAction;
import object_models.helpers.title.PageTitle;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

@ExtendWith(TestResultLogger.class)
class LeftMenuElementTestsAll {	
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	private static LeftMenu menu;
	
	@BeforeAll	
	static void setup() throws NullDriverException, InterruptedException, ExecutionException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Login.
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the menu from home page.
		menu = hp.getLeftMenu();
	}
			
//	@Test
//	void checkEmployees() {
//		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
//		List<String> elementNames = elements.getEmployees();
//		checkList(elementNames);
//	}
	
	@Test
	void checkReports() {
		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
		List<String> elementNames = elements.getReports();
		checkList(elementNames);
	}

	@Test
	void checkAll() {
		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
		List<List<String>> all = elements.getAll();
		for (List<String> list : all) {
			checkList(list);
		}
	}
		
	void checkList(List<String> elementNames) {
		int nameCount = 0;
		for (String name : elementNames) {
			loadAndCheckTitle(name);
//			System.out.println(name);
			nameCount++;
		}
		assertTrue(nameCount == elementNames.size());
	}
		
	@AfterAll
	static void tearDown() {
//		driver.quit();
	}
	
	/* 
	 * Helpers below
	 */
	private void loadAndCheckTitle(String menuTitle) {
		ContainerAction obj = menu.load(menuTitle);		
		if(obj != null) {
			PageTitle title = obj.getTitle();
			assertEquals(title.getExpected(), title.getActual());
			obj.closeElement();
		}else {
			System.out.println(menuTitle + " failed test.");
			fail(menuTitle + " failed test.");
		}		
	}		
}
