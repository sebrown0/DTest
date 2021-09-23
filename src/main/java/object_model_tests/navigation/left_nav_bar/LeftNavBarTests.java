/**
 * 
 */
package object_model_tests.navigation.left_nav_bar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import entities.Company;
import logging.TestResultLogger;
import object_models.left_nav_bar.LeftNavBar;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.CompanyProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 *
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class LeftNavBarTests {
	private static HomePage homepagePayroll; 
	private static LeftNavBar leftNavBar;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		leftNavBar = homepagePayroll.getLeftNavBar();
	}		
	
	@Test
	void switchToMarsNorthProd() {
		Company marsNorthProd = leftNavBar.loadCompany(CompanyProvider.marsNorthernProductsLtd().getName());		
		assertEquals(CompanyProvider.marsNorthernProductsLtd().getName(), marsNorthProd.getName());
	}
	
	@Test
	void switchToMarsInc() {
		Company marsInc = leftNavBar.loadCompany(CompanyProvider.marsIncorporatedProductsLtd().getName());		
		assertEquals(CompanyProvider.marsIncorporatedProductsLtd().getName(), marsInc.getName());
	}
	
	@AfterAll
	public static void tearDown() {			
		homepagePayroll.close();
	}

}
