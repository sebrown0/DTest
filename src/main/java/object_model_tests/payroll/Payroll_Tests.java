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
 * Test payroll functionality from initialisation, 
 * through calculation until closed and locked.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class,
	TestResultLogger.class })
public final class Payroll_Tests {
	private static HomePagePayroll hp;
	private static PayGroup pg;	
	private static Company comp;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {		
		hp = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());
		
		PayPeriod pp = new CurrentPayPeriod(2, LocalDate.of(2021, Month.JANUARY, 29), LocalDate.of(2021, Month.FEBRUARY, 25));
		pg = new PayGroup("Fourweekly", pp);
		
		comp = new Company("Mars Northern Products Ltd");
		comp.addPayGroup(pg);
	}
		
	@Test	@Order(1)
	void initialisePay() {
		hp = hp.initialisePayroll(comp, pg);
	}
	
	@Test	@Order(2)
	void globalAdjustments() {
		hp.openGlobalAdjustments();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		hp.close();
	}

}
