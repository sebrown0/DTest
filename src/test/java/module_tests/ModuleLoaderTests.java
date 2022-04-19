package module_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import library.dakar_hr.entities.company.Company;
import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.object_models.modules.payroll.PayrollModuleElements;
import library.dakar_hr.pages.homepage.HomePage;
import providers.XMLFileProvider;
import resources.test_data.ZZZ_UserProvider;
import xml_reader.config_file.ConfigReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModuleLoaderTests {
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;
	private static String uri;
	
	@BeforeAll	
	static void setUpBeforeClass() throws Exception {		
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get the login URI.
		uri = configReader.getUri();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@Test @Order(1)
	void loadHomePage_withPayrollModule() {
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, uri, new PayrollModuleElements(new Company("Mars Incorporated Ltd")));
		// Get a home page after successful login
		hp = userLogin.loginValidUser(ZZZ_UserProvider.userPortal());		
		assertTrue(hp != null);
	}
	
//	@Test @Order(2)
//	void switchToPersonnel() {
//		HomePagePersonnel personnel = (HomePagePersonnel) hp.loadModule(new PersonnelModuleElements(new Company("Mars Incorporated Ltd")));
//		assertTrue(personnel != null);
//	}
	
}
