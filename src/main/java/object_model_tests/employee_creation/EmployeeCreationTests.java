package object_model_tests.employee_creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import dto.Employee;
import logging.TestResultLogger;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.forms.FormFadeShow;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCreation;
import object_models.top_right_nav_bar.common.NavBarElement;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.EmployeeFromXml;
import providers.EmployeeProvider;
import providers.RandomEmployeeProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeCreationTests {
	private static HomePage homepagePayroll;
	private static EmployeeCreationWizard wizard; 
	private static Employee emp;
	private static NavBarElement navEmpWizard;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the employee creation wizard from the nav bar.
		navEmpWizard = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
	}	

	@Test @Order(1)
	void createDuplicateEmployee_codeInUseFormShouldBeShown() {
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getEmployeeRequired("1");
		
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();	
		// Check that we're on the wizard.
		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
		
		FormFadeShow frm = wizard.createEmployee(emp);
		assertEquals("Data Error", frm.getTitle().getExpected());
		frm.close();
		wizard.close();
	}

	@Test @Order(2)
	void createDupcateEmployee_codeInUseFormShouldBeShown() {
		System.out.println("--------------------------------------------"); // TODO - remove or log 	
		
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getEmployeeRequired("1");
		
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();	
		// Check that we're on the wizard.
		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
		
		FormFadeShow frm = wizard.createEmployee(emp);
		assertEquals("Data Error", frm.getTitle().getExpected());
		frm.close();
		wizard.close();
	}

	@Test @Order(3)
	void createDupEmployee_codeInUseFormShouldBeShown() {
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getEmployeeRequired("1");
		
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();	
		// Check that we're on the wizard.
		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
		
		FormFadeShow frm = wizard.createEmployee(emp);
		assertEquals("Data Error", frm.getTitle().getExpected());
		frm.close();
		wizard.close();
	}

	@Test @Order(4)
	void createDuployee_codeInUseFormShouldBeShown() {
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getEmployeeRequired("1");
		
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();	
		// Check that we're on the wizard.
		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
		
		FormFadeShow frm = wizard.createEmployee(emp);
		assertEquals("Data Error", frm.getTitle().getExpected());
		frm.close();
		wizard.close();
	}

//	@Test @Order(5)
//	void createDeEmployee_codeInUseFormShouldBeShown() {
//		EmployeeProvider empProvider = new EmployeeFromXml(); 
//		emp = empProvider.getEmployeeRequired("1");
//		
//		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();	
//		// Check that we're on the wizard.
//		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
//		
//		FormFadeShow frm = wizard.createEmployee(emp);
//		assertEquals("Data Error", frm.getTitle().getExpected());
//		frm.close();
//		wizard.close();
//	}
//
//	@Test @Order(6)
//	void cricateEmployee_codeInUseFormShouldBeShown() {
//		EmployeeProvider empProvider = new EmployeeFromXml(); 
//		emp = empProvider.getEmployeeRequired("1");
//		
//		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();	
//		// Check that we're on the wizard.
//		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
//		
//		FormFadeShow frm = wizard.createEmployee(emp);
//		assertEquals("Data Error", frm.getTitle().getExpected());
//		frm.close();
//		wizard.close();
//	}
	
//	----------------------------------------
	
//	@Test @Order(2)
//	void createRandomEmployee_withLowerCaseCode() {
//		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
//		emp = empProvider.getAnyEmpWithRandomCode();
//		String empCode = emp.getEmpCode();
//		System.out.println("->" + empCode); // TODO - remove or log 	
//		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();
//		
//		FormFadeShow frm = wizard.createEmployee(emp);
//	}
	
	@AfterAll
	static void teardown() {
//		homepagePayroll.close();
	}
	
}
