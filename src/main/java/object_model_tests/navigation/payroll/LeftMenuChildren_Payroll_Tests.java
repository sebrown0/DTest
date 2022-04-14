package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import library.common.helpers.title.PageTitle;
import library.dakar_hr.left_menu.LeftMenu;
import logging.TestResultLogger;
import object_models.forms.ContainerAction;
import object_models.left_menu.additional_hours.ApplyAdditionalHours;
import object_models.left_menu.additional_hours.Authorisation;
import object_models.modules.common.EmployeeList;
import object_models.modules.payroll.left_menu.Documents;
import object_models.modules.payroll.left_menu.MonthlyReports;
import object_models.modules.payroll.left_menu.PayrollStatistics;
import object_models.modules.payroll.left_menu.SettingsPayroll;
import object_models.modules.payroll.left_menu.YearlyReports;
import object_models.modules.payroll.left_menu.absence_statistics.EmployeeAccruals;
import object_models.modules.payroll.left_menu.absence_statistics.OtherAbsenceStatistics;
import object_models.modules.payroll.left_menu.bulk_updates.ColaSalaryUpdates;
import object_models.modules.payroll.left_menu.bulk_updates.EmployeeCreation;
import object_models.modules.payroll.left_menu.employee_others.AbsenceEntitlements;
import object_models.modules.payroll.left_menu.employee_others.AdvancesAndPayments;
import object_models.modules.payroll.left_menu.employee_others.Covid19Supplement;
import object_models.modules.payroll.left_menu.employee_others.Loans;
import object_models.modules.payroll.left_menu.employee_others.Pensions;
import object_models.modules.payroll.left_menu.employee_others.TaxArrears;
import object_models.modules.payroll.left_menu.employee_statistics.Fs3QuickView;
import object_models.modules.payroll.left_menu.employee_statistics.PayslipQuickView;
import object_models.modules.payroll.left_menu.employees.Banks;
import object_models.modules.payroll.left_menu.employees.CareerProgression;
import object_models.modules.payroll.left_menu.employees.ContactNumbers;
import object_models.modules.payroll.left_menu.employees.EmployeeDetails;
import object_models.modules.payroll.left_menu.employees.PermanentAllowances;
import object_models.modules.payroll.left_menu.employees.PreviousEmployement;
import object_models.modules.payroll.left_menu.employees.SalaryDetails;
import object_models.modules.payroll.left_menu.employees.Schedule;
import object_models.modules.payroll.left_menu.employees.Unions;
import object_models.modules.payroll.left_menu.payroll.CalculatePayroll;
import object_models.modules.payroll.left_menu.payroll.CalculationStatistics;
import object_models.modules.payroll.left_menu.payroll.CloseAndLockPayroll;
import object_models.modules.payroll.left_menu.payroll.DetailedAdjustments;
import object_models.modules.payroll.left_menu.payroll.ExcelPayrollUploads;
import object_models.modules.payroll.left_menu.payroll.GlobalAbsences;
import object_models.modules.payroll.left_menu.payroll.GlobalAdjustments;
import object_models.modules.payroll.left_menu.payroll.GlobalExtras;
import object_models.modules.payroll.left_menu.payroll.PayrollDetails;
import object_models.modules.payroll.left_menu.payroll.PayrollDetailsDrillDown;
import object_models.modules.payroll.left_menu.payroll.initialise.InitialisePayroll;
import object_models.modules.payroll.left_menu.reports.AbsenceRelatedReports;
import object_models.modules.payroll.left_menu.reports.AdjustmentsReports;
import object_models.modules.payroll.left_menu.reports.ChequePrinting;
import object_models.modules.payroll.left_menu.reports.DirectCredits;
import object_models.modules.payroll.left_menu.reports.GlobalPayrollAnalysis;
import object_models.modules.payroll.left_menu.reports.HrRelatedReports;
import object_models.modules.payroll.left_menu.reports.PayrollReports;
import object_models.modules.payroll.left_menu.reports.Payslips;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePagePayroll;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;
/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0 
 * 
 * Check that clicking an element of the 
 * left menu takes the user to the correct place.
 * 
 * This could either be expanding the menu to show a sub-menu,
 * i.e. Reports, or opening a new page, 
 * i.e. Reports -> Payroll Reports
 * 
 * TODO
 * ----
 * Some tests fail randomly.
 * Probably because we're in the wrong state.
 * 
 * For the future if a test fails as part of the test suite
 * add it to a list of tests to be run in isolation???
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class LeftMenuChildren_Payroll_Tests {
	private static LeftMenu menu;
	private static HomePagePayroll homepagePayroll;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());
		menu = homepagePayroll.getLeftMenu();
	}
		
	@Test
	void click_and_get_EmployeeList() {
		ContainerAction empList = menu.clickAndLoad(EmployeeList.class).get();
		PageTitle title = empList.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		empList.close();
	}
	
	@Test
	void click_and_get_Documents() {
		ContainerAction doc = menu.clickAndLoad(Documents.class).get();
		PageTitle title = doc.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		doc.close();
	}
	
	@Test
	void click_Employees_and_get_EmployeeDetails() {
		ContainerAction obj = loadAndCheckTitle("Employees", EmployeeDetails.class).get();		
		closePanelAndParent(obj, "Employees");
	}
	
	@Test
	void click_Employees_and_get_ContactNumbers() {
		ContainerAction obj = loadAndCheckTitle("Employees", ContactNumbers.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Banks() {
		ContainerAction obj = loadAndCheckTitle("Employees", Banks.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_SalaryDetails() {
		ContainerAction obj = loadAndCheckTitle("Employees", SalaryDetails.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_CareerProgression() {
		ContainerAction obj = loadAndCheckTitle("Employees", CareerProgression.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Schedule() {
		ContainerAction obj = loadAndCheckTitle("Employees", Schedule.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_PermanentAllowances() {
		ContainerAction obj = loadAndCheckTitle("Employees", PermanentAllowances.class).get();
		closePanelAndParent(obj, "Employees");
	}
	
	@Test
	void click_Employees_and_get_PreviousEmployement() {
		ContainerAction obj = loadAndCheckTitle("Employees", PreviousEmployement.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_Employees_and_get_Unions() {
		ContainerAction obj = loadAndCheckTitle("Employees", Unions.class).get();
		closePanelAndParent(obj, "Employees");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_AbsenceEntitlements() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", AbsenceEntitlements.class).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_AdvancesAndPayments() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", AdvancesAndPayments.class).get();
		closePanelAndParent(obj, "Employee Others");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_TaxArrears() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", TaxArrears.class).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_Loans() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", Loans.class).get();
		closePanelAndParent(obj, "Employee Others");		
	}
	
	@Test
	void click_EmployeeOthers_and_get_Pensions() {
		ContainerAction obj = loadAndCheckTitle("Employee Others", Pensions.class).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_EmployeeOthers_and_get_Covid19Supplement() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", Covid19Supplement.class).get();
		closePanelAndParent(obj, "Employee Others");
	}
	
	@Test
	void click_AdditionalHours_and_get_ApplyAdditionalHours() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", ApplyAdditionalHours.class).get();
		closePanelAndParent(obj, "Additional Hours");
	}
	
	@Test
	void click_AdditionalHours_and_get_Authorisation() {
		ContainerAction obj = loadAndCheckTitle("Additional Hours", Authorisation.class).get();
		closePanelAndParent(obj, "Additional Hours");
	}
	
	@Test
	void click_Payroll_and_get_InitialisePayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", InitialisePayroll.class).get();
		closePanelAndParent(obj, "Payroll");		
	}
	
	@Test
	void click_Payroll_and_get_PayrollDetailsDrillDown() {
		ContainerAction obj = loadAndCheckTitle("Payroll", PayrollDetailsDrillDown.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_DetailedAdjustments() {
		ContainerAction obj = loadAndCheckTitle("Payroll", DetailedAdjustments.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_GlobalAdjustments() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalAdjustments.class).get();
		closePanelAndParent(obj, "Payroll");
	}
	
	@Test
	void click_Payroll_and_get_GlobalAbsences() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalAbsences.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_GlobalExtras() {
		ContainerAction obj = loadAndCheckTitle("Payroll", GlobalExtras.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CalculatePayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CalculatePayroll.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CloseAndLockPayroll() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CloseAndLockPayroll.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_PayrollDetails() {
		ContainerAction obj = loadAndCheckTitle("Payroll", PayrollDetails.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_ExcelPayrollUploads() {
		ContainerAction obj = loadAndCheckTitle("Payroll", ExcelPayrollUploads.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_Payroll_and_get_CalculationStatistics() {
		ContainerAction obj = loadAndCheckTitle("Payroll", CalculationStatistics.class).get();
		closePanelAndParent(obj, "Payroll");
	}

	@Test
	void click_EmployeeStatistics_and_get_PayslipQuickView() {
		ContainerAction obj = loadAndCheckTitle("Employee Statistics", PayslipQuickView.class).get();
		closePanelAndParent(obj, "Employee Statistics");
	}

	@Test
	void click_EmployeeStatistics_and_get_item() {
		ContainerAction obj = loadAndCheckTitle("Employee Statistics", Fs3QuickView.class).get();
		closePanelAndParent(obj, "Employee Statistics");
	}

	@Test
	void click_and_get_PayrollStatistics() {
		ContainerAction obj = menu.clickAndLoad(PayrollStatistics.class).get();
		PageTitle title = obj.getTitle();
		assertEquals(title.getExpected(), title.getActual());
		obj.close();
	}
	
	@Test
	void click_AbsenceStatistics_and_get_EmployeeAccruals() {
		ContainerAction obj = loadAndCheckTitle("Absence Statistics", EmployeeAccruals.class).get();
		closePanelAndParent(obj, "Absence Statistics");
	}

	@Test
	void click_AbsenceStatistics_and_get_OtherAbsenceStatistics() {
		ContainerAction obj = loadAndCheckTitle("Absence Statistics", OtherAbsenceStatistics.class).get();
		closePanelAndParent(obj, "Absence Statistics");
	}
	
	@Test
	void click_Reports_and_get_PayrollReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", PayrollReports.class).get();
		closePanelAndParent(obj, "Reports");
	}
	
	@Test
	void click_Reports_and_get_Payslips() {
		ContainerAction obj = loadAndCheckTitle("Reports", Payslips.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_DirectCredits() {
		ContainerAction obj = loadAndCheckTitle("Reports", DirectCredits.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_GlobalPayrollAnalysis() {
		ContainerAction obj = loadAndCheckTitle("Reports", GlobalPayrollAnalysis.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_ChequePrinting() {
		ContainerAction obj = loadAndCheckTitle("Reports", ChequePrinting.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_AdjustmentsReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", AdjustmentsReports.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_HrRelatedReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", HrRelatedReports.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void click_Reports_and_get_AbsenceRelatedReports() {
		ContainerAction obj = loadAndCheckTitle("Reports", AbsenceRelatedReports.class).get();
		closePanelAndParent(obj, "Reports");
	}

	@Test
	void load_and_check_MonthlyReports() {
		ContainerAction obj = loadAndCheckTitle(MonthlyReports.class).get();
		closePanel(obj);
	}

	@Test
	void load_and_check_YearlyReports() {
		ContainerAction obj = loadAndCheckTitle(YearlyReports.class).get();
		closePanel(obj);
	}
	
	@Test
	void click_BulkUpdates_and_get_ColaSalaryUpdates() {
		ContainerAction obj = loadAndCheckTitle("Bulk Updates", ColaSalaryUpdates.class).get();
		closePanelAndParent(obj, "Bulk Updates");
	}
	
	@Test
	void click_BulkUpdates_and_get_EmployeeCreation() {
		ContainerAction obj = loadAndCheckTitle("Bulk Updates", EmployeeCreation.class).get();
		closePanelAndParent(obj, "Bulk Updates");
	}

	@Test
	void load_and_check_PayrollSettings() {
		ContainerAction obj = loadAndCheckTitle(SettingsPayroll.class).get();
		closePanel(obj);
	}
	
	@AfterAll
	static void tearDown() {
//		homepagePayroll.close();
	}
	
	/* 
	 * Helpers below
	 */
	private Optional<ContainerAction> loadAndCheckTitle(String prntName, Class<?> clazz) {
		try {
			Optional<ContainerAction> obj = menu.clickParent(prntName).clickAndLoad(clazz);
			PageTitle title = obj.get().getTitle();
			assertEquals(title.getExpected(), title.getActual());
			return obj;	
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private Optional<ContainerAction> loadAndCheckTitle(Class<?> clazz) {
		Optional<ContainerAction> obj = menu.clickAndLoad(clazz);
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
		closer.close();
	}
	
	private void closeParent(String prntName) {		
		menu.clickParent(prntName);
	}
		
}
