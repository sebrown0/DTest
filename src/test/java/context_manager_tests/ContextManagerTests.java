/**
 * 
 */
package context_manager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.contexts.Context;
import context_manager.contexts.ContextPayroll;
import context_manager.states.State;
import context_manager.states.StateHeaderPanel;
import context_manager.states.StateLeftMenu;
import context_manager.states.StateModule;
import context_manager.states.StateTop;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.Banks;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.left_menu.parents.Documents;
import object_models.left_menu.parents.MonthlyReports;
import object_models.left_menu.parents.PayrollStatistics;
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
 * 
 * ISSUES - TODO
 * -------------
 * 1. Tests run to the end but with errors.
 * 		
 * 		getContextFromQueue_usingContextId_object, 
 * 		getContextFromQueue_usingContextId_actual & 
 * 		removeContextFromQueue_usingContextId_actual
 * 
 * 		Have error this.actualId is null.
 * 		
 * 		This is probably because the context has been 
 * 		reverted to a module and there is no actual id.
 * 
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
		menu.clickAndLoad(Documents.class);
		ContextId id = manager.getCurrentContext().getContextId();
		manager.closeCurrentStateInCurrentContext();
		assertEquals("Employee Document Management:jsPanel-1", id.getId());
	}
	
	@Test
	void checkContextId_equalsValue() {
		ContextId id = new ContextId("expected", "actual");
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
		assertTrue(id.equals(new ContextId("expected", "actual")));
		assertTrue(id.equals(new ContextId("expect", "actual")));
		assertTrue(id.equals(new ContextId("expected", "act")));
		assertFalse(id.equals(new ContextId("expect", "act")));
	}
	
	@Test
	void checkContextIsInQueue() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.getEndOfQueue();
		manager.closeCurrentStateInCurrentContext();
		assertTrue(cs.getContextId().equals("Employee Document Management"));
	}
	
	@Test
	void getContextFromQueue_usingContextId_actual() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.findContext("jsPanel-1").get();
		manager.closeCurrentStateInCurrentContext();
		assertEquals("Employee Document Management", cs.getContextId().getExpectedName());
	}
	
	@Test
	void getContextFromQueue_usingContextId_object() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.findContext(new ContextId("Employee Document Management", "jsPanel-1")).get();
		manager.closeCurrentStateInCurrentContext();
		assertEquals("Employee Document Management", cs.getContextId().getExpectedName());
	}
	
	@Test
	void removeContextFromQueue_usingContextId_actual() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.findContext("jsPanel-1").get();
		boolean isRemoved = manager.removeContextFromQueueForContextId(cs.getContextId());
		manager.closeCurrentStateInCurrentContext();
		assertTrue(isRemoved);
	}
	
	@Test
	void loadDocument_and_checkState() {		
		menu.clickAndLoad(Documents.class);
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getCurrentContext().getState() instanceof StateLeftMenu);				
	}

	@Test
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_newStateShouldBeHeaderPanel() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextId());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details:jsPanel-2", manager.getContextId());
		
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getCurrentContext().getState() instanceof StateHeaderPanel);
	}

	@Test	
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_twice_newContextShouldBeDocuments() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextId());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details:jsPanel-2", manager.getContextId());
		
		manager.closeCurrentStateInCurrentContext();
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getCurrentContext().getState() instanceof StateLeftMenu);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextId());		
	}

	@Test
	void loadEmployeeBanks_then_loadDocuments() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(Documents.class);		
	}
	
	@Test
	void loadPayroll_checkContext_and_state() {
		Context c = (Context) manager.getCurrentContext();
		assertTrue(c instanceof ContextPayroll);
		State s = c.getState();
		assertEquals("StateModule", s.getClass().getSimpleName());
	}

	@Test
	void findStateInContext_stateLeftMenu_notPresent() {
		manager.printQueue();
		assertFalse(manager.getCurrentContext().isStateInContext(StateLeftMenu.class));
	}

	@Test
	void findStateInContext_stateLeftMenu_isPresent() {
		assertTrue(manager.getCurrentContext().isStateInContext(StateTop.class));
	}
	
	@Test
	void addNewStateToContext() {
		Optional<State> s = manager.moveToStateInCurrentContext(StateLeftMenu.class);		
		assertTrue(s.get() instanceof StateLeftMenu);
	}

	@Test
	void isStateInContext() {
		assertFalse(manager.isStateInCurrentContext(StateLeftMenu.class));
		assertTrue(manager.isStateInCurrentContext(StateTop.class));
	}
	
	@Test
	void currentStateIsRequiredState() {
		State current = manager.getCurrentContext().getState();
		assertEquals(current, manager.moveToStateInCurrentContext(StateModule.class).get()); 	
	}
	
	@Test
	void currentStateIsNotRequiredState_but_isInContext() {
		State current = manager.getCurrentContext().getState();
		assertFalse(current.getClass().getSimpleName() == StateLeftMenu.class.getSimpleName()); 			
		
		assertTrue(manager.isStateInCurrentContext(StateTop.class));
		current = manager.moveToStateInCurrentContext(StateTop.class).get();
		assertEquals(current.getClass().getSimpleName(), StateTop.class.getSimpleName()); 	
	}
	
	@Test
	void currentStateIsNotRequiredState_and_isNotInContext() {
		State current = manager.getCurrentContext().getState();
		assertFalse(current.getClass().getSimpleName() == StateLeftMenu.class.getSimpleName()); 			
		
		assertFalse(manager.isStateInCurrentContext(StateLeftMenu.class));
		current = manager.moveToStateInCurrentContext(StateLeftMenu.class).get();
		assertEquals(current.getClass().getSimpleName(), StateLeftMenu.class.getSimpleName()); 	
	}
	
	@Test
	void loadDocuments_then_close_context_currentContext_shouldBe_ContextPayroll() {
		menu.clickAndLoad(Documents.class);		
		Context c = (Context) manager.closeCurrentContext().getCurrentContext();
		assertTrue(c instanceof ContextPayroll);		
		// Try and close the current (Payroll) context.
		// It should not be possible.		 
		c = (Context) manager.closeCurrentContext().getCurrentContext();
		assertTrue(c instanceof ContextPayroll);			 
	}
	
	@Test
	void penultimateContext_exists() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		menu.clickAndLoad(PayrollStatistics.class);
		ContextState doc = manager.getPenultimateContext().get();
		assertEquals("Employee Document Management", doc.getContextId().getExpectedName());
	}

	@Test	
	void penultimateContext_doesNotExist() {
		Optional<ContextState> con = manager.getPenultimateContext();
		con.ifPresent(c -> { 
			fail("Should be no penultimate context [" + c.getContextId() + "]"); 
		});
	}
	
	@Test	
	void getPosInQueue() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		ContextState penultimate = manager.getPenultimateContext().get();
		assertEquals(2, manager.getQueue().getPositionInQueue(penultimate));		
	}
	
	@Test	
	void findContextInQueue() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		assertEquals(
				MonthlyReports.PANEL_TITLE + ":jsPanel-2", 
				manager.findContext(MonthlyReports.PANEL_TITLE + ":jsPanel-2").get().getContextId().getId());		
	}

	@Test	
	void findPrevContextInQueue() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		
		ContextState forContext = manager.findContext(MonthlyReports.PANEL_TITLE + ":jsPanel-2").get();
		ContextId prevId = manager.getPrevContext(forContext).get().getContextId(); 
		assertEquals(Banks.PANEL_TITLE + ":jsPanel-1", prevId.getId());
	}
	
	@Test	
	void xxxxxx() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		Banks b = (Banks) menu.clickAndLoad(Banks.class).get();
		assertEquals(Banks.PANEL_TITLE + ":jsPanel-1", b.getContextId().getId());
	}
}
