package object_model_tests.payroll;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import entities.company.Company;
import entities.payroll.CurrentPayPeriod;
import entities.payroll.PayGroup;
import entities.payroll.PayPeriod;
import logging.TestResultLogger;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePagePayroll;
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
 * Test the Initialise Payroll form.
 * Does not test the initialisation of payroll.
 * For this see InitialisePayroll_Tests
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class, 
	TestResultLogger.class })
public final class InitialisePayroll_Tests {
	private static HomePagePayroll hp;
		
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		hp = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@Test	@Order(1)
	void checkInitPay_loaded() {
//		hp.initialisePayroll(null, null);
	}
	
	private Company getCompany() {
		PayPeriod pp = new CurrentPayPeriod(2, LocalDate.of(2021, Month.JANUARY, 1), LocalDate.of(2021, Month.FEBRUARY, 25));
		PayGroup pg = new PayGroup("Fourweekly", pp);
		Company c = new Company("Mars Northern Products Ltd");
		c.addPayGroup(pg);
		
		return c;
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		hp.close();
	}

}
