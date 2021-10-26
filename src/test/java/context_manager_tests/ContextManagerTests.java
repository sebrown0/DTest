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
import object_models.panels.PanelSwitcher;
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
		ContextId id = manager.getLastContext().getContextId();
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
		assertTrue(manager.getLastContext().getState() instanceof StateLeftMenu);				
	}

	@Test
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_newStateShouldBeHeaderPanel() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextId());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details:jsPanel-2", manager.getContextId());
		
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getLastContext().getState() instanceof StateHeaderPanel);
	}

	@Test	
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_twice_newContextShouldBeDocuments() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextId());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details:jsPanel-2", manager.getContextId());
		
		manager.closeCurrentStateInCurrentContext();
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getLastContext().getState() instanceof StateLeftMenu);
		assertEquals("Employee Document Management:jsPanel-1", manager.getContextId());		
	}

	@Test
	void loadEmployeeBanks_then_loadDocuments() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(Documents.class);		
	}
	
	@Test
	void loadPayroll_checkContext_and_state() {
		Context c = (Context) manager.getLastContext();
		assertTrue(c instanceof ContextPayroll);
		State s = c.getState();
		assertEquals("StateModule", s.getClass().getSimpleName());
	}

	@Test
	void findStateInContext_stateLeftMenu_notPresent() {
		manager.printQueue();
		assertFalse(manager.getLastContext().isStateInContext(StateLeftMenu.class));
	}

	@Test
	void findStateInContext_stateLeftMenu_isPresent() {
		assertTrue(manager.getLastContext().isStateInContext(StateTop.class));
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
		State current = manager.getLastContext().getState();
		assertEquals(current, manager.moveToStateInCurrentContext(StateModule.class).get()); 	
	}
	
	@Test
	void currentStateIsNotRequiredState_but_isInContext() {
		State current = manager.getLastContext().getState();
		assertFalse(current.getClass().getSimpleName() == StateLeftMenu.class.getSimpleName()); 			
		
		assertTrue(manager.isStateInCurrentContext(StateTop.class));
		current = manager.moveToStateInCurrentContext(StateTop.class).get();
		assertEquals(current.getClass().getSimpleName(), StateTop.class.getSimpleName()); 	
	}
	
	@Test
	void currentStateIsNotRequiredState_and_isNotInContext() {
		State current = manager.getLastContext().getState();
		assertFalse(current.getClass().getSimpleName() == StateLeftMenu.class.getSimpleName()); 			
		
		assertFalse(manager.isStateInCurrentContext(StateLeftMenu.class));
		current = manager.moveToStateInCurrentContext(StateLeftMenu.class).get();
		assertEquals(current.getClass().getSimpleName(), StateLeftMenu.class.getSimpleName()); 	
	}
	
	@Test
	void loadDocuments_then_close_context_currentContext_shouldBe_ContextPayroll() {
		menu.clickAndLoad(Documents.class);		
		Context c = (Context) manager.closeCurrentContext().getLastContext();
		assertTrue(c instanceof ContextPayroll);		
		// Try and close the current (Payroll) context.
		// It should not be possible.		 
		c = (Context) manager.closeCurrentContext().getLastContext();
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
	void findContextInQueue_usingFullId() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		assertEquals(
				MonthlyReports.PANEL_TITLE + ":jsPanel-2", 
				manager.findContext(MonthlyReports.PANEL_TITLE + ":jsPanel-2").get().getContextId().getId());		
	}
		
	@Test	
	void findContextInQueue_usingPanelTitle() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		assertEquals(
				MonthlyReports.PANEL_TITLE + ":jsPanel-2", 
				manager.findContext(MonthlyReports.PANEL_TITLE).get().getContextId().getId());		
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
	void checkExistingContextIsLoaded_fromMenu() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		Banks b = (Banks) menu.clickAndLoad(Banks.class).get();
		assertEquals(Banks.PANEL_TITLE + ":jsPanel-1", b.getContextId().getId());		
	}

	@Test	
	void checkExistingContextIsLoaded_fromContextManager() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Banks.class);		
		
		ContextState cs = manager.findContext(Banks.PANEL_TITLE + ":jsPanel-2").get();
		State hdr = manager.switchToStateInContext(StateHeaderPanel.class, cs).get();
		assertTrue(hdr instanceof StateHeaderPanel);
	}
	
	@Test	
	void loadTwoPanels_switchToFirstPanel_usingContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Banks.class);		
		
		ContextState csBanks = manager.getLastContext();
		/*
		 * THIS HAS TO BE USED WHEN 
		 * 	SWITCHING CONTEXTS
		 * AND/OR
		 * 	StateHeaderPanel.switchToMe()
		 * 
		 * StateHeaderPanel SHOULD BE THE DEFAULT STATE.
		 * THEN WHEN THE CONTEXT IS LOADED THE PANEL
		 * SHOULD AUTOMATICALLY BE SWITCHED.
		 */
		PanelSwitcher panelSwitcherBanks = (PanelSwitcher) csBanks.getContinerAction();
		panelSwitcherBanks.switchToExistingPanel(MonthlyReports.class);

		ContextState csReports = manager.getCurrentContext();
		assertEquals(MonthlyReports.PANEL_TITLE +  ":jsPanel-1", csReports.getContextId().getId());
	}
	
	/* TO SWITCH TO A PANEL
	 * --------------------
	 * 1. HAVE TO GET CONTEXT THAT IS A PANEL 									CM -> getContextThatIsPanel();
	 * 2. USE THIS CURRENT CONTEXT TO GET THE DROPDOWN					Is PanelSwitcher;
	 * 3. THEN USE THE REQUIRED CLASS TO FIND THE PANEL TITLE
	 * 4. LOAD THE PANEL USING THE PANEL TITLE
	 * 5. SET THAT PANEL'S CONTEXT AS THE CURRENT CONTEXT
	 * 
	 * -> HOW DO WE KNOW WHICH PANEL WE WANT
	 * 
	 */
	@Test	
	void loadTwoPanels_XXXXXXXXXXXXXXXX() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Banks.class);		
		
		// 1. HAVE TO GET CONTEXT THAT IS A PANEL.
		JsPanel banks = manager.getContextThatIsPanel().get();
		assertTrue(banks instanceof JsPanel);
		
		//
		banks.getHeaderBar().getToolBar().showDropDownMenu();
//		ContextState csBanks = manager.getLastContext();
//		JsPanel panelBanks = (JsPanel) csBanks.getContinerAction();
//		PanelSwitcher panelSwitcher = (PanelSwitcher) panelBanks;
//		
//		
//		ContextState csReps = manager.findContext(MonthlyReports.PANEL_TITLE + ":jsPanel-1").get();
//
//		ffff(panelSwitcher, csReps);
//		ffff(panelSwitcher, (JsPanel) csReps.getContinerAction());

//		ContextState csReports = manager.getCurrentContext();
//		assertEquals(MonthlyReports.PANEL_TITLE +  ":jsPanel-1", csReports.getContextId().getId());
	}

	private <T extends JsPanel> void ffff(PanelSwitcher panelSwitcher, ContextState cs){
		JsPanel panel = (JsPanel) cs.getContinerAction();
		// use the panel (switcher) switch to panel with class -> panel.getClass()
//		panelSwitcher.switchToExistingPanel(panel.getClass(), cs);
		panelSwitcher.switchToExistingPanel(panel, cs);
	}


	@Test	
	void currentContext_afterDeleting_currentContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);
		menu.clickAndLoad(Banks.class);		
		
		manager.deleteContext(manager.getLastContext());
		assertEquals(YearlyReports.PANEL_TITLE, manager.getCurrentContext().getContextId().getExpectedName());
	}

	@Test
	void getContextThatIsPanel_shouldReturnCurrentContext_thatIs_YearlyReports() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);
		
		JsPanel reports = manager.getContextThatIsPanel().get();
		assertTrue(reports instanceof JsPanel);
	}
	
	@Test
	void getContextThatIsPanel_shouldReturnEmployeeDetails_thatIs_YearlyReports() {
		EmployeeDetails empDetails = (EmployeeDetails) menu.clickAndLoad(EmployeeDetails.class).get();
		EmployeeSelection empSelection = (EmployeeSelection) empDetails.getEmployeeControl().getControl(EmployeeControlNames.SELECT_EMP).get();
		assertTrue(empSelection instanceof FormModal);
		//Should be one context that is a panel.
		empDetails = (EmployeeDetails) manager.getContextThatIsPanel().get();
		assertTrue(empDetails instanceof JsPanel);
	}
	
	@Test	
	void currentContext_afterDeleting_currentContext_which_isFirstContext_shouldBePayroll() {
		menu.clickAndLoad(MonthlyReports.class);				
		
		ContextState first = manager.findContext("Payroll Module").get();
		manager
			.moveToExistingContext(first)
			.deleteContext(first);
		
		assertEquals("Payroll Module", manager.getCurrentContext().getContextId().getExpectedName());
	}
	
	@Test	
	void currentContext_afterDeleting_currentContext_which_isSecondContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);		
//		menu.clickAndLoad(Banks.class);
		
		ContextState first = manager.findContext(MonthlyReports.PANEL_TITLE).get();
		manager
			.moveToExistingContext(first)
			.deleteContext(first);
		
		/*
		 * DELETE CONTEXT FROM CM
		 * ----------------------
		 * CM -> deleteContext(cs) 
		 * 			-> ContextQueue.removeContextForContextId(cs) 
		 * 			-> ContextQueue.removeContext(cs)
		 * 				-> cs.getContextCloser().close();
		 */
		
		/*
		 * IF DELETING CONTEXT HEADER HAVE TO GO TO THE CONTROL BAR AND CLICK CLOSE
		 * THEN SWITCH TO THE PREV CONTEXT.
		 * THAT CONTEXT SHOULD BE LOADED, I.E.
		 *  Set as the current context and switch to default state in context.
		 */
//		assertEquals(YearlyReports.PANEL_TITLE, manager.getCurrentContext().getContextId().getExpectedName());
	}
	
	// remove if not needed
	@Test	
	void findContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);		
		
		ContextState first = manager.findContext(MonthlyReports.PANEL_TITLE).get();
		manager.moveToExistingContext(first);
		assertEquals("jsPanel-1", manager.getCurrentContext().getContextId().getActualId());
	}
}
