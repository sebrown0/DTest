package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.forms.ContainerAction;
import object_models.helpers.title.PageTitle;
import object_models.left_menu.absence_statistics.EmployeeAccruals;
import object_models.left_menu.absence_statistics.OtherAbsenceStatistics;
import object_models.left_menu.additional_hours.ApplyAdditionalHours;
import object_models.left_menu.additional_hours.Authorisation;
import object_models.left_menu.bulk_updates.ColaSalaryUpdates;
import object_models.left_menu.bulk_updates.EmployeeCreation;
import object_models.left_menu.common.LeftMenuActions;
import object_models.left_menu.employee_others.AbsenceEntitlements;
import object_models.left_menu.employee_others.AdvancesAndPayments;
import object_models.left_menu.employee_others.Covid19Supplement;
import object_models.left_menu.employee_others.Loans;
import object_models.left_menu.employee_others.Pensions;
import object_models.left_menu.employee_others.TaxArrears;
import object_models.left_menu.employee_statistics.Fs3QuickView;
import object_models.left_menu.employee_statistics.PayslipQuickView;
import object_models.left_menu.employees.Banks;
import object_models.left_menu.employees.CareerProgression;
import object_models.left_menu.employees.ContactNumbers;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.left_menu.employees.PermanentAllowances;
import object_models.left_menu.employees.PreviousEmployement;
import object_models.left_menu.employees.SalaryDetails;
import object_models.left_menu.employees.Schedule;
import object_models.left_menu.employees.Unions;
import object_models.left_menu.parents.Documents;
import object_models.left_menu.parents.EmployeeList;
import object_models.left_menu.parents.MonthlyReports;
import object_models.left_menu.parents.PayrollStatistics;
import object_models.left_menu.parents.SettingsPayroll;
import object_models.left_menu.parents.YearlyReports;
import object_models.left_menu.payroll.CalculatePayroll;
import object_models.left_menu.payroll.CalculationStatistics;
import object_models.left_menu.payroll.CloseAndLockPayroll;
import object_models.left_menu.payroll.DetailedAdjustments;
import object_models.left_menu.payroll.ExcelPayrollUploads;
import object_models.left_menu.payroll.GlobalAbsences;
import object_models.left_menu.payroll.GlobalAdjustments;
import object_models.left_menu.payroll.GlobalExtras;
import object_models.left_menu.payroll.InitialisePayroll;
import object_models.left_menu.payroll.PayrollDetails;
import object_models.left_menu.payroll.PayrollDetailsDrillDown;
import object_models.left_menu.reports.AbsenceRelatedReports;
import object_models.left_menu.reports.AdjustmentsReports;
import object_models.left_menu.reports.ChequePrinting;
import object_models.left_menu.reports.DirectCredits;
import object_models.left_menu.reports.GlobalPayrollAnalysis;
import object_models.left_menu.reports.HrRelatedReports;
import object_models.left_menu.reports.PayrollReports;
import object_models.left_menu.reports.Payslips;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;
/**
 * @author Steve Brown
 * 
 * Check that clicking an element of the 
 * left menu takes the user to the correct place.
 * 
 * This could either be expanding the menu to show a sub-menu,
 * i.e. Reports, or opening a new page, 
 * i.e. Reports -> Payroll Reports
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class LeftMenuChildren_Payroll_Tests {	
	private static WebDriver driver;	
	private static LeftMenuActions menu;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		driver = hp.getWebDriver();
		menu = hp.getLeftMenu();
	}
				
	@Test
	void click_and_get_EmployeeList() {
		ContainerAction empList = menu.clickAndLoad(EmployeeList.MENU_TITLE).get();
		PageTitle title = empList.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		empList.closeElement();
	}
	
	@Test
	void click_and_get_Documents() {
		ContainerAction doc = menu.clickAndLoad(Documents.MENU_TITLE).get();
		PageTitle title = doc.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		doc.closeElement();
	}
	
	@Test
	void click_Employees_and_get_EmployeeDetails() {
		ContainerAction obj = loadAndCheckTitle("Employees", EmployeeDetails.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");
	}
	
	@Test
	void click_Employees_and_get_ContactNumbers() {
		ContainerAction obj = loadAndCheckTitle("Employees", ContactNumbers.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Banks() {
		ContainerAction obj = loadAndCheckTitle("Employees", Banks.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_SalaryDetails() {
		ContainerAction obj = loadAndCheckTitle("Employees", SalaryDetails.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_CareerProgression() {
		ContainerAction obj = loadAndCheckTitle("Employees", CareerProgression.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Schedule() {
		ContainerAction obj = loadAndCheckTitle("Employees", Schedule.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_PermanentAllowances() {
		ContainerAction obj = loadAndCheckTitle("Employees", PermanentAllowances.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");
	}
	
	@Test
	void click_Employees_and_get_PreviousEmployement() {
		ContainerAction obj = loadAndCheckTitle("Employees", PreviousEmployement.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Unions() {
		ContainerAction obj = loadAndCheckTitle("Employees", Unions.MENU_TITLE).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_AbsenceEntitlements() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", AbsenceEntitlements.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_AdvancesAndPayments() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", AdvancesAndPayments.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Others");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_TaxArrears() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", TaxArrears.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_Loans() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", Loans.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Others");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_Pensions() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", Pensions.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_Covid19Supplement() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", Covid19Supplement.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_AdditionalHours_and_get_ApplyAdditionalHours() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", ApplyAdditionalHours.MENU_TITLE).get();
		closePanelAndParent(obj, "Additional Hours");
	}
	
	@Test
	void click_AdditionalHours_and_get_Authorisation() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", Authorisation.MENU_TITLE).get();
		closePanelAndParent(obj, "Additional Hours");
	}
	
	@Test
	void click_Payroll_and_get_InitialisePayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", InitialisePayroll.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");		
	}
	
	@Test
	void click_Payroll_and_get_PayrollDetailsDrillDown() {
		ContainerAction obj = loadAndCheckTitle("Payroll", PayrollDetailsDrillDown.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_DetailedAdjustments() {
		ContainerAction obj = loadAndCheckTitle("Payroll", DetailedAdjustments.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_GlobalAdjustments() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalAdjustments.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}
	
	@Test
	void click_Payroll_and_get_GlobalAbsences() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalAbsences.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_GlobalExtras() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalExtras.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CalculatePayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CalculatePayroll.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CloseAndLockPayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CloseAndLockPayroll.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_PayrollDetails() {
		ContainerAction obj = loadAndCheckTitle("Payroll", PayrollDetails.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_ExcelPayrollUploads() {
		ContainerAction obj = loadAndCheckTitle("Payroll", ExcelPayrollUploads.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CalculationStatistics() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CalculationStatistics.MENU_TITLE).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_EmployeeStatistics_and_get_PayslipQuickView() {
		ContainerAction obj = loadAndCheckTitle("Employee Statistics", PayslipQuickView.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Statistics");
	}

	@Test
	void click_EmployeeStatistics_and_get_item() {
		ContainerAction obj = loadAndCheckTitle("Employee Statistics", Fs3QuickView.MENU_TITLE).get();
		closePanelAndParent(obj, "Employee Statistics");
	}

	@Test
	void click_and_get_PayrollStatistics() {
		ContainerAction obj = menu.clickAndLoad(PayrollStatistics.MENU_TITLE).get();
		PageTitle title = obj.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		obj.closeElement();
	}
	
	@Test
	void click_AbsenceStatistics_and_get_EmployeeAccruals() {
		ContainerAction obj = loadAndCheckTitle("Absence Statistics", EmployeeAccruals.MENU_TITLE).get();
		closePanelAndParent(obj, "Absence Statistics");
	}

	@Test
	void click_AbsenceStatistics_and_get_OtherAbsenceStatistics() {
		ContainerAction obj = loadAndCheckTitle("Absence Statistics", OtherAbsenceStatistics.MENU_TITLE).get();
		closePanelAndParent(obj, "Absence Statistics");
	}
	
	@Test
	void click_Reports_and_get_PayrollReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", PayrollReports.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}
	
	@Test
	void click_Reports_and_get_Payslips() {
		ContainerAction obj = loadAndCheckTitle("Reports", Payslips.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_DirectCredits() {
		ContainerAction obj = loadAndCheckTitle("Reports", DirectCredits.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_GlobalPayrollAnalysis() {
		ContainerAction obj = loadAndCheckTitle("Reports", GlobalPayrollAnalysis.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_ChequePrinting() {
		ContainerAction obj = loadAndCheckTitle("Reports", ChequePrinting.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_AdjustmentsReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", AdjustmentsReports.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_HrRelatedReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", HrRelatedReports.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_AbsenceRelatedReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", AbsenceRelatedReports.MENU_TITLE).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void load_and_check_MonthlyReports() {
		ContainerAction obj = loadAndCheckTitle(MonthlyReports.MENU_TITLE).get();
		closePanel(obj);
	}

	@Test
	void load_and_check_YearlyReports() {
		ContainerAction obj = loadAndCheckTitle(YearlyReports.MENU_TITLE).get();
		closePanel(obj);
	}
	
	@Test
	void click_BulkUpdates_and_get_ColaSalaryUpdates() {
		ContainerAction obj = loadAndCheckTitle("Bulk Updates", ColaSalaryUpdates.MENU_TITLE).get();
		closePanelAndParent(obj, "Bulk Updates");
	}
	
	@Test
	void click_BulkUpdates_and_get_EmployeeCreation() {
		ContainerAction obj = loadAndCheckTitle("Bulk Updates", EmployeeCreation.MENU_TITLE).get();
		closePanelAndParent(obj, "Bulk Updates");
	}

	@Test
	void load_and_check_PayrollSettings() {
		ContainerAction obj = loadAndCheckTitle(SettingsPayroll.MENU_TITLE).get();
		closePanel(obj);
	}
	
	@AfterAll
	static void tearDown() {
		driver.quit();
	}
	
	/* 
	 * Helpers below
	 */
	private Optional<ContainerAction> loadAndCheckTitle(String prntName, String menuTitle) {
		Optional<ContainerAction> obj = menu.clickParent(prntName).clickAndLoad(menuTitle);
		PageTitle title = obj.get().getTitle();
		assertEquals(title.getExpected(), title.getActual());
		return obj;
	}
	
	private Optional<ContainerAction> loadAndCheckTitle(String menuTitle) {
		Optional<ContainerAction> obj = menu.clickAndLoad(menuTitle);
		PageTitle title = obj.get().getTitle();
		assertEquals(title.getExpected(), title.getActual());
		return obj;
	}
		
	private void closePanelAndParent(ContainerAction closer, String prntName) {
		closeElement(closer);
		closeParent(prntName);
	}
	
	private void closePanel(ContainerAction closer) {
		closeElement(closer);		
	}
	
	private void closeElement(ContainerAction closer) {
		closer.closeElement();
	}
	
	private void closeParent(String prntName) {
		menu.clickParent(prntName);
	}
		
}