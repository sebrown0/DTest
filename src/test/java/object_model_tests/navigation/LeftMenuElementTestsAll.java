package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.swing.MenuElement;

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
import object_models.navigation.left_side_menu.LeftMenuPayroll.MenuItem;
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
	
//	private List<String> failedNames = new ArrayList<>();
	private String failedNames = "";
	
	int jj = 0;
	
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
//	void ffff() {
//		Map<String, List<MenuItem>> expected = LeftMenuPayroll.getAll();
//		List<String> actual = LeftMenu.getActualListOfNames(driver);
//		act
//		expected.entrySet().forEach((set) -> {
//			String key = set.getKey();
//			System.out.println("Looking for Parent = " + key);
//			
//		});
//	}
	
//	@Test
//	void ffff() {
//		LeftMenuPayroll.getAll().forEach(item -> {
//			System.out.println("Parent = " + item.getParentName());
//			item.getChildNames()
//					.ifPresent(
//							names -> {
//								names.forEach(name ->	System.out.println("  Child = " + name)
//								);
//							}
//					);
//		});
//	}
	

				
//	@Test
//	void checkReports() {
//		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
//		List<String> elementNames = elements.getReports();
//		checkList(elementNames);
//	}
//
//	@Test
//	void checkAll() {
//		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
//		List<List<String>> all = elements.getAll();
//		for (List<String> list : all) {
//			checkList(list);
//		}
//	}
		
	void checkList(List<String> elementNames) {
		int nameCount = 0;
		for (String name : elementNames) {
			loadAndCheckTitle(name);
			nameCount++;
		}
		assertTrue(nameCount == elementNames.size());
		writeFailedList(nameCount, elementNames.size());
	}
		
	@AfterAll
	static void tearDown() {
		driver.quit();
	}
	
	/* 
	 * Helpers below
	 */
	private void loadAndCheckTitle(String menuTitle) {		
		Optional<ContainerAction> obj = menu.load(menuTitle);		
		obj.ifPresentOrElse(o -> {
			PageTitle title = o.getTitle();			
			if(!title.getExpected().equals(title.getActual())) {
				addNameToFailList(menuTitle);
			}
			o.closeElement();
		}, 
			addNameToFailList(menuTitle)							
		);		
	}		
	
	private Runnable addNameToFailList(String menuTitle) {		
		return new Runnable() {			
			@Override
			public void run() {
				if(failedNames.length() > 0 ) {
					failedNames += ":" + menuTitle ;	
				}else {
					failedNames += menuTitle;
				}				
//				failedNames.add(menuTitle);				
			}
		};
	}
	
	private void writeFailedList(int nameCount, int listSize) {
		if(failedNames.length() > 0 ) {
			fail("The elements [" + failedNames + "] failed");
		}else if (nameCount != listSize){
			fail("The elements [" + failedNames + "] failed");
		}
	}
	
}
