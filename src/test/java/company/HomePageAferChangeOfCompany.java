/**
 * 
 */
package company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
		currentHomepage.close();
	}
	
	@Test
	void getNewHomepage_afterLoading_newCompany() {
		// Original company is provided by LoginPageResolverPayroll.
		HomePage newHomepage = currentHomepage.loadCompany(new Company("Mars Northern Products Ltd"));		
		assertFalse(currentHomepage.equals(newHomepage));
		currentHomepage = newHomepage;
	}
	
	@Test
	void loadMenuItem_after_another_companyLoad() {
		HomePage newHomepageOne = currentHomepage.loadCompany(new Company("Mars Northern Products Ltd"));
		HomePage newHomepageTwo = newHomepageOne.loadCompany(new Company("Mars Incorporated Ltd"));
		assertFalse(newHomepageTwo == newHomepageOne);
		currentHomepage = newHomepageTwo;
	}
	
	@Test
	void loadTheSameCompanyTwice_checkTheyAreEqual_loadMenuItem() {		
		HomePage newHomepageOne = currentHomepage.loadCompany(new Company("Mars Incorporated Ltd"));
		LeftMenu menu = newHomepageOne.getLeftMenu();
		menu.clickAndLoad(InitialisePayroll.class);
		HomePage newHomepageTwo = newHomepageOne.loadCompany(new Company("Mars Incorporated Ltd"));
		assertTrue(newHomepageTwo.equals(newHomepageOne));
		currentHomepage = newHomepageTwo;
	}	

	@Test
	void loadInvalidNewModule() {		
		HomePage newHomepageOne = currentHomepage.loadModule("Giberish");		
		assertTrue(newHomepageOne.equals(currentHomepage));
		currentHomepage = newHomepageOne;
	}
	
	@Test
	void loadValidNewModule() {		
		HomePage newHomepageOne = currentHomepage.loadModule("Personnel");		
		assertEquals("Personnel", newHomepageOne.waitForAndGetModuleName("Personnel"));
		currentHomepage = newHomepageOne;
	}
}
