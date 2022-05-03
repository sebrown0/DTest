package object_model_tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import library.common.controls.combos.ComboWriteAndSelect;
import library.common.controls.interfaces.Control;
import library.dto.entites.company.Company;
import library.dto.entites.user.User;
import library.helpers.login.UserLoginPage;
import library.object_models.modules.common.nav.nav_bar_elements.NavBarElement;
import library.object_models.modules.payroll.PayrollModuleElements;
import library.object_models.modules.payroll.top_right_nav.authorisation.Authorisations;
import library.pages.homepage.HomePage;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import root.elements.ControlGroup;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.1
 * @since 1.0
 *
 * Login for a given user from resources.test_data.UserProvider
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class 
})
class UserLoginTest { // <- TEST SUITE
	private static WebDriver driver;
	private static String uri;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader) throws Exception {
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver(); // <- APP CONFIG
		uri = configReader.getUri();
	}
	
	@ParameterizedTest
	@MethodSource("resources.test_data.UserProvider#validPortalUser") // <- TEST DATA
	@Tag("R20")
	@Tag("T3834")	
	void validUserLogin(User user) {
		// Supply valid user, login and check home page is loaded.
		UserLoginPage userLogin = 
				new UserLoginPage(
						driver, 
						uri, 
						new PayrollModuleElements(new Company("Mars Incorporated Ltd"))); // <- POM		
		HomePage hp = userLogin.loginValidUser(user); // <- FLUENT API
		
		NavBarElement el = hp.getTopRightNavBarElement("Authorisations").get();
		Authorisations authorisations = (Authorisations) el.clickElement();
		WebDriver d = hp.getWebDriver();
		List<WebElement> els = d.findElements(By.xpath("//*[@class='col-md-2']"));

		WebElement e = d.findElement(By.xpath(".//*[@class='col-md-2' and starts-with(text(),'Company')]"));
		System.out.println("->" + e.getText()); // TODO - remove or log 	
		
		ControlGroup row1 = (ControlGroup) authorisations.getControl("Row1").get();
		ComboWriteAndSelect co = (ComboWriteAndSelect) row1.getControlByTitle("Company").get();
		//select2-COMP_SELx-container
		co.click();
		co.selectFullText("Mars Incorporated Ltd");
		assertTrue(hp != null); // <- TEST VERIFICATION & TEST LOGGING
	}
		
	@ParameterizedTest
	@MethodSource("resources.test_data.UserProvider#invalidUser")
	@Tag("R20")
	@Tag("T3835")
	void invalidUserLogin(User user) {
		// Supply invalid user, login (fail) and home page is NOT loaded.
		UserLoginPage userLogin = new UserLoginPage(driver, "http://deploy.dakarhr.com/DakarHR.php");
		HomePage hp = userLogin.loginValidUser(user); 
				
		assertTrue(hp == null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
}
