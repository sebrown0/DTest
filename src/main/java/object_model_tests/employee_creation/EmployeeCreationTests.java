package object_model_tests.employee_creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.TextOut;
import dto.Employee;
import enums.control_names.EmployeeControlNames;
import logging.TestResultLogger;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.forms.FormFadeShow;
import object_models.modules.payroll.left_menu.employees.EmployeeDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCreation;
import object_models.top_right_nav_bar.common.NavBarElement;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.employee.EmployeeFromXml;
import providers.employee.EmployeeProvider;
import providers.employee.RandomEmployeeProvider;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeCreationTests {
	private static HomePage homepage;
	private static EmployeeCreationWizard wizard; 
	private static Employee emp;
	private static NavBarElement navEmpWizard;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the homepage
		homepage = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the employee creation wizard from the nav bar.
		navEmpWizard = homepage.getTopRightNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
	}	

	@Test @Order(1)
	void createDuplicateEmployee_codeInUseFormShouldBeShown() {
		// GET AN EMPLOYEE FROM XML
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getEmployeeWithRequiredFields("1");
		
		// GET THE EMPLOYEE WIZARD
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();
		
		// CHECK THAT THE WIZARD IS LOADED.
		assertTrue(wizard.getContextExpectedName().equals("Employee Creation Wizard"));
		
		// THE EMP EXISTS SO THE RESULT SHOULD BE AN ERROR FORM.
		FormFadeShow frm = wizard.createEmployeeAndGetConfirmation(emp);
		assertEquals("Data Error", frm.getTitle().getExpected());
		
		//CLOSE THE ERROR FORM AND WIZARD
		frm.close();
		wizard.close();		
	}

	@Test @Order(2)
	void createRandomEmployee_withLowerCaseCode_openEmpDetails_codeShouldBeInUpperCase() {
		// Get an employee with random code
		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getAnyEmpWithRandomCode();		
		String randomEmpCode = emp.getEmpCode();
		// Open the wizard and create the emp
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();		
		wizard.createEmployeeAndGetConfirmation(emp);
		wizard.close();
		// Open employee details and check the code.
		EmployeeDetails empDetails = (EmployeeDetails) homepage.getLeftMenu().clickAndLoad(EmployeeDetails.class).get();
		TextOut empDetailsCode = (TextOut) empDetails.getPanelControl().getControl(EmployeeControlNames.EMP_CODE).get();		
		assertFalse(empDetailsCode.getText().equals(randomEmpCode));
		assertTrue(empDetailsCode.getText().equalsIgnoreCase(randomEmpCode));
		empDetails.close();
	}

	@Test @Order(3)
	void createRandomEmployee_withSpaceInCode_openEmpDetails_spaceShouldBeRemoved() {
		// Get an employee with random code and add space.
		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getAnyEmpWithRandomCode();		
		String randomEmpCode = emp.getEmpCode();
		String randomEmpCodeWithSpace = randomEmpCode + " _space";
		emp.setEmpCode(randomEmpCodeWithSpace);
		// Open the wizard and create the emp
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();		
		wizard.createEmployeeAndGetConfirmation(emp);
		wizard.close();
		// Open employee details and check the code.
		EmployeeDetails empDetails = (EmployeeDetails) homepage.getLeftMenu().clickAndLoad(EmployeeDetails.class).get();
		TextOut empDetailsCode = (TextOut) empDetails.getPanelControl().getControl(EmployeeControlNames.EMP_CODE).get();
		assertFalse(empDetailsCode.getText().equals(randomEmpCode));
		assertTrue(empDetailsCode.getText().equalsIgnoreCase(randomEmpCode + "_space"));
		empDetails.close();
	}
	
	@Test @Order(4)
	void createRandomEmployee_withSymbolInCode_openEmpDetails_symbolsShouldBeRemoved() {
		// Get an employee with random code and add symbols.
		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
		emp = empProvider.getAnyEmpWithRandomCode();		
		String randomEmpCode = emp.getEmpCode();
		String randomEmpCodeWithSymbol = randomEmpCode + "_(M)";
		emp.setEmpCode(randomEmpCodeWithSymbol);
		// Open the wizard and create the emp
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();		
		wizard.createEmployeeAndGetConfirmation(emp);
		wizard.close();
		// Open employee details and check the code.
		EmployeeDetails empDetails = (EmployeeDetails) homepage.getLeftMenu().clickAndLoad(EmployeeDetails.class).get();
		TextOut empDetailsCode = (TextOut) empDetails.getPanelControl().getControl(EmployeeControlNames.EMP_CODE).get();
		assertFalse(empDetailsCode.getText().equals(randomEmpCode));
		assertEquals(randomEmpCode + "_M", empDetailsCode.getText());
		empDetails.close();
		/*
		 * LAST TEST CLOSE APP
		 */
		homepage.close();
	}
		
}
