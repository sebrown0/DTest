package object_model_tests.employee_creation;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import dto.Employee;
import enums.Gender;
import exceptions.NullDriverException;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.panels.employee_creation.EmployeeCreationWizard;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

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
		emp = 
				Employee.withRequiredFields()
				.firstName("Steve")
				.lastName("Brown")
				.idCardNumber("0144213A")
				.empCode("SB_101")
				.addressOne("1")
				.town("Attard")
				.country("MALTA")
				.gender(Gender.MALE)
				.dateOfBirth(Date.valueOf("2000-01-22"))
				.dateOfEmployement(Date.valueOf("2021-08-31"))
				.taxNumber("123456")
				.niNumber("123456")				
				.build();	
	}
	
	@AfterAll
	static void teardown() {
//		driver.quit();
	}
	
	@Test
	void enterFirstName() {
		wizard.createEmployee(emp);
	}
}
