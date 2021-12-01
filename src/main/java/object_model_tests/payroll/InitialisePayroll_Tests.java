package object_model_tests.payroll;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.TextSelect;
import entities.Company;
import entities.PayGroup;
import entities.PayPeriod;
import enums.control_names.CommonControlNames;
import enums.control_names.PayrollControlNames;
import logging.TestResultLogger;
import object_models.dialog.DialogOkCancel;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll.initialise.InitialisePayroll;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePagePayroll;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0 
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class, 
	TestResultLogger.class })
public final class InitialisePayroll_Tests {
	private static HomePagePayroll hp;
	private static LeftMenu leftMenu;	
	private static InitialisePayroll initPay;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		hp = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());
		leftMenu = hp.getLeftMenu();
		// Load initialise pay from container.
		leftMenu
				.clickParent(InitialisePayroll.MENU_PARENT_NAME)
				.clickAndLoad(InitialisePayroll.class)
				.ifPresent(c -> initPay = (InitialisePayroll) c);
	}
	
	@Test	@Order(1)
	void checkInitPay_loaded() {
		if(initPay == null) {
			fail("Could not get InitialisePayroll object");
		}	
	}
	
	@Test	@Order(2)
	void checkCompany() {
		TextSelect comp = (TextSelect) initPay.getControl(CommonControlNames.COMPANY).get();
		assertTrue(comp.getText().length() > 0);
	}
	
	@Test	@Order(3)
	void checkPayGroup() {
		TextSelect payGrp = (TextSelect) initPay.getControl(PayrollControlNames.PAY_GROUP).get();
		assertTrue(payGrp.getText().length() > 0);
	}

	@Test	@Order(4)
	void checkPayPeriod() {
		TextSelect payPer = (TextSelect) initPay.getControl(PayrollControlNames.PAY_PERIODS).get();
		assertTrue(payPer.getText().length() > 0);
	}
	
	@Test	@Order(5)
	void initPayroll() {
		DialogOkCancel okCancel = initPay.clickInitialisePayroll();
		okCancel.getBtnCancel().ifPresent(b -> b.click());
	}
	
	@Test	@Order(6)
	void cooooooooooooooomp() {
		PayPeriod pp = new PayPeriod(10, true, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		PayGroup pg = new PayGroup("Monthly Paygroup", pp);
//		Company co = new Company("Mars Northern Products Ltd");
		Company co = new Company("Mars Incorporated Ltd");
		co.addPayGroup(pg);
		
		hp.initialisePayroll(co);		
	}
	
	@Test	@Order(15) //TODO - update test num!!!
	void closeForm() {
		initPay.closeFormAndContext();
	}
	
//	@Test
//	@Order()
//	void click_initialisePayroll_check_DialogAppears_and_clickCancel() {
//		DialogOkCancel okCancel = (DialogOkCancel) initPay.clickInitialisePayroll();
//		okCancel.getBtnCancel().get().click();
//		assertEquals("Are you sure you want to Initialise the Payroll ?", okCancel.getMsg().get());
//		assertEquals("Payroll Initialisation", okCancel.getTitle().get());
//		assertEquals("OK", okCancel.getBtnOk().get().getElementKey());
//		assertEquals("Cancel", okCancel.getBtnCancel().get().getElementKey());
//	}
	
//	@Test
//	@Order()
//	void click_initialisePayroll_then_clickOk() {
//		DialogOkCancel okCancel = (DialogOkCancel) initPay.clickInitialisePayroll();
//		okCancel.getBtnOk().get().click();
//		Optional<String> msg = initPay.getPayrollInitialisedMsg();
//	}
	
//	@Test
//	@Order()
//	void assert_that_payroll_is_already_initialised() {
//		assertEquals("This Payroll has already been Initialised", initPay.getPayrollAlreadyInitialisedMsg().get());
//	}
		
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		initPay.close();
//		hp.close();
	}

}
