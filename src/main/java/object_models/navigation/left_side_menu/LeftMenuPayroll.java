/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import object_models.panels.menu.absence_statistics.EmployeeAccruals;
import object_models.panels.menu.absence_statistics.OtherAbsenceStatistics;
import object_models.panels.menu.additional_hours.ApplyAdditionalHours;
import object_models.panels.menu.additional_hours.Authorisation;
import object_models.panels.menu.bulk_updates.ColaSalaryUpdates;
import object_models.panels.menu.bulk_updates.EmployeeCreation;
import object_models.panels.menu.employee_others.AbsenceEntitlements;
import object_models.panels.menu.employee_others.AdvancesAndPayments;
import object_models.panels.menu.employee_others.Covid19Supplement;
import object_models.panels.menu.employee_others.Loans;
import object_models.panels.menu.employee_others.Pensions;
import object_models.panels.menu.employee_others.TaxArrears;
import object_models.panels.menu.employee_statistics.EmployeePayrollStatistics;
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
import object_models.panels.menu.parents.MonthlyReports;
import object_models.panels.menu.parents.PayrollStatistics;
import object_models.panels.menu.parents.SettingsPayroll;
import object_models.panels.menu.parents.YearlyReports;
import object_models.panels.menu.payroll.CalculatePayroll;
import object_models.panels.menu.payroll.CalculationStatistics;
import object_models.panels.menu.payroll.CloseAndLockPayroll;
import object_models.panels.menu.payroll.DetailedAdjustments;
import object_models.panels.menu.payroll.ExcelPayrollUploads;
import object_models.panels.menu.payroll.GlobalAbsences;
import object_models.panels.menu.payroll.GlobalAdjustments;
import object_models.panels.menu.payroll.GlobalExtras;
import object_models.panels.menu.payroll.InitialisePayroll;
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

/**
 * @author Steve Brown
 *
 * LeftMenuElements IS A MARKER.
 * 
 * Represents the Left Menu for payroll.
 * All Menu Items (top level) should be included.
 * If a menu item has a sub-menu that is included in a 
 * list of sub-menu items.
 * 
 * The final menu is returned as a map: 
 * 	Key = menu item name, i.e. Employees.
 *	Val = Optional list of sub-menu names. 
 */
public class LeftMenuPayroll implements LeftMenuElements {

	private static final List<String> EMPLOYEE_LIST = Arrays.asList(
			EmployeeList.MENU_TITLE
	);	
	public List<String> getEmployeeList() {
		return EMPLOYEE_LIST;	
	}
	
	private static final List<String> DOCUMENTS = Arrays.asList(
			Documents.MENU_TITLE
	);	
	public List<String> getDocuments() {
		return DOCUMENTS;	
	}
	
	private static final List<String> EMPLOYEES = Arrays.asList(
			EmployeeDetails.MENU_TITLE,
			ContactNumbers.MENU_TITLE,
			Banks.MENU_TITLE,
			SalaryDetails.MENU_TITLE,
			CareerProgression.MENU_TITLE,
			Schedule.MENU_TITLE,
			PermanentAllowances.MENU_TITLE,
			PreviousEmployement.MENU_TITLE,
			Unions.MENU_TITLE
	);	
	public List<String> getEmployees() {
		return EMPLOYEES;	
	}
	
	private static final List<String> EMPLOYEE_OTHERS = Arrays.asList(
			AbsenceEntitlements.MENU_TITLE,
			AdvancesAndPayments.MENU_TITLE,
			TaxArrears.MENU_TITLE,
			Loans.MENU_TITLE,
			Pensions.MENU_TITLE,
			Covid19Supplement.MENU_TITLE
	);
	public List<String> getEmployeeOthers() {
		return EMPLOYEE_OTHERS;	
	}

	private static final List<String> ADDITIONAL_HOURS = Arrays.asList(
			ApplyAdditionalHours.MENU_TITLE,
			Authorisation.MENU_TITLE
	);
	public List<String> getAdditionalHours() {
		return ADDITIONAL_HOURS;	
	}

	private static final List<String> PAYROLL = Arrays.asList(
			InitialisePayroll.MENU_TITLE,
			PayrollDetailsDrillDown.MENU_TITLE,
			DetailedAdjustments.MENU_TITLE,
			GlobalAdjustments.MENU_TITLE,
			GlobalAbsences.MENU_TITLE,
			GlobalExtras.MENU_TITLE,
			CalculatePayroll.MENU_TITLE,
			CloseAndLockPayroll.MENU_TITLE,
			PayrollDetails.MENU_TITLE,
			ExcelPayrollUploads.MENU_TITLE,
			CalculationStatistics.MENU_TITLE
	);
	public List<String> getPayroll() {
		return PAYROLL;	
	}

	private static final List<String> EMPLOYEE_STATISTICS = Arrays.asList(
			PayslipQuickView.MENU_TITLE,
			Fs3QuickView.MENU_TITLE,
			EmployeePayrollStatistics.MENU_TITLE
	);
	public List<String> getEmployeeStatistics() {
		return EMPLOYEE_STATISTICS;	
	}
	
	private static final List<String> PAYROLL_STATISTICS = Arrays.asList(
			PayrollStatistics.MENU_TITLE
	);
	public List<String> getPayrollStatistics() {
		return PAYROLL_STATISTICS;	
	}
	
	private static final List<String> ABSENCE_STATISTICS = Arrays.asList(
			EmployeeAccruals.MENU_TITLE,
			OtherAbsenceStatistics.MENU_TITLE
	);
	public List<String> getAbsenceStatistics() {
		return ABSENCE_STATISTICS;	
	}
	
	private static final List<String> REPORTS = Arrays.asList(
			PayrollReports.MENU_TITLE,
			Payslips.MENU_TITLE,
			DirectCredits.MENU_TITLE,
			GlobalPayrollAnalysis.MENU_TITLE,			
			ChequePrinting.MENU_TITLE,
			AdjustmentsReports.MENU_TITLE,
			HrRelatedReports.MENU_TITLE,
			AbsenceRelatedReports.MENU_TITLE
	);
	public List<String> getReports() {
		return REPORTS;	
	}
	
	private static final List<String> MONTHLY_REPORTS = Arrays.asList(
			MonthlyReports.MENU_TITLE
	);
	public List<String> getMonthlyReports() {
		return MONTHLY_REPORTS;	
	}
	
	private static final List<String> YEARLY_REPORTS = Arrays.asList(
			YearlyReports.MENU_TITLE
	);
	public List<String> getYearlyReports() {
		return YEARLY_REPORTS;	
	}
	
	private static final List<String> BULK_UPDATES = Arrays.asList(
			ColaSalaryUpdates.MENU_TITLE,
			EmployeeCreation.MENU_TITLE
	);
	public List<String> getBulkUpdates() {
		return BULK_UPDATES;	
	}
	
	private static final List<String> SETTINGS = Arrays.asList(
			SettingsPayroll.MENU_TITLE
	);
	public List<String> getSettings() {
		return SETTINGS;	
	}
			
	@SuppressWarnings("unchecked")
	public Map<String, Optional<List<String>>> getAll(){				
		return Stream.of(new Object[][] {
			{EmployeeList.MENU_TITLE,  Optional.empty()},
			{Documents.MENU_TITLE, Optional.empty()},
			{"Employees", Optional.of(EMPLOYEES)},
			{"Employee Others", Optional.of(EMPLOYEE_OTHERS)},
			{"Additional Hours", Optional.of(ADDITIONAL_HOURS)},
			{"Payroll", Optional.of(PAYROLL)},
			{"Employee Statistics", Optional.of(EMPLOYEE_STATISTICS)},
			{PayrollStatistics.MENU_TITLE, Optional.empty()},
			{"Absence Statistics", Optional.of(ABSENCE_STATISTICS)},
			{"Reports", Optional.of(REPORTS)},
			{MonthlyReports.MENU_TITLE, Optional.empty()},
			{YearlyReports.MENU_TITLE, Optional.empty()},
			{"Bulk Updates", Optional.of(BULK_UPDATES)},
			{SettingsPayroll.MENU_TITLE, Optional.empty()}
		}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));		
	}
			
	public static class MenuItem {
		private String parentName;
		private Optional<List<String>> childNames;
		
		public MenuItem(String parentName, Optional<List<String>> childNames) {
			this.parentName = parentName;
			this.childNames = childNames;
		}
		public String getParentName() {
			return parentName;
		}
		public Optional<List<String>> getChildNames() {
			return childNames;
		}		
	}	
}
