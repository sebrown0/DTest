/**
 * 
 */
package object_model_tests.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import context_manager.ContextManager;
import controls.PageControl;
import dto.Employee;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GroupControlNames;
import logging.TestResultLogger;
import object_models.controls.DropdownCombo;
import object_models.controls.EmployeeSelection;
import object_models.element.TextOut;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import providers.EmployeeFromXml;
import providers.EmployeeProvider;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Verify and employee loaded by EmployeeProvider.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeDetailsTests {
	private static HomePage homepagePayroll;	
	private static LeftMenu menu;
	private static EmployeeDetails empDetails;
	private static Employee emp;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the homepage
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		// Load the menu.
		menu = homepagePayroll.getLeftMenu();
		// Load the employee details page.
		empDetails = (EmployeeDetails) menu.clickParent("Employees").clickAndLoad(EmployeeDetails.class).get();
		// Get the employee we're going to use with required fields from the provider.
		EmployeeProvider empProvider = new EmployeeFromXml();
		emp = empProvider.getEmployeeRequired("1");
	}
		
	/*
	 * 
	 * REMOVE CONTROLS AS IN OWN TEST
	 * 
	 * 
	 */
	// Controls - Start
	@Test @Order(1)
	void selectEmployee() {
		PageControl control = empDetails.getPanelControl();
		EmployeeSelection empSelection = (EmployeeSelection) control.getControl(GroupControlNames.SELECT_EMP).get();
		empSelection.clickRow("3");		
		assertEquals("0134213A", empDetails.tab().basicDetails().iDCardNumber().getTextByValue());				
	}	
	@Test @Order(2)
	void checkCode() {
		PageControl control = empDetails.getPanelControl();
		TextOut textOut = (TextOut) control.getControl(EmployeeControlNames.EMP_CODE).get();
		assertEquals(emp.getEmpCode(), textOut.getTextByValue());		
	}	
	@Test @Order(3)
	void checkEmployeeName() {
		PageControl control = empDetails.getPanelControl();
		TextOut textOut = (TextOut) control.getControl(EmployeeControlNames.EMP_NAME).get();
		assertEquals(emp.getFullName(), textOut.getTextByValue().trim());
	}
	@Test @Order(4)
	void openCombos() {		
		PageControl control = empDetails.getPanelControl();
		DropdownCombo combos = (DropdownCombo) control.getControl(GroupControlNames.COMBOS).get();		
		combos.close(); 
		ContextManager cm = combos.getContextManager();
		assertEquals(cm.getCurrentContext().getContextId().getExpectedName(), "Employee Details");
	}
	
//	@Test //
//	void openCombos() {
//		
//		WebDriver driver = homepagePayroll.getWebDriver();
//		driver.switchTo().defaultContent();
////		System.out.println("c1->" + driver.findElements(By.tagName("iframe")).size());
//		menu.clickParent("Employees").clickAndLoad(ContactNumbers.class).get();
//		driver.switchTo().defaultContent();
////		System.out.println("c2->" + driver.findElements(By.tagName("iframe")).size());
//		WebElement p = driver.findElement(By.id("jsPanel-2"));
//		WebElement h = p.findElement(By.cssSelector("div[class='jsPanel-titlebar']"));
//		System.out.println("c3->");
//		
//		driver.findElement(By.id(empDetails.getPanelId().get()));
//		PageControl control = empDetails.getEmployeeControl();
//		DropdownCombo combos = (DropdownCombo) control.getControl(EmployeeControlNames.COMBOS).get();		
////		combos.close(); // not a panel!!!
//	}
	
//	@Test
//	void openGrid() {
//		PageControl control = empDetails.getEmployeeControl();		
//		DkGridEmployeeDetails grid = (DkGridEmployeeDetails) control.getControl(EmployeeControlNames.GRID_VIEW).get();
//		grid.close();
//	}
//	// Controls - End
//	
//	@Test
//	void checkIdCardNo() {		
//		assertEquals(emp.getIdCardNumber(), empDetails.tab().basicDetails().iDCardNumber().getTextByValue());
//	}
//	
//	@Test
//	void set_and_check_PartTimerHoursPerDay() {
//		TextInOut partTimerHoursPerDay = empDetails.tab().settings().partTimerHoursPerDay();		
//		partTimerHoursPerDay.clear();
//		partTimerHoursPerDay.writeText("5");
//		assertEquals("5", partTimerHoursPerDay.getTextByValue());
//	}
		
	@AfterAll
	static void tearDown() {		
//		homepagePayroll.close();
	}
}
