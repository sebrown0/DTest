/**
 * 
 */
package context_manager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
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
@SuppressWarnings("unlikely-arg-type")
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class ContextManagerTests {
	private static HomePage homepagePayroll;
	private static ContextManager manager;
	private static LeftMenu menu;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		homepagePayroll = userLoginPayroll.loginValidUser(UserProvider.userPortal());
		manager = homepagePayroll.getContextManager();
		menu = homepagePayroll.getLeftMenu();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		homepagePayroll.close();
	}

	@Test
	void checkContextId() {
		menu.clickAndLoad(Documents.MENU_TITLE);
		ContextId id = manager.getContext().getContextId();
		manager.closeCurrent();
		assertEquals("Employee Document Management:jsPanel-1", id.getContextId());
	}
	
	@Test
	void checkContextId_equalsValue() {
		ContextId id = new ContextId("expected", Optional.of("actual"));
		// Obj == Obj
		assertTrue(id.equals(id));
		// String
		assertTrue(id.equals("expected:actual"));
		assertFalse(id.equals("expected:actual-X"));
		assertTrue(id.equals("expected"));
		assertFalse(id.equals("expecte"));
		assertTrue(id.equals("actual"));
		assertFalse(id.equals("act"));
		// Object
		assertTrue(id.equals(new ContextId("expected", Optional.of("actual"))));
		assertTrue(id.equals(new ContextId("expect", Optional.of("actual"))));
		assertTrue(id.equals(new ContextId("expected", Optional.of("act"))));
		assertFalse(id.equals(new ContextId("expect", Optional.of("act"))));
	}
	
	@Test
	void checkContextIsInQueue() {
		menu.clickAndLoad(Documents.MENU_TITLE);
		ContextState cs = manager.getEndOfQueue();
		manager.closeCurrent();
		assertTrue(cs.getContextId().equals("Employee Document Management"));
	}
	
	@Test
	void getContextFromQueue_usingContextId_actual() {
		menu.clickAndLoad(Documents.MENU_TITLE);
		ContextState cs = manager.findContext("jsPanel-1").get();
		manager.closeCurrent();
		assertEquals("Employee Document Management", cs.getContextId().getExpectedName());
	}
	
	@Test
	void getContextFromQueue_usingContextId_object() {
		menu.clickAndLoad(Documents.MENU_TITLE);
		ContextState cs = manager.findContext(new ContextId("Employee Document Management", Optional.of("jsPanel-1"))).get();
		manager.closeCurrent();
		assertEquals("Employee Document Management", cs.getContextId().getExpectedName());
	}
	
	@Test
	void removeContextFromQueue_usingContextId_actual() {
		menu.clickAndLoad(Documents.MENU_TITLE);
		ContextState cs = manager.findContext("jsPanel-1").get();
		boolean isRemoved = manager.removeContextFromQueue(cs.getContextId());
		manager.closeCurrent();
		assertTrue(isRemoved);
	}
	
	@Test
	void loadDocument_and_checkState() {		
		menu.clickAndLoad(Documents.MENU_TITLE);
		manager.closeCurrent();
		assertTrue(manager.getContext().getState() instanceof StateLeftMenu);				
	}
	
	@Test
	void loadDocuments_then_employeeDetails_then_close_employeeDetails() {
		menu.clickAndLoad(Documents.MENU_TITLE);
		manager.switchToFirstState();
		menu.clickAndLoad(EmployeeDetails.MENU_TITLE);
		manager.closeCurrent();
		assertTrue(manager.getContext().getState() == null);	
	}
	
}
