/**
 * 
 */
package ui_tests;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.pages.homepage.HomePage;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.ZZZ_UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class,
	TestResultLogger.class })
class AllButtonsTests {
	private static HomePage hp;
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {		
		hp = userLogin.loginValidUser(ZZZ_UserProvider.userPortal());		
	}
	
	@Test
	void test() {
		List<WebElement> btns = hp.getWebDriver().findElements(By.cssSelector("a[class='btn']"));
		btns.forEach(b -> {
			System.out.println("->" + b.getText()); // TODO - remove or log 	
		});
	}

}
