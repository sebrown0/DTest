package object_model_tests.employee_creation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import dto.Employee;
import enums.Gender;
import exceptions.NullDriverException;
import listeners.TestResultLogger;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.NavBarElement;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.panels.EmployeeDetails;
import object_models.panels.employee_creation.EmployeeCreationWizard;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

@ExtendWith(TestResultLogger.class)
class EmployeeCreationTests {
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	private static NavBarElement navEmpWizard;
	private static EmployeeCreationWizard wizard; 
	private static Employee emp;
	
	@BeforeAll	
	static void setup() throws NullDriverException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the employee creation wizard from the nav bar.
		navEmpWizard = hp.getTopRightNavBar().getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
		// Open the wizard.
		wizard = (EmployeeCreationWizard) navEmpWizard.clickElement();
		// Create the employee we're going to use with required fields.
		emp = Employee
				.withRequiredFields()
				.firstName("Steve")
				.lastName("Brown")
				.idCardNumber("0144213A")
				.empCode("SB_101")
				.addressOne("1")
				.town("Attard")
				.country("MALTA")
				.gender(Gender.MALE)
				.dateOfBirth("21/05/2002")
				.dateOfEmployement("01/01/2021")
				.taxNumber("123456")
				.niNumber("123456")	
				.payGroup("Fourweekly")
				.annualSalary(BigDecimal.valueOf(10000))								
				.hourlyRate(BigDecimal.valueOf(10))
				.build();	
	}
	
	@AfterAll
	static void teardown() {
//		driver.quit();
	}
	
	@Test
	void createEmployee() {
		LeftMenu menu = hp.getLeftMenu();
		wizard.createEmployee(emp);
		EmployeeDetails empDetails = (EmployeeDetails) menu.clickParent("Employees").clickChild("Employee Details");
		assertEquals(empDetails.employeeCode().getTextByValue().length(), emp.getEmpCode());
		empDetails.closePanel();
	}
}
