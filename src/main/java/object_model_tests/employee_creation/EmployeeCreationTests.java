package object_model_tests.employee_creation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import library.common.forms.FormFadeShow;
import library.content.EmployeeContent;
import library.helpers.login.UserLoginPage;
import library.nav.NavBarElement;
import library.nav.nav_bar_elements.NavBarEmployeeCreation;
import library.object_models.modules.payroll.top_right_nav.employees.EmployeeCreation;
import library.pages.homepage.HomePage;
import library.providers.employee.EmployeeFromXml;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeCreationTests {
	private static HomePage homepage;
	private static EmployeeCreation empCreation;
	private static EmployeeContent emp;
	private static NavBarElement navEmpWizard;
	private static EmployeeFromXml empProvider;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the home page
		homepage = 
			userLogin.loginValidUser(UserProvider.userPortal());
		
		// Get the employee creation wizard from the nav bar.
		navEmpWizard = 
			homepage.getTopRightNavBarElement(
					NavBarEmployeeCreation.ORIGINAL_NAME).get();

		//load XML data.
		empProvider = 
				new EmployeeFromXml(XMLFileProvider.EMPLOYEE_TESTS_FILE_PATH);
	}	

	@Test @Order(1)
	void createDuplicateEmployee_codeInUseFormShouldBeShown() {
		// GET AN EMPLOYEE FROM XML				
		emp = empProvider.getFirstEmployeeWithCompleteContent();
		
		// GET THE EMPLOYEE WIZARD
		empCreation = (EmployeeCreation) navEmpWizard.clickElement();
		
		// CHECK THAT THE WIZARD IS LOADED.
		assertTrue(empCreation.getContextExpectedName().equals("Employee Creation Wizard"));
		
		// THE EMP EXISTS SO THE RESULT SHOULD BE AN ERROR FORM.
		FormFadeShow frm = empCreation.getEmployeeCreationWizard().createEmployeeAndGetConfirmation(emp);
		assertTrue(frm.getTitle().getActual().contains("Data Error"));
		
		//CLOSE THE ERROR FORM AND WIZARD
		frm.close();
		empCreation.close();		
	}
		
}
