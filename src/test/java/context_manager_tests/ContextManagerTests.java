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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.contexts.Context;
import context_manager.contexts.ContextPanel;
import context_manager.contexts.ContextPayroll;
import context_manager.states.State;
import context_manager.states.StateHeaderPanel;
import context_manager.states.StateLeftMenu;
import context_manager.states.StateModule;
import context_manager.states.StateTop;
import enums.control_names.EmployeeControlNames;
import logging.TestResultLogger;
import object_models.controls.EmployeeSelection;
import object_models.forms.FormModal;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.Banks;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.left_menu.parents.Documents;
import object_models.left_menu.parents.MonthlyReports;
import object_models.left_menu.parents.PayrollStatistics;
import object_models.left_menu.parents.YearlyReports;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.panels.JsPanel;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Test the elements of the home page for payroll.
 * 
 * 
 * 
 * Context Load Order (assuming all panels except first):
 * ------------------------------------------------------
 * 1. PayrollModule
 * 2. Documents
 * 3. EmployeeDetails
 * 4.	Banks
 * 5. MonthlyReports
 * 6. PayrollStatistics
 * 7. YearlyReports
 * 
 */
@SuppressWarnings("unlikely-arg-type")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

	@Test	@Order(1)
	void checkContextId() {
		menu.clickAndLoad(Documents.class);
		ContextId id = manager.getLastContext().getContextId();
		manager.closeCurrentStateInCurrentContext();
		assertEquals("Employee Document Management:jsPanel-1", id.getId());
	}
		
	@Test	@Order(2)
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
	
	@Test	@Order(3)
	void checkContextIsInQueue() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.getEndOfQueue();
		manager.closeCurrentStateInCurrentContext();
		assertTrue(cs.getContextId().equals("Employee Document Management"));
	}
	

	@Test	@Order(4)
	void getContextFromQueue_usingContextId_contextName() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.findContext(Documents.PANEL_TITLE).get(); 
		manager.closeCurrentStateInCurrentContext();
		assertEquals("Employee Document Management", cs.getContextId().getExpectedName());
	}
	
	@Test	@Order(5)
	void getContextFromQueue_usingContextId_object() {
		menu.clickAndLoad(Documents.class);
		ContextState cs = manager.findContext(new ContextId("Employee Document Management", "jsPanel-1")).get();
		manager.closeCurrentStateInCurrentContext();
		assertEquals("Employee Document Management", cs.getContextId().getExpectedName());
	}
	
	@Test	@Order(6)
	void loadDocument_and_checkState_revertFromIframe_to_headerPanel() {		
		menu.clickAndLoad(Documents.class);
		manager.closeCurrentStateInCurrentContext(); 	
		assertTrue(manager.getLastContext().getState() instanceof StateHeaderPanel);				
	}

	@Test	@Order(7)
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_newStateShouldBeHeaderPanel() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextIdOfCurrentContext());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details:jsPanel-2", manager.getContextIdOfCurrentContext());
		
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getLastContext().getState() instanceof StateHeaderPanel);
	}

	@Test	@Order(8)
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_twice_newContextShouldBeDocuments() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextIdOfCurrentContext());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details:jsPanel-2", manager.getContextIdOfCurrentContext());
		
		manager.closeCurrentStateInCurrentContext();
		manager.closeCurrentStateInCurrentContext();		
		assertTrue(manager.getLastContext().getState() instanceof StateModule);		
	}

	@Test	@Order(9)
	void loadEmployeeBanks_then_loadDocuments() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(Documents.class);		
	}

	@Test	@Order(10)
	void findStateInContext_stateLeftMenu_isPresent() {
		assertTrue(manager.getLastContext().isStateInContext(StateTop.class));
	}
	
	@Test	@Order(11)
	void addNewStateToContext() {
		Optional<State> s = manager.moveToStateInCurrentContext(StateLeftMenu.class);		
		assertTrue(s.get() instanceof StateLeftMenu);
	}

	@Test	@Order(12)
	void isStateInContext() {
//		assertFalse(manager.isStateInCurrentContext(StateLeftMenu.class));
//		assertTrue(manager.isStateInCurrentContext(StateTop.class));
	}

	@Test	@Order(13)
	void currentStateIsRequiredState() {
		ContextState conModule = manager.getContextThatIsFirstContext().get(); 
		State state = conModule.getState();
		assertTrue(state instanceof StateModule);		
	}
	
	@Test	@Order(14)
	void todo() {		
	}
	
	@Test	@Order(15)
	void loadTwoPanels_then_close_currentContext_thrice_shouldBe_ContextPayroll() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(Documents.class);
		manager.removeLastContextFromQueue();
		Context c = (Context) manager.getLastContext();
		assertTrue(c instanceof ContextPanel);		
		// Try and close the current (Payroll) context.
		// It should not be possible.
		manager.removeLastContextFromQueue();
		manager.removeLastContextFromQueue();
		c = (Context) manager.getLastContext();		
		assertTrue(c instanceof ContextPayroll);			 
	}

	@Test	@Order(16)
	void penultimateContext_exists() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		menu.clickAndLoad(PayrollStatistics.class);
		ContextState doc = manager.getPenultimateContext().get();
//		/*
//		 * Setting the id to use later
//		 */
//		setMonthlyReportsId();
		assertTrue(doc.getContextId().getExpectedName().length() > 0);		
	}

//	private void setMonthlyReportsId() {
//		if(repId == null) {
//			ContextState rep = manager.findContext(MonthlyReports.PANEL_TITLE).get();
//			ContextId repId = rep.getContextId();
//			System.out.println("->" + repId); // TODO - remove or log	
//		}		
//	}
	
	@Test	@Order(17)
	void penultimateContext_doesNotExist() {
//		Optional<ContextState> con = manager.getPenultimateContext();
//		con.ifPresent(c -> { 
//			fail("Should be no penultimate context [" + c.getContextId() + "]"); 
//		});
	}
			
	@Test	@Order(18)	
	void findContextInQueue_usingFullId() {
//		menu.clickAndLoad(Banks.class);
//		menu.clickAndLoad(MonthlyReports.class);
//		menu.clickAndLoad(Documents.class);
//		assertEquals(repId,	manager.findContext(repId).get().getContextId());		
	}
		
	@Test	@Order(19)
	void findContextInQueue_usingPanelTitle() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		assertEquals(
				MonthlyReports.PANEL_TITLE, 
				manager.findContext(MonthlyReports.PANEL_TITLE).get().getContextId().getExpectedName());		
	}

	@Test	@Order(20)
	void findPrevContextInQueue() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		
		ContextState forContext = manager.findContext(MonthlyReports.PANEL_TITLE).get();
		ContextId prevId = manager.getPrevContext(forContext).get().getContextId();  	
		assertEquals(Banks.PANEL_TITLE, prevId.getExpectedName());
	}

	@Test	@Order(21)
	void checkExistingContextIsLoaded_fromMenu() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		Banks b = (Banks) menu.clickAndLoad(Banks.class).get();
		assertEquals(Banks.PANEL_TITLE, b.getContextId().getExpectedName());		
	}

	@Test	@Order(22)	
	void loadTwoPanels_switchToFirstPanel_contextManager() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Banks.class);		
		
		manager.switchToExistingPanel(MonthlyReports.class);	

		ContextState csReports = manager.getCurrentContext();
		assertEquals(MonthlyReports.PANEL_TITLE, csReports.getContextId().getExpectedName());
	}

	@Test	@Order(23)
	void currentContext_afterDeleting_currentContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);
		menu.clickAndLoad(Banks.class);	
		
		manager.removeAndCloseContext(manager.getCurrentContext());
		assertEquals("Payroll Module", manager.getCurrentContext().getContextId().getExpectedName());		
	}

	@Test	@Order(24)
	void getContextThatIsPanel_shouldReturnCurrentContext_thatIs_YearlyReports() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class); //why is current con 'PayrollModule' and not this?
		/*
		 * myContext for states is getting set to payroll not the correct context
		 */
		JsPanel reports = manager.getContextThatIsPanel().get();
		assertTrue(reports instanceof JsPanel);
	}
	
	@Test	@Order(25)
	void getContextThatIsPanel_shouldReturnEmployeeDetails_thatIs_YearlyReports() {
		EmployeeDetails empDetails = (EmployeeDetails) menu.clickAndLoad(EmployeeDetails.class).get();
		EmployeeSelection empSelection = (EmployeeSelection) empDetails.getEmployeeControl().getControl(EmployeeControlNames.SELECT_EMP).get();
		assertTrue(empSelection instanceof FormModal);
		//Should be one context that is a panel.
		JsPanel panel = manager.getContextThatIsPanel().get();
		assertTrue(panel instanceof JsPanel);
	}
	
	@Test	@Order(26)
	void currentContext_afterDeleting_currentContext_which_isFirstContext_shouldBePayroll() {
		menu.clickAndLoad(MonthlyReports.class);				
		
		ContextState first = manager.findContext("Payroll Module").get();
		manager
			.moveToExistingContext(first)
			.removeAndCloseContext(first);
		
		assertEquals("Payroll Module", manager.getCurrentContext().getContextId().getExpectedName());
	}
		
	@Test	@Order(27)
	void findContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);		
		
		ContextState first = manager.findContext(MonthlyReports.PANEL_TITLE).get();
		manager.moveToExistingContext(first);
		assertEquals("jsPanel-4", manager.getCurrentContext().getContextId().getActualId());
	}
}
