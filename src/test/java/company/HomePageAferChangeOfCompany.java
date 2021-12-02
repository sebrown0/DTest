/**
 * 
 */
package company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import entities.company.Company;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll.initialise.InitialisePayroll;
import object_models.modules.payroll.PayrollModuleElements;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 * Check the home page is created correctly
 * after changing companies.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class HomePageAferChangeOfCompany {
	private static HomePage homepagePayroll;
	private static HomePage newHomepageOne;
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		homepagePayroll = userLoginPayroll.loginValidUser(UserProvider.userPortal());
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		homepagePayroll.close();
	}
		
	/*
	 * TODO - ADD COMPANY NAME TO CONFIG READER.
	 */
	
	@Test
	void getNewHomepage_afterLoading_newCompany() {
		// Original company is provided by LoginPageResolverPayroll.
		HomePage newHomepage = homepagePayroll.loadCompany(new Company("Mars Northern Products Ltd"));		
		assertFalse(homepagePayroll.equals(newHomepage));
	}
	
	@Test
	void loadMenuItem_after_another_companyLoad() {
		HomePage newHomepageOne = homepagePayroll.loadCompany(new Company("Mars Northern Products Ltd"));
		HomePage newHomepageTwo = newHomepageOne.loadCompany(new Company("Mars Incorporated Ltd"));
		LeftMenu menu = newHomepageTwo.getLeftMenu();
//		menu.clickAndLoad(InitialisePayroll.class);
		assertFalse(newHomepageTwo == newHomepageOne);
	}
	
	@Test
	void loadTheSameCompanyTwice_checkTheyAreEqual_loadMenuItem() {
		HomePage newHomepageOne = homepagePayroll.loadCompany(new Company("Mars Incorporated Ltd"));
		LeftMenu menu = newHomepageOne.getLeftMenu();
//		menu.clickAndLoad(InitialisePayroll.class);
		HomePage newHomepageTwo = newHomepageOne.loadCompany(new Company("Mars Incorporated Ltd"));
		
		
		assertTrue(newHomepageTwo == newHomepageOne);
	}
	
	@Test 
	void klljljlk() {
//		UserLoginPage p = new UserLoginPage(null, new PayrollModuleElements(null));
	}
}
