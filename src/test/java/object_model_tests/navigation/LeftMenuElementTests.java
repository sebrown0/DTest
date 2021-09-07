package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import listeners.TestResultLogger;
import object_models.forms.ContainerAction;
import object_models.forms.menu.payroll.CloseAndLockPayroll;
import object_models.forms.menu.payroll.InitialisePayroll;
import object_models.helpers.title.PageTitle;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.panels.menu.additional_hours.ApplyAdditionalHours;
import object_models.panels.menu.additional_hours.Authorisation;
import object_models.panels.menu.employee_others.AbsenceEntitlements;
import object_models.panels.menu.employee_others.AdvancesAndPayments;
import object_models.panels.menu.employee_others.Covid19Supplement;
import object_models.panels.menu.employee_others.Loans;
import object_models.panels.menu.employee_others.Pensions;
import object_models.panels.menu.employee_others.TaxArrears;
import object_models.panels.menu.employees.Banks;
import object_models.panels.menu.employees.CareerProgression;
import object_models.panels.menu.employees.ContactNumbers;
import object_models.panels.menu.employees.EmployeeDetails;
import object_models.panels.menu.employees.PermanentAllowances;
import object_models.panels.menu.employees.PreviousEmployement;
import object_models.panels.menu.employees.SalaryDetails;
import object_models.panels.menu.employees.Schedule;
import object_models.panels.menu.employees.Unions;
import object_models.panels.menu.parents.Documents;
import object_models.panels.menu.parents.EmployeeList;
import object_models.panels.menu.payroll.CalculatePayroll;
import object_models.panels.menu.payroll.CalculationStatistics;
import object_models.panels.menu.payroll.DetailedAdjustments;
import object_models.panels.menu.payroll.ExcelPayrollUploads;
import object_models.panels.menu.payroll.GlobalAbsences;
import object_models.panels.menu.payroll.GlobalAdjustments;
import object_models.panels.menu.payroll.GlobalExtras;
import object_models.panels.menu.payroll.PayrollDetails;
import object_models.panels.menu.payroll.PayrollDetailsDrillDown;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

@ExtendWith(TestResultLogger.class)
class LeftMenuElementTests {	
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	private static LeftMenu menu;
	
	@BeforeAll	
	static void setup() throws NullDriverException, InterruptedException, ExecutionException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Login.
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		// Get the menu from homepage.
		menu = hp.getLeftMenu();
	}
			
//	@Test
//	void click_and_get_EmployeeList() {
//		ContainerAction empList = menu.load(EmployeeList.MENU_TITLE);
//		PageTitle title = empList.getTitle();
//		assertEquals(title.getExpected(), title.getActual());
//		empList.closeElement();
//	}
//	
//	@Test
//	void click_and_get_Documents() {
//		ContainerAction doc = menu.load(Documents.MENU_TITLE);
//		PageTitle title = doc.getTitle();
//		assertEquals(title.getExpected(), title.getActual());
//		doc.closeElement();
//	}
	
	@Test
	void click_Employees_and_get_EmployeeDetails() {
		ContainerAction obj = loadAndCheckTitle("Employees", EmployeeDetails.MENU_TITLE);
		closePanelAndParent(obj, "Employees");
	}
	
	@Test
	void click_Employees_and_get_ContactNumbers() {
		ContainerAction obj = loadAndCheckTitle("Employees", ContactNumbers.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Banks() {
		ContainerAction obj = loadAndCheckTitle("Employees", Banks.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_SalaryDetails() {
		ContainerAction obj = loadAndCheckTitle("Employees", SalaryDetails.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_CareerProgression() {
		ContainerAction obj = loadAndCheckTitle("Employees", CareerProgression.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Schedule() {
		ContainerAction obj = loadAndCheckTitle("Employees", Schedule.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_PermanentAllowances() {
		ContainerAction obj = loadAndCheckTitle("Employees", PermanentAllowances.MENU_TITLE);
		closePanelAndParent(obj, "Employees");
	}
	
	@Test
	void click_Employees_and_get_PreviousEmployement() {
		ContainerAction obj = loadAndCheckTitle("Employees", PreviousEmployement.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Unions() {
		ContainerAction obj = loadAndCheckTitle("Employees", Unions.MENU_TITLE);
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_AbsenceEntitlements() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", AbsenceEntitlements.MENU_TITLE);
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_AdvancesAndPayments() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", AdvancesAndPayments.MENU_TITLE);
		closePanelAndParent(obj, "Employee Others");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_TaxArrears() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", TaxArrears.MENU_TITLE);
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_Loans() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", Loans.MENU_TITLE);
		closePanelAndParent(obj, "Employee Others");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_Pensions() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", Pensions.MENU_TITLE);
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_Covid19Supplement() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", Covid19Supplement.MENU_TITLE);
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_AdditionalHours_and_get_ApplyAdditionalHours() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", ApplyAdditionalHours.MENU_TITLE);
		closePanelAndParent(obj, "Additional Hours");
	}
	
	@Test
	void click_AdditionalHours_and_get_Authorisation() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", Authorisation.MENU_TITLE);
		closePanelAndParent(obj, "Additional Hours");
	}
	
	@Test
	void click_Payroll_and_get_InitialisePayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", InitialisePayroll.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");		
	}
	
	@Test
	void click_Payroll_and_get_PayrollDetailsDrillDown() {
		ContainerAction obj = loadAndCheckTitle("Payroll", PayrollDetailsDrillDown.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_DetailedAdjustments() {
		ContainerAction obj = loadAndCheckTitle("Payroll", DetailedAdjustments.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_GlobalAdjustments() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalAdjustments.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}
	
	@Test
	void click_Payroll_and_get_GlobalAbsences() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalAbsences.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_GlobalExtras() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalExtras.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CalculatePayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CalculatePayroll.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CloseAndLockPayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CloseAndLockPayroll.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_PayrollDetails() {
		ContainerAction obj = loadAndCheckTitle("Payroll", PayrollDetails.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_ExcelPayrollUploads() {
		ContainerAction obj = loadAndCheckTitle("Payroll", ExcelPayrollUploads.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CalculationStatistics() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CalculationStatistics.MENU_TITLE);
		closePanelAndParent(obj, "Payroll");
	}


	@AfterAll
	static void tearDown() {
		driver.quit();
	}
	
	/* 
	 * Helpers below
	 */
	private ContainerAction loadAndCheckTitle(String prntName, String menuTitle) {
		ContainerAction obj = menu.clickParent(prntName).load(menuTitle);
		PageTitle title = obj.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		return obj;
	}
	
	private void closePanelAndParent(ContainerAction closer, String prntName) {
		closeElement(closer);
		closeParent(prntName);
	}
	
	private void closeElement(ContainerAction closer) {
		closer.closeElement();
	}
	
	private void closeParent(String prntName) {
		menu.clickParent(prntName);
	}
		
}
