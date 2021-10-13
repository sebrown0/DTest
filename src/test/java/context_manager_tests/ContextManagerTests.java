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
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
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
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		homepagePayroll = userLoginPayroll.loginValidUser(UserProvider.userPortal());
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		homepagePayroll.close();
	}
	
	@Test
	void checkContextManagerLoaded() {
		ContextManager cm = homepagePayroll.getContextManager();
		cm.setContext(new ContextPanel());
		assertTrue(cm.getContext().getState() instanceof StateHeaderPanel);		
	}
	
	@Test
	void loadDocument_and_checkState() {
		LeftMenu lm = homepagePayroll.getLeftMenu();
		lm.clickAndLoad(Documents.MENU_TITLE);
		ContextManager cm = homepagePayroll.getContextManager();
		assertTrue(cm.getContext().getState() instanceof StateHeaderPanel);		
	}
}
