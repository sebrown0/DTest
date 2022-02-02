/**
 * 
 */
package context_manager_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import context_manager.states.StateTop;
import enums.control_names.GroupControlNames;
import logging.TestResultLogger;
import object_models.controls.EmployeeSelection;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.forms.ContainerAction;
import object_models.forms.FormModal;
import object_models.left_menu.common.LeftMenu;
import object_models.modules.payroll.Documents;
import object_models.modules.payroll.MonthlyReports;
import object_models.modules.payroll.PayrollStatistics;
import object_models.modules.payroll.YearlyReports;
import object_models.modules.payroll.left_menu.employees.Banks;
import object_models.modules.payroll.left_menu.employees.EmployeeDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import object_models.panels.JsPanel;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCreation;
import object_models.top_right_nav_bar.common.TopRightNavBar;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Test the elements of the home page for payroll.
 * 
 * THE RESULTS WILL BE DIFFERENT DEPENDING ON IF THE
 * TESTS ARE RUN AS A WHOLE OR INDIVIDUALLY.
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
		assertEquals("Employee Document Management", manager.getExpectedNameOfCurrentContext());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details", manager.getExpectedNameOfCurrentContext());
		
		manager.closeCurrentStateInCurrentContext();
		assertTrue(manager.getLastContext().getState() instanceof StateHeaderPanel);
	}

	@Test	@Order(8)
	void loadDocuments_then_employeeDetails_then_close_employeeDetailsState_twice_newContextShouldBeDocuments() {
		menu.clickAndLoad(Documents.class);
		assertEquals("Employee Document Management", manager.getExpectedNameOfCurrentContext());
		
		menu.clickAndLoad(EmployeeDetails.class);
		assertEquals("Employee Details", manager.getExpectedNameOfCurrentContext());
		
		manager.closeCurrentStateInCurrentContext();
		manager.closeCurrentStateInCurrentContext();		
		assertTrue(manager.getLastContext() instanceof ContextPayroll);		
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
//		Optional<State> s = manager.moveToStateInCurrentContext(StateLeftMenu.class);		
//		assertTrue(s.get() instanceof StateLeftMenu);
//		TODO
	}

	@Test	@Order(12)
	void isStateInContext() {
		ContextState conModule = manager.getContextThatIsFirstContext().get();
		assertTrue(conModule instanceof ContextPayroll);
	}

	@Test	@Order(13)
	void currentStateIsRequiredState() {
		ContextState conModule = manager.getContextThatIsFirstContext().get(); 
		State state = conModule.getState();
		assertTrue(state instanceof StateLeftMenu);		
	}
		
	@Test	@Order(14)
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

	@Test	@Order(15)
	void penultimateContext_exists() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Documents.class);
		menu.clickAndLoad(PayrollStatistics.class);
		ContextState doc = manager.getPenultimateContext().get();

		assertTrue(doc.getContextId().getExpectedName().length() > 0);		
	}
	
	@Test	@Order(16)
	void findContextInQueue_usingPanelTitle() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		assertEquals(
				MonthlyReports.PANEL_TITLE, 
				manager.findContext(MonthlyReports.PANEL_TITLE).get().getContextId().getExpectedName());		
	}

	@Test	@Order(17)
	void findPrevContextInQueue() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		
		ContextState forContext = manager.findContext(MonthlyReports.PANEL_TITLE).get();
		ContextId prevId = manager.getPrevContext(forContext).get().getContextId();  	
		assertEquals(Banks.PANEL_TITLE, prevId.getExpectedName());
	}

	@Test	@Order(18)
	void checkExistingContextIsLoaded_fromMenu() {
		menu.clickAndLoad(Banks.class);
		menu.clickAndLoad(MonthlyReports.class);
		Banks b = (Banks) menu.clickAndLoad(Banks.class).get();
		assertEquals(Banks.PANEL_TITLE, b.getContextId().getExpectedName());		
	}

	@Test	@Order(19)	
	void loadTwoPanels_switchToFirstPanel_contextManager() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(Banks.class);		
		
		manager.switchToExistingPanel(MonthlyReports.class);	

		ContextState csReports = manager.getCurrentContext();
		assertEquals(MonthlyReports.PANEL_TITLE, csReports.getContextId().getExpectedName());
	}

	@Test	@Order(20)
	void currentContext_afterDeleting_currentContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);
		menu.clickAndLoad(Banks.class);	
		
		manager.removeAndCloseContext(manager.getCurrentContext());
		assertEquals("Payroll Module", manager.getCurrentContext().getContextId().getExpectedName());		
	}

	@Test	@Order(21)
	void getContextThatIsPanel_shouldReturnCurrentContext_thatIs_YearlyReports() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class); 		
		JsPanel reports = manager.getContextThatIsPanel().get();
		assertTrue(reports instanceof YearlyReports);
	}
	
	@Test	@Order(22)
	void getContextThatIsPanel_shouldReturnEmployeeDetails_thatIs_YearlyReports() {
		EmployeeDetails empDetails = (EmployeeDetails) menu.clickAndLoad(EmployeeDetails.class).get();
		EmployeeSelection empSelection = (EmployeeSelection) empDetails.getPanelControl().getControl(GroupControlNames.SELECT_EMP).get();
		assertTrue(empSelection instanceof FormModal);
		//Should be one context that is a panel.
		JsPanel panel = manager.getContextThatIsPanel().get();
		assertTrue(panel instanceof JsPanel);
	}
	
	@Test	@Order(23)
	void currentContext_afterDeleting_currentContext_which_isFirstContext_shouldBePayroll() {
		menu.clickAndLoad(MonthlyReports.class);				
		
		ContextState first = manager.findContext("Payroll Module").get();
		manager
			.moveToExistingContext(first)
			.removeAndCloseContext(first);
		
		assertEquals("Payroll Module", manager.getCurrentContext().getContextId().getExpectedName());
	}
		
	@Test	@Order(24)
	void findContext() {
		menu.clickAndLoad(MonthlyReports.class);
		menu.clickAndLoad(YearlyReports.class);		
		
		ContextState first = manager.findContext(MonthlyReports.PANEL_TITLE).get();
		manager.moveToExistingContext(first);
		assertEquals("Monthly Payroll Reports", manager.getCurrentContext().getContextId().getExpectedName());
	}
	
	@Test	@Order(25)
	void findContext_navBar_using_clickAndLoad() {		
		TopRightNavBar navBar = homepagePayroll.getTopRightNavBar();
		ContainerAction empCr = navBar.clickAndLoad(NavBarEmployeeCreation.class).get();
		
		ContextState cs = manager.findContext(EmployeeCreationWizard.PANEL_TITLE).get();
		manager.moveToExistingContext(cs);
		assertEquals("Employee Creation Wizard", manager.getCurrentContext().getContextId().getExpectedName());
		
		empCr.close(); 
	}
	
	@Test	@Order(26)
	void changeModule_shouldBeA_newContextManager() {
		HomePage homepagePersonnel = homepagePayroll.loadModule("Personnel");
		ContextManager cm = homepagePersonnel.getContextManager();
		assertTrue(homepagePersonnel != null);
		assertTrue(cm.getContextQueue().size() == 1);
	}
}
