/**
 * 
 */
package context_manager_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import context_manager.ContextManager;
import context_manager.ContextPanel;
import context_manager.StateHeaderPanel;
import context_manager.StateIframe;
import context_manager.StateTop;
import context_manager.StateLeftMenu;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.left_menu.parents.Documents;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Test the elements of the home page for payroll.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class ContextManagerTests {
	private static HomePage homepagePayroll;
	private static ContextManager cm;
	private static LeftMenu lm;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		homepagePayroll = userLoginPayroll.loginValidUser(UserProvider.userPortal());
		cm = homepagePayroll.getContextManager();
		lm = homepagePayroll.getLeftMenu();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		homepagePayroll.close();
	}
	
	@Test
	void loadDocument_and_checkState() {		
		lm.clickAndLoad(Documents.MENU_TITLE);		
		assertTrue(cm.getContext().getState() instanceof StateLeftMenu);	
		cm.closeCurrent();		
	}
	
	@Test
	void loadDocuments_then_employeeDetails_then_close_employeeDetails() {
		lm.clickAndLoad(Documents.MENU_TITLE);
		lm.clickAndLoad(EmployeeDetails.MENU_TITLE);
//		cm.closeCurrent();
//		assertTrue(cm.getContext().getState() == null);	
	}
}
