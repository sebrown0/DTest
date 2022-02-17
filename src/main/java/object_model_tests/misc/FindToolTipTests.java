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

import controls.Button;
import controls.Control;
import controls.InputGroup;
import object_models.left_menu.common.LeftMenu;
import object_models.modules.payroll.left_menu.employees.SalaryDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
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
		InputGroup grp =  (InputGroup) salDetails.getControl("group_1").get();
		
		grp
			.getElementByTitle("existing_records")
			.ifPresent(e -> {
				Control c = e.getControl();
				Button b = (Button) c;
				assertEquals("Grid View for existing records", b.getToolTipText());
			});		 	
	}	
	
	@AfterAll
	static void tearDown() {		
		homepagePayroll.close();
	}
}