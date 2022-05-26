/**
 * 
 */
package control_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import element_library.common.controls.button.Button;
import element_library.common.controls.combos.ComboSelectFromList;
import element_library.common.controls.with_text.TextOut;
import element_library.common.controls.with_text.TextSelect;
import library.helpers.login.UserLoginPage;
import library.left_menu.LeftMenu;
import library.object_models.modules.payroll.left_menu.employees.SalaryDetails;
import library.pages.homepage.HomePage;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.ZZZ_UserProvider;
import root.elements.ControlGroup;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class Control_Tests {
	private static HomePage homepagePayroll;	
	private static LeftMenu menu;
	private static SalaryDetails salDetails;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(ZZZ_UserProvider.userPortal());		
		// Load the menu.
		menu = homepagePayroll.getLeftMenu();
		// Load the employee details page.
		salDetails = (SalaryDetails) menu.clickParent("Employees").clickAndLoad(SalaryDetails.class).get();
	}

	@AfterAll
	static void tearDown() {		
		homepagePayroll.close();
	}

//	@Test
//	void getEmployeeList() {
//		PageControl pageControl = salDetails.getPanelControl();
//		final String PARENT = "EmpLookup";
//		final String CHILD = "EmployeeList";
//		
//		pageControl.findFirst(PARENT, CHILD);
//	}
	@Test
	void ZZZZZZZZZZZZZZZZZZZget_reasonFrom_SalDetails() {
		ControlGroup grpTabs = 
				(ControlGroup) salDetails
						.getPanelControl()
						.getControl("Tabs")
						.get();
		
		ControlGroup grpTabSalDetails = 
			(ControlGroup) grpTabs
				.getControlByTitle("SalaryDetails")
				.get(); 

		TextSelect reason = 
			(TextSelect) grpTabSalDetails.getControlByTitle("Reason").get();
		
		assertTrue(reason != null);
	}
	
	@Test
	void get_reasonFrom_SalDetails() {
		ControlGroup grpTabs = 
				(ControlGroup) salDetails
						.getPanelControl()
						.getControl("Tabs")
						.get();
		
		ControlGroup grpTabSalDetails = 
			(ControlGroup) grpTabs
				.getControlByTitle("SalaryDetails")
				.get(); 

		TextSelect reason = 
			(TextSelect) grpTabSalDetails.getControlByTitle("Reason").get();
		
		assertTrue(reason != null);
	}
	
	@Test
	void get_btnEmployeeList() {
		ControlGroup grp = 
			(ControlGroup) salDetails
					.getPanelControl()
					.getControl("EmpLookup")
					.get();
		
		Button empList = (Button) grp.getControlByTitle("EmployeeList").get();
		assertEquals("Search Employee", empList.getToolTipText());
	}
	
	@Test
	void get_date() {
		ControlGroup datePicker = 
			(ControlGroup) salDetails
					.getPanelControl()
					.getControl("DatePicker")
					.get();
		
		TextOut date = (TextOut) datePicker.getControlByTitle("Date").get();
		assertTrue(date.isAvailable());
	}
	
	@Test
	void get_tabSalaryDetails() {
		ControlGroup tabs = 
			(ControlGroup) salDetails
					.getPanelControl()
					.getControl("Tabs")
					.get();
		
		ControlGroup salDetails = (ControlGroup) tabs.getControlByTitle("SalaryDetails").get();
		assertEquals("Salary Details", salDetails.getText());
	}
	
	@Test
	void get_reason_from_tabSalaryDetails() {
		ControlGroup tabs = 
			(ControlGroup) salDetails
					.getPanelControl()
					.getControl("Tabs")
					.get();
		
		ControlGroup salDetails = (ControlGroup) tabs.getControlByTitle("SalaryDetails").get();
		ComboSelectFromList reason = 
				(ComboSelectFromList) salDetails.getControlByTitle("Reason").get();
		assertTrue(reason != null);
	}
	
	@Test
	void get_newBtn_faFa() {
		ControlGroup footerBtns =
			(ControlGroup) salDetails
				.getPanelControl()
				.getControl("FooterBtns")
				.get();
		
		Button newRec = (Button) footerBtns.getControlByTitle("NewRec").get();
		assertEquals("fa fa-plus", newRec.getFaFaText());
	}
}
