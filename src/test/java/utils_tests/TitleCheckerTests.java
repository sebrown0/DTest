package utils_tests;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.helpers.TitleChecker;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenuPayroll.MenuItem;
import object_models.pages.UserLoginPage;
import object_models.panels.menu.employees.Banks;
import object_models.panels.menu.employees.CareerProgression;
import object_models.panels.menu.employees.ContactNumbers;
import object_models.panels.menu.employees.EmployeeDetails;
import object_models.panels.menu.employees.PermanentAllowances;
import object_models.panels.menu.employees.PreviousEmployement;
import object_models.panels.menu.employees.SalaryDetails;
import object_models.panels.menu.employees.Schedule;
import object_models.panels.menu.employees.Unions;
import object_models.panels.menu.parents.Documents;
import object_models.panels.menu.parents.EmployeeList;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

class TitleCheckerTests {
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
//	private static LeftMenu menu;
	
	private static List<String> actual = Arrays.asList(
			"Employee List", 
			"Documents", 
			"Banks", 
			"Employees", 
			"Additional Item",
			"Employee Details",
			"Contact Numbers",
			"Salary Details",
			"Schedule",
			"Unions",
			"Permanent Allowances",
			"Previous Employment");
	
	public static Map<String, MenuItem> expected = 				
		Stream.of(new Object[][] {
				{EmployeeList.MENU_TITLE, new MenuItem(EmployeeList.MENU_TITLE, Optional.empty())},
				{Documents.MENU_TITLE, new MenuItem(Documents.MENU_TITLE, Optional.empty())},
				{"Employees", new MenuItem("Employees", Optional.of(Arrays.asList(
						EmployeeDetails.MENU_TITLE,
						ContactNumbers.MENU_TITLE,
						Banks.MENU_TITLE,
						SalaryDetails.MENU_TITLE,
						CareerProgression.MENU_TITLE,
						Schedule.MENU_TITLE,
						PermanentAllowances.MENU_TITLE,
						PreviousEmployement.MENU_TITLE,
						Unions.MENU_TITLE
				)))}
		}).collect(Collectors.toMap(d -> (String) d[0], d -> ((MenuItem) d[1])));		
	
	@BeforeAll	
	static void setup() throws NullDriverException, InterruptedException, ExecutionException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Login.
		userLogin.loginValidUser(UserProvider.userPortal());
	}	
	
//	@Test
//	void test() {
//		TitleChecker checker = new TitleChecker(expected, actual);
//		checker.mapMissing().isCorrect();
//		checker.report();
//	}
	
	@Test
	void menuMapper() {
		LeftMenuMapper mapper = new LeftMenuMapper(driver);
		mapper.map();
		mapper.print();
	}

	@AfterAll
	static void tearDown() {
		driver.quit();
	}	
}
