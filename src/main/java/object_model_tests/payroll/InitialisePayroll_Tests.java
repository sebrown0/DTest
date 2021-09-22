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
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.element.ComboSelect;
import object_models.forms.ContainerAction;
import object_models.left_menu.common.LeftMenuActions;
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
	private static WebDriver driver;
	private static LeftMenuActions leftMenu;
	private static Optional<ContainerAction> contPay;
	private static InitialisePayroll initPay;
	private static boolean initPayrollLoaded = false;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		driver = hp.getWebDriver();
		leftMenu = hp.getLeftMenu();
		contPay = leftMenu
				.clickParent(InitialisePayroll.MENU_PARENT_NAME)
				.clickAndLoad(InitialisePayroll.MENU_TITLE);
		
		if(contPay.isPresent()) {
			initPay = (InitialisePayroll) contPay.get();
			initPayrollLoaded = true;
		}	else {
			fail("Could not get InitialisePayroll object");
		}	
	}

	@Test
	@Order(1)
	void loadInitialisePayroll() {		
		if(initPayrollLoaded) {			
			assertEquals("Initialise Payroll", initPay.getIframeTitle());
		}
	}

	@Test
	void checkCompany() {
		ComboSelect selectComp = initPay.getSelectCompany(); 
		assertEquals("Mars Incorporated Ltd", selectComp.getText());
	}
	
	@Test
	void checkPayGroup() {
		ComboSelect selectPayGroup = initPay.getSelectPayGroup(); 
		assertEquals("Monthly Paygroup", selectPayGroup.getText());
	}

	@Test
	void checkPayPeriod() {
		ComboSelect selectPayPeriod = initPay.getSelectPayPeriod(); 
		assertTrue(selectPayPeriod.getText().length() > 0);
	}
	
//	@Test
//	void checkCompany() {
//		Optional<WebElement> selectComp = initPay.getSelectCompany(); 
//		selectComp.ifPresentOrElse(s -> 
//			assertEquals("Mars Incorporated Ltd", s.getText()), 
//			new TestFail("Select Company could not be found")
//		);
//	}
	
//	private class TestFail implements Runnable {
//		private String msg;
//		
//		public TestFail(String msg) {
//			this.msg = msg;			
//		}
//
//		@Override
//		public void run() {
//			fail(msg);
//		}		
//	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		initPay.closeElement();
		driver.quit();
	}

}
