package object_model_tests.employee_creation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeCreationTests {
	private static HomePage homepagePayroll;
	private static EmployeeCreationWizard wizard; 
	private static Employee emp;
	private static EmployeeProvider empProvider; 
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the employee creation wizard from the nav bar.
		NavBarElement navEmpWizard = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
		// Open the wizard.
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();
		// Get the employee we're going to use with required fields from the provider.
		empProvider = new EmployeeFromXml();
//		emp = empProvider.getEmployeeRequired("1");
	}

	@Test
	void createDuplicateEmployee_codeInUseFormShouldBeShown() {
		emp = empProvider.getEmployeeRequired("1");
		FormFadeShow frm = wizard.createEmployee(emp);
//		PageTitle frmTitle = frm.getTitle();
//		System.out.println("t->" + frmTitle.getActual()); // TODO - remove or log 	
		frm.close();
	}
	
//	@Test
//	void createDuplicateEmployee_codeInUseFormShouldBeShown() {
//		emp = empProvider.getEmployeeRequired("1");
//		WebDriverWait wait = new WebDriverWait(homepagePayroll.getWebDriver(), Duration.ofSeconds(3));
//		WebElement frm = null;
//
//		wizard.createEmployee(emp);		
//		try {
//			System.out.println("Loading emp 1"); // TODO - remove or log 	
//			frm =	wait.until(
//							ExpectedConditions.visibilityOfElementLocated(
//									By.cssSelector("div[class='modal fade show']")));	
//		} catch (TimeoutException e) {
//			// Error form not there so load the emp again (duplicate)
//			System.out.println("Loading emp 2"); // TODO - remove or log
//			wizard.createEmployee(emp);
//			frm =	wait.until(
//					ExpectedConditions.visibilityOfElementLocated(
//							By.cssSelector("div[class='modal fade show']")));
//		}
//				
//		if(frm == null) {
//			fail("Duplicate code form NOT displayed");
//		}else {
//			WebElement title = frm.findElement(By.className("modal-body"));
//			WebElement btn = frm.findElement(By.className("close"));
//			String formTitle = title.getText();
//			System.out.println("Title ->" + formTitle); // TODO - remove or log
//			System.out.println("->" + btn.getAttribute("class")); // TODO - remove or log 	
//			btn.click(); // should be a form object
////			wizard.close();
//			
//			assertEquals("Employee Code [" + emp.getEmpCode() + "] is already in use!", formTitle);
//		}		 	
//	}
	
	@AfterAll
	static void teardown() {
//		homepagePayroll.close();
	}
	
}
