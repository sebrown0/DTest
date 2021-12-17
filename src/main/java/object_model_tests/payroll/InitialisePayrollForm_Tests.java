package object_model_tests.payroll;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.TextSelect;
import enums.control_names.CommonControlNames;
import enums.control_names.PayrollControlNames;
import exceptions.PayrollAlreadyInitialisedException;
import logging.TestResultLogger;
import object_models.dialog.DialogOkCancel;
import object_models.left_menu.common.LeftMenuLoadItem;
import object_models.left_menu.payroll.initialise.InitialisePayroll;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePagePayroll;
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
 * Test the Initialise Payroll form.
 * Does not test the initialisation of payroll.
 * For this see Payroll_Tests
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class, 
	TestResultLogger.class })
public final class InitialisePayrollForm_Tests {
	private static HomePagePayroll hp;
	private static LeftMenuLoadItem leftMenu;	
	private static InitialisePayroll initPay;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		hp = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());
		leftMenu = hp.getLeftMenu();
		// Load initialise pay from container.
		leftMenu
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
	void initialisePayroll() {
		DialogOkCancel okCancel;
		try {
			okCancel = initPay.clickInitialisePayroll();
			okCancel.getBtnCancel().ifPresent(b -> b.click());
		} catch (PayrollAlreadyInitialisedException e) {
			// Not necessarily a fail, depends on the current state.
			fail("WARNING - Payroll already initialised");
		}		
	}
	
	@Test	@Order(6) 
	void closeForm() {
		initPay.closeFormAndContext();
	}
		
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		hp.close();
	}

}
