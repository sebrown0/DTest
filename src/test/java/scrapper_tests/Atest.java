package scrapper_tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.modules.PayrollModuleLoader;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import scrapper.TestScrapper;
import xml_reader.ConfigReader;

class Atest {

	@SuppressWarnings("unused")
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	
	@BeforeAll	
	static void setup() throws NullDriverException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@AfterAll
	static void teardown() {
		driver.quit();
	}
		
	@Test
	void aTest() {
		TestScrapper.getListOfElements(driver, "nav-link");
	}
}
