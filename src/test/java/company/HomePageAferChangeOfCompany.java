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
 * @param
 * 	user: SteveB (from UserProvider).
 *	config: from  ConfigParameterResolver.
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
	private static HomePage currentHomepage;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		currentHomepage = userLoginPayroll.loginValidUser(UserProvider.userSteveB());
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		homepagePayroll.close();
	}
		
	/*
	 * TODO - ADD COMPANY NAME TO CONFIG READER.
	 */
	
	@Test @Order(1)
	void getNewHomepage_afterLoading_newCompany() {
		// Original company is provided by LoginPageResolverPayroll.
		HomePage newHomepage = currentHomepage.loadCompany(new Company("Mars Northern Products Ltd"));		
		assertFalse(currentHomepage.equals(newHomepage));
		currentHomepage = newHomepage;
	}
	
	@Test @Order(2)
	void loadMenuItem_after_another_companyLoad() {
		HomePage newHomepageOne = currentHomepage.loadCompany(new Company("Mars Northern Products Ltd"));
		HomePage newHomepageTwo = newHomepageOne.loadCompany(new Company("Mars Incorporated Ltd"));
		LeftMenu menu = newHomepageTwo.getLeftMenu();
		menu.clickAndLoad(InitialisePayroll.class);
		assertFalse(newHomepageTwo == newHomepageOne);
		currentHomepage = newHomepageTwo;
	}
	
	@Test @Order(3)
	void loadTheSameCompanyTwice_checkTheyAreEqual_loadMenuItem() {
		/*
		 * in modal closer: current container is PayrollModuleElements -> has to contexts -> payroll
		 * Where is the 
		 */
		HomePage newHomepageOne = currentHomepage.loadCompany(new Company("Mars Northern Products Ltd"));
		LeftMenu menu = newHomepageOne.getLeftMenu();
		menu.clickAndLoad(InitialisePayroll.class);
		//have not got a new CM. There are 3 context payroll
		HomePage newHomepageTwo = newHomepageOne.loadCompany(new Company("Mars Incorporated Ltd"));
		
		
		assertTrue(newHomepageTwo != newHomepageOne);
		currentHomepage = newHomepageTwo;
	}
	
//	@Test 
//	void klljljlk() {
////		UserLoginPage p = new UserLoginPage(null, new PayrollModuleElements(null));
//	}
}
