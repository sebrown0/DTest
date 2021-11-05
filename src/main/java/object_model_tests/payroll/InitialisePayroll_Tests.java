package object_model_tests.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.ComboSelect;
import logging.TestResultLogger;
import object_models.dialog.DialogOkCancel;
import object_models.forms.ContainerAction;
import object_models.helpers.text_utils.RemoveX;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.payroll.InitialisePayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class, 
	TestResultLogger.class })
public final class InitialisePayroll_Tests {
	private static HomePage hp;
	private static LeftMenu leftMenu;
	private static Optional<ContainerAction> contPay;
	private static InitialisePayroll initPay;
//	private static boolean initPayrollLoaded = false;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		hp = userLogin.loginValidUser(UserProvider.userPortal());
		leftMenu = hp.getLeftMenu();
		contPay = leftMenu
				.clickParent(InitialisePayroll.MENU_PARENT_NAME)
				.clickAndLoad(InitialisePayroll.class);

		if(contPay.isPresent()) {
			initPay = (InitialisePayroll) contPay.get();
//			initPayrollLoaded = true;
		}	else {
			fail("Could not get InitialisePayroll object");
		}	
	}
	
	@Test
	@Order(1)
	void loadInitialisePayroll() {
		// NOT HAVING METHOD getIframeTitle JUST FOR ONE TEST
//		if(initPayrollLoaded) {			
//			assertEquals("Initialise Payroll", initPay.getIframeTitle());
//		}
	}

	@Test
	@Order(2)
	void checkCompany() {
		ComboSelect selectComp = initPay.getSelectCompany(); 
		assertEquals("Mars Incorporated Ltd", selectComp.getText(new RemoveX()));
	}
	
	@Test
	@Order(3)
	void checkPayGroup() {
		ComboSelect selectPayGroup = initPay.getSelectPayGroup(); 
		assertEquals("Monthly Paygroup", selectPayGroup.getText(new RemoveX()));
	}

	@Test
	@Order(4)
	void checkPayPeriod() {
		ComboSelect selectPayPeriod = initPay.getSelectPayPeriod(); 
		assertTrue(selectPayPeriod.getText(new RemoveX()).length() > 0);
	}
	
	@Test
	@Order(5)
	void click_initialisePayroll_check_DialogAppears_and_clickCancel() {
		DialogOkCancel okCancel = (DialogOkCancel) initPay.clickInitialisePayroll();
		okCancel.getBtnCancel().get().click();
		assertEquals("Are you sure you want to Initialise the Payroll ?", okCancel.getMsg().get());
		assertEquals("Payroll Initialisation", okCancel.getTitle().get());
		assertEquals("OK", okCancel.getBtnOk().get().getElementKey());
		assertEquals("Cancel", okCancel.getBtnCancel().get().getElementKey());
	}
	
//	@Test
//	@Order(6)
//	void click_initialisePayroll_then_clickOk() {
//		DialogOkCancel okCancel = (DialogOkCancel) initPay.clickInitialisePayroll();
//		okCancel.getBtnOk().get().click();
//		Optional<String> msg = initPay.getPayrollInitialisedMsg();
//	}
	
	@Test
	@Order(7)
	void assert_that_payroll_is_already_initialised() {
		assertEquals("This Payroll has already been Initialised", initPay.getPayrollAlreadyInitialisedMsg().get());
	}
		
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		initPay.close();
		hp.close();
	}

}
