package object_model_tests.modules;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import object_models.modules.payroll.PayrollModuleElements;
import object_models.modules.personnel.PersonnelModuleLoader;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import object_models.pages.homepage.HomePagePersonnel;
import providers.XMLFileProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ModuleLoaderTests {
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;
	
	@BeforeAll	
	static void setUpBeforeClass() throws Exception {		
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@Test @Order(1)
	void loadHomePage_withPayrollModule() {
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleElements());
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
		assertTrue(hp != null);
	}
	
	@Test @Order(2)
	void switchToPersonnel() {
		HomePagePersonnel personnel = (HomePagePersonnel) hp.loadModule(new PersonnelModuleLoader());
		assertTrue(personnel != null);
	}
	
}
