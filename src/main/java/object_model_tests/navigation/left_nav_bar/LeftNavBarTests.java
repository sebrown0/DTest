/**
 * 
 */
package object_model_tests.navigation.left_nav_bar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.pages.homepage.HomePagePayroll;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class LeftNavBarTests {
	private static HomePagePayroll homepagePayroll; 
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());
	}		
	
	/*
	 * NONE
	 */
	
	@AfterAll
	public static void tearDown() {			
		homepagePayroll.close();
	}

}
