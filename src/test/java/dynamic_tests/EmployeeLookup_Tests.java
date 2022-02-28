/**
 * 
 */
package dynamic_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.data_inserters.emp_lookup.EmployeeLookup;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.modules.payroll.left_menu.employees.SalaryDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
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
class EmployeeLookup_Tests {

	private static SalaryDetails salDetails;

	@BeforeAll
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		HomePage homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		LeftMenu menu = homepagePayroll.getLeftMenu();
		salDetails = 
			(SalaryDetails)	menu
				.clickParent("Employees")
				.clickAndLoad(SalaryDetails.class)
				.get();

	}
	
	@Test
	void test() {
		EmployeeLookup emplLookup = new EmployeeLookup(salDetails);
		emplLookup.loadEmployee("XXX");
	}

}
