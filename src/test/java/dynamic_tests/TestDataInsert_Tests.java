/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import controls.data_inserters.TestDataInserter;
import controls.data_inserters.emp_lookup.EmployeeLookupByName;
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
 * 	Initial
 * @since 1.0
 * 
 * Test the different ways of inserting test data.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class TestDataInsert_Tests {

	private static SalaryDetails salDetails;
	private static HomePage hp;

	@BeforeAll
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
		LeftMenu menu = hp.getLeftMenu();
		salDetails = 
			(SalaryDetails)	menu
				.clickParent("Employees")
				.clickAndLoad(SalaryDetails.class)
				.get();
		
	}
	
	@AfterAll
	public static void teardown() {
		hp.close();
	}
	
	@Test
	void test_empLookup() {
		/*
		 * Have to pass the employee code as well so that we can get results
		 * by name and then filter by code, thus getting the correct employee.
		 */
		TestDataInserter emplLookup = new EmployeeLookupByName(salDetails, "Borg");
		emplLookup.insertData();
		
		WebElement res = hp.getWebDriver().findElement(By.cssSelector("input[name='XNAME']"));
		assertTrue(res.getAttribute("value").contains("Borg"));
	}
	
}
