package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
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
import object_models.navigation.left_side_menu.LeftMenuPayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.panels.menu.absence_statistics.EmployeeAccruals;
import object_models.panels.menu.absence_statistics.OtherAbsenceStatistics;
import object_models.panels.menu.additional_hours.ApplyAdditionalHours;
import object_models.panels.menu.additional_hours.Authorisation;
import object_models.panels.menu.employee_others.AbsenceEntitlements;
import object_models.panels.menu.employee_others.AdvancesAndPayments;
import object_models.panels.menu.employee_others.Covid19Supplement;
import object_models.panels.menu.employee_others.Loans;
import object_models.panels.menu.employee_others.Pensions;
import object_models.panels.menu.employee_others.TaxArrears;
import object_models.panels.menu.employee_statistics.Fs3QuickView;
import object_models.panels.menu.employee_statistics.PayslipQuickView;
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
import object_models.panels.menu.parents.PayrollStatistics;
import object_models.panels.menu.payroll.CalculatePayroll;
import object_models.panels.menu.payroll.CalculationStatistics;
import object_models.panels.menu.payroll.DetailedAdjustments;
import object_models.panels.menu.payroll.ExcelPayrollUploads;
import object_models.panels.menu.payroll.GlobalAbsences;
import object_models.panels.menu.payroll.GlobalAdjustments;
import object_models.panels.menu.payroll.GlobalExtras;
import object_models.panels.menu.payroll.PayrollDetails;
import object_models.panels.menu.payroll.PayrollDetailsDrillDown;
import object_models.panels.menu.reports.AbsenceRelatedReports;
import object_models.panels.menu.reports.AdjustmentsReports;
import object_models.panels.menu.reports.ChequePrinting;
import object_models.panels.menu.reports.DirectCredits;
import object_models.panels.menu.reports.GlobalPayrollAnalysis;
import object_models.panels.menu.reports.HrRelatedReports;
import object_models.panels.menu.reports.PayrollReports;
import object_models.panels.menu.reports.Payslips;
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
		// Get the menu from home page.
		menu = hp.getLeftMenu();
	}
			
//	@Test
//	void checkEmployees() {
//		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
//		List<String> elementNames = elements.getEmployees();
//		checkList(elementNames);
//	}
	
	void checkList(List<String> elementNames) {
		int nameCount = 0;
		for (String name : elementNames) {
			ContainerAction empList = menu.load(name);
			PageTitle title = empList.getTitle();
			assertEquals(title.getExpected(), title.getActual());
			empList.closeElement();	
			System.out.println(name);
			nameCount++;
		}
		assertTrue(nameCount == elementNames.size());
	}
	
//	@Test
//	void checkAll() {
//		LeftMenuPayroll elements = (LeftMenuPayroll) menu.getElements();
//		List<List<String>> all = elements.getAll();
//		for (List<String> list : all) {
//			checkList(list);
//		}
//	}
	
	
	@Test
	void click_and_get_EmployeeList() {
		ContainerAction empList = menu.load(EmployeeList.MENU_TITLE);
		PageTitle title = empList.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		empList.closeElement();
	}
	
	@Test
	void click_and_get_Documents() {
		ContainerAction doc = menu.load(Documents.MENU_TITLE);
		PageTitle title = doc.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		doc.closeElement();
	}
	
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

	@Test
	void click_EmployeeStatistics_and_get_PayslipQuickView() {
		ContainerAction obj = loadAndCheckTitle("Employee Statistics", PayslipQuickView.MENU_TITLE);
		closePanelAndParent(obj, "Employee Statistics");
	}

	@Test
	void click_EmployeeStatistics_and_get_item() {
		ContainerAction obj = loadAndCheckTitle("Employee Statistics", Fs3QuickView.MENU_TITLE);
		closePanelAndParent(obj, "Employee Statistics");
	}

	@Test
	void click_and_get_PayrollStatistics() {
		ContainerAction obj = menu.load(PayrollStatistics.MENU_TITLE);
		PageTitle title = obj.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		obj.closeElement();
	}
	
	@Test
	void click_AbsenceStatistics_and_get_EmployeeAccruals() {
		ContainerAction obj = loadAndCheckTitle("Absence Statistics", EmployeeAccruals.MENU_TITLE);
		closePanelAndParent(obj, "Absence Statistics");
	}

	@Test
	void click_AbsenceStatistics_and_get_OtherAbsenceStatistics() {
		ContainerAction obj = loadAndCheckTitle("Absence Statistics", OtherAbsenceStatistics.MENU_TITLE);
		closePanelAndParent(obj, "Absence Statistics");
	}
	
	@Test
	void click_Reports_and_get_PayrollReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", PayrollReports.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}
	
	@Test
	void click_Reports_and_get_Payslips() {
		ContainerAction obj = loadAndCheckTitle("Reports", Payslips.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_DirectCredits() {
		ContainerAction obj = loadAndCheckTitle("Reports", DirectCredits.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_GlobalPayrollAnalysis() {
		ContainerAction obj = loadAndCheckTitle("Reports", GlobalPayrollAnalysis.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_ChequePrinting() {
		ContainerAction obj = loadAndCheckTitle("Reports", ChequePrinting.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_AdjustmentsReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", AdjustmentsReports.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_HrRelatedReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", HrRelatedReports.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_AbsenceRelatedReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", AbsenceRelatedReports.MENU_TITLE);
		closePanelAndParent(obj, "Reports");
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
