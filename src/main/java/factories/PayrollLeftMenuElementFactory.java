/**
 * 
 */
package factories;

import org.apache.logging.log4j.LogManager;

import object_models.forms.ContainerAction;
import object_models.left_menu.additional_hours.ApplyAdditionalHours;
import object_models.left_menu.additional_hours.Authorisation;
import object_models.module_common.EmployeeList;
import object_models.module_payroll.Documents;
import object_models.module_payroll.MonthlyReports;
import object_models.module_payroll.PayrollStatistics;
import object_models.module_payroll.SettingsPayroll;
import object_models.module_payroll.YearlyReports;
import object_models.module_payroll.left_menu.absence_statistics.EmployeeAccruals;
import object_models.module_payroll.left_menu.absence_statistics.OtherAbsenceStatistics;
import object_models.module_payroll.left_menu.bulk_updates.ColaSalaryUpdates;
import object_models.module_payroll.left_menu.bulk_updates.EmployeeCreation;
import object_models.module_payroll.left_menu.employee_others.AbsenceEntitlements;
import object_models.module_payroll.left_menu.employee_others.AdvancesAndPayments;
import object_models.module_payroll.left_menu.employee_others.Covid19Supplement;
import object_models.module_payroll.left_menu.employee_others.Loans;
import object_models.module_payroll.left_menu.employee_others.Pensions;
import object_models.module_payroll.left_menu.employee_others.TaxArrears;
import object_models.module_payroll.left_menu.employee_statistics.Fs3QuickView;
import object_models.module_payroll.left_menu.employee_statistics.PayslipQuickView;
import object_models.module_payroll.left_menu.employees.Banks;
import object_models.module_payroll.left_menu.employees.CareerProgression;
import object_models.module_payroll.left_menu.employees.ContactNumbers;
import object_models.module_payroll.left_menu.employees.EmployeeDetails;
import object_models.module_payroll.left_menu.employees.PermanentAllowances;
import object_models.module_payroll.left_menu.employees.PreviousEmployement;
import object_models.module_payroll.left_menu.employees.SalaryDetails;
import object_models.module_payroll.left_menu.employees.Schedule;
import object_models.module_payroll.left_menu.employees.Unions;
import object_models.module_payroll.left_menu.payroll.CalculatePayroll;
import object_models.module_payroll.left_menu.payroll.CalculationStatistics;
import object_models.module_payroll.left_menu.payroll.CloseAndLockPayroll;
import object_models.module_payroll.left_menu.payroll.DetailedAdjustments;
import object_models.module_payroll.left_menu.payroll.ExcelPayrollUploads;
import object_models.module_payroll.left_menu.payroll.GlobalAbsences;
import object_models.module_payroll.left_menu.payroll.GlobalAdjustments;
import object_models.module_payroll.left_menu.payroll.GlobalExtras;
import object_models.module_payroll.left_menu.payroll.PayrollDetails;
import object_models.module_payroll.left_menu.payroll.PayrollDetailsDrillDown;
import object_models.module_payroll.left_menu.payroll.initialise.InitialisePayroll;
import object_models.module_payroll.left_menu.reports.AbsenceRelatedReports;
import object_models.module_payroll.left_menu.reports.AdjustmentsReports;
import object_models.module_payroll.left_menu.reports.ChequePrinting;
import object_models.module_payroll.left_menu.reports.DirectCredits;
import object_models.module_payroll.left_menu.reports.GlobalPayrollAnalysis;
import object_models.module_payroll.left_menu.reports.HrRelatedReports;
import object_models.module_payroll.left_menu.reports.PayrollReports;
import object_models.module_payroll.left_menu.reports.Payslips;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class PayrollLeftMenuElementFactory implements MenuElementFactory {	
	private CoreData coreData;
		
	public PayrollLeftMenuElementFactory(CoreData coreData) {
		this.coreData = coreData;
	}

	@Override // MenuElementFactory
	public ContainerAction getElementForName(String elementName) {			
		ContainerAction element = null;
		switch (elementName) {		
		case Documents.MENU_TITLE:				
			element = new Documents(coreData);
			break;			
		case EmployeeList.MENU_TITLE:
			element = new EmployeeList(coreData);
			break;
			
		// Employees
		case EmployeeDetails.MENU_TITLE:
			element = new EmployeeDetails(coreData);
			break;			
		case ContactNumbers.MENU_TITLE:
			element = new ContactNumbers(coreData);
			break;
		case Banks.MENU_TITLE:
			element = new Banks(coreData);
			break;
		case SalaryDetails.MENU_TITLE:
			element = new SalaryDetails(coreData);
			break;
		case CareerProgression.MENU_TITLE:
			element = new CareerProgression(coreData);
			break;	
		case Schedule.MENU_TITLE:
			element = new Schedule(coreData);
			break;	
		case PermanentAllowances.MENU_TITLE:
			element = new PermanentAllowances(coreData);
			break;		
		case PreviousEmployement.MENU_TITLE:
			element = new PreviousEmployement(coreData);
			break;		
		case Unions.MENU_TITLE:
			element = new Unions(coreData);
			break;
			
		// Employee Others
		case AbsenceEntitlements.MENU_TITLE:
			element = new AbsenceEntitlements(coreData);
			break;
		case AdvancesAndPayments.MENU_TITLE:
			element = new AdvancesAndPayments(coreData);
			break;	
		case TaxArrears.MENU_TITLE:
			element = new TaxArrears(coreData);
			break;		
		case Loans.MENU_TITLE:
			element = new Loans(coreData);
			break;	
		case Pensions.MENU_TITLE:
			element = new Pensions(coreData);
			break;	
		case Covid19Supplement.MENU_TITLE:
			element = new Covid19Supplement(coreData);
			break;
			
		// Additional Hours
		case ApplyAdditionalHours.MENU_TITLE:
			element = new ApplyAdditionalHours(coreData);
			break;
		case Authorisation.MENU_TITLE:
			element = new Authorisation(coreData);
			break;

		// Payroll
		case InitialisePayroll.MENU_TITLE:
			element = new InitialisePayroll(coreData);
			break;
		case PayrollDetailsDrillDown.MENU_TITLE:
			element = new PayrollDetailsDrillDown(coreData);
			break;
		case DetailedAdjustments.MENU_TITLE:
			element = new DetailedAdjustments(coreData);
			break;
		case GlobalAdjustments.MENU_TITLE:
			element = new GlobalAdjustments(coreData);
			break;
		case GlobalAbsences.MENU_TITLE:
			element = new GlobalAbsences(coreData);
			break;
		case GlobalExtras.MENU_TITLE:
			element = new GlobalExtras(coreData);
			break;		
		case CalculatePayroll.MENU_TITLE:
			element = new CalculatePayroll(coreData);
			break;		
		case CloseAndLockPayroll.MENU_TITLE:
			element = new CloseAndLockPayroll(coreData);
			break;		
		case PayrollDetails.MENU_TITLE:
			element = new PayrollDetails(coreData);
			break;		
		case ExcelPayrollUploads.MENU_TITLE:
			element = new ExcelPayrollUploads(coreData);
			break;		
		case CalculationStatistics.MENU_TITLE:
			element = new CalculationStatistics(coreData);
			break;						
			
		// Employee Statistics
		case PayslipQuickView.MENU_TITLE:
			element = new PayslipQuickView(coreData);
			break;
		case Fs3QuickView.MENU_TITLE:
			element = new Fs3QuickView(coreData);
			break;
			
		// Payroll Statistics
		case PayrollStatistics.MENU_TITLE:
			element = new PayrollStatistics(coreData);
			break;
			
		// Absence Statistics
		case EmployeeAccruals.MENU_TITLE:
			element = new EmployeeAccruals(coreData);
			break;
		case OtherAbsenceStatistics.MENU_TITLE:
			element = new OtherAbsenceStatistics(coreData);
			break;	
			
		// Reports
		case PayrollReports.MENU_TITLE:
			element = new PayrollReports(coreData);
			break;
		case Payslips.MENU_TITLE:
			element = new Payslips(coreData);
			break;
		case DirectCredits.MENU_TITLE:
			element = new DirectCredits(coreData);
			break;
		case GlobalPayrollAnalysis.MENU_TITLE:
			element = new GlobalPayrollAnalysis(coreData);
			break;
		case ChequePrinting.MENU_TITLE:
			element = new ChequePrinting(coreData);
			break;
		case AdjustmentsReports.MENU_TITLE:
			element = new AdjustmentsReports(coreData);
			break;
		case HrRelatedReports.MENU_TITLE:
			element = new HrRelatedReports(coreData);
			break;
		case AbsenceRelatedReports.MENU_TITLE:
			element = new AbsenceRelatedReports(coreData);
			break;	
			
		// Monthly Reports
		case MonthlyReports.MENU_TITLE:
			element = new MonthlyReports(coreData);
			break;
			
		// Yearly Reports
		case YearlyReports.MENU_TITLE:
			element = new YearlyReports(coreData);
			break;

		// Bulk Updates
		case ColaSalaryUpdates.MENU_TITLE:
			element = new ColaSalaryUpdates(coreData);
			break;
		case EmployeeCreation.MENU_TITLE:
			element = new EmployeeCreation(coreData);
			break;

		// Payroll Settings
		case SettingsPayroll.MENU_TITLE:
			element = new SettingsPayroll(coreData);
			break;
			
		default:
			LogManager.getLogger().error("Could not create [" + elementName + "]");				
			break;
		}
		
		return element;
	}

}
