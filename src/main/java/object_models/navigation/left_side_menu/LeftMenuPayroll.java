/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.Arrays;
import java.util.List;

import object_models.forms.menu.payroll.CloseAndLockPayroll;
import object_models.forms.menu.payroll.InitialisePayroll;
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

/**
 * @author Steve Brown
 *
 * LeftMenuElements IS A MARKER.
 */
public class LeftMenuPayroll implements LeftMenuElements{
		
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
			ApplyAdditionalHours.MENU_TITLE,
			Authorisation.MENU_TITLE
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
	
	public List<List<String>> getAll(){
		return Arrays.asList(
				EMPLOYEES,
				EMPLOYEE_LIST
		);
	}
}
