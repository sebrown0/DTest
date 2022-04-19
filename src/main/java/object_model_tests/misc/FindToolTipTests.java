/**
 * 
 */
package object_model_tests.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import dynamic_tests.elements.ControlGroup;
import library.common.controls.button.Button;
import library.helpers.login.UserLoginPage;
import library.left_menu.LeftMenu;
import library.object_models.modules.payroll.left_menu.employees.SalaryDetails;
import library.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 * Verify and employee loaded by EmployeeProvider.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class })
class FindToolTipTests {
	private static HomePage homepagePayroll;	
	private static LeftMenu menu;
	private static SalaryDetails salDetails;
		
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		// Load the menu.
		menu = homepagePayroll.getLeftMenu();
		// Load the employee salary details page.
		salDetails = 
				(SalaryDetails) menu
						.clickParent("Employees")
						.clickAndLoad(SalaryDetails.class)
						.get();
	}
		
	@Test @Order(1)
	void select_existingRecords_forEmployee() {
		ControlGroup grp = 
				(ControlGroup) salDetails
						.getPanelControl()
						.getControl("EmpLookup")
						.get();			
		Button empList = (Button) grp.getControlByTitle("ExistingRecords").get();
		
		assertEquals("Grid View for existing records", empList.getToolTipText());		 	
	}	
	
	@AfterAll
	static void tearDown() {		
		homepagePayroll.close();
	}
}
