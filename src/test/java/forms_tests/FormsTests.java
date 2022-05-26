/**
 * 
 */
package forms_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import element_library.common.modal_forms.DropdownCombo;
import library.enums.control_names.GroupControlNames;
import library.helpers.login.UserLoginPage;
import library.left_menu.LeftMenu;
import library.object_models.modules.payroll.left_menu.employees.EmployeeDetails;
import library.pages.homepage.HomePage;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.ZZZ_UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 *
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class FormsTests {
	private static HomePage homepagePayroll;
//	private static ContextManager manager;
	private static LeftMenu menu;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLoginPayroll) {
		homepagePayroll = userLoginPayroll.loginValidUser(ZZZ_UserProvider.userPortal());
//		manager = homepagePayroll.getContextManager();
		menu = homepagePayroll.getLeftMenu();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		homepagePayroll.close();
	}
	

	@Test
	void modalFormWith_iFrame() {		
		EmployeeDetails empDetails = (EmployeeDetails) menu.clickAndLoad(EmployeeDetails.class).get();		
		DropdownCombo combos = (DropdownCombo) empDetails.getPanelControl().getControl(GroupControlNames.COMBOS).get();
		assertEquals("Dropdown Combo", combos.getHeader().getTitle().get());		
	}
}
