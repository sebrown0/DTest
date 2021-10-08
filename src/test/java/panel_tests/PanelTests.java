package panel_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import enums.control_names.EmployeeControlNames;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.panels.JSPanel;
import object_models.panels.JsPanelContextManager;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class PanelTests {
	private static HomePage homepagePayroll;	
	private static LeftMenu menu;
	private static EmployeeDetails empDetails;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		// Load the menu.
		menu = homepagePayroll.getLeftMenu();
		// Load the employee details page.
		empDetails = (EmployeeDetails) menu.clickParent("Employees").clickAndLoad(EmployeeDetails.MENU_TITLE).get();
	}
	
	@Test
	void checkPanelId() {
		assertEquals("jsPanel-1", empDetails.getPanelId().get());
	}
	
	@Test
	void checkPanelTitle() {
		assertEquals("Employee Details", empDetails.getHeaderBar().getTitle().get());
	}
	
	@Test
	void switchFromFrame_to_panel_then_PanelToFrame() {		
		assertEquals(JsPanelContextManager.FrameOrPanel.FRAME, empDetails.getContextManager().getFrameOrPanel());
		empDetails.getHeaderBar().getTitle().get();
		assertEquals(JsPanelContextManager.FrameOrPanel.PANEL, empDetails.getContextManager().getFrameOrPanel());
		empDetails.getEmployeeControl().getControl(EmployeeControlNames.SELECT_EMP).get();
		assertEquals(JsPanelContextManager.FrameOrPanel.FRAME, empDetails.getContextManager().getFrameOrPanel());
	}
	
	@AfterAll
	static void tearDown() {		
		homepagePayroll.close();
	}
}