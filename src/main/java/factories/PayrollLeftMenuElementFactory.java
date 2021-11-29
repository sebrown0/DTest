/**
 * 
 */
package factories;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.forms.ContainerAction;
import object_models.left_menu.absence_statistics.EmployeeAccruals;
import object_models.left_menu.absence_statistics.OtherAbsenceStatistics;
import object_models.left_menu.additional_hours.ApplyAdditionalHours;
import object_models.left_menu.additional_hours.Authorisation;
import object_models.left_menu.bulk_updates.ColaSalaryUpdates;
import object_models.left_menu.bulk_updates.EmployeeCreation;
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
import object_models.left_menu.payroll.PayrollDetails;
import object_models.left_menu.payroll.PayrollDetailsDrillDown;
import object_models.left_menu.payroll.initialise.InitialisePayroll;
import object_models.left_menu.reports.AbsenceRelatedReports;
import object_models.left_menu.reports.AdjustmentsReports;
import object_models.left_menu.reports.ChequePrinting;
import object_models.left_menu.reports.DirectCredits;
import object_models.left_menu.reports.GlobalPayrollAnalysis;
import object_models.left_menu.reports.HrRelatedReports;
import object_models.left_menu.reports.PayrollReports;
import object_models.left_menu.reports.Payslips;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class PayrollLeftMenuElementFactory implements MenuElementFactory {	
	private CoreData hp;
	private WebDriver driver;
	private ContextManager contextManager;
		
	public PayrollLeftMenuElementFactory(CoreData hp) {
		this.hp = hp;
		this.driver = hp.getWebDriver();
		this.contextManager = hp.getContextManager();
	}

	@Override // MenuElementFactory
	public ContainerAction getElementForName(String elementName) {			
		ContainerAction element = null;
		switch (elementName) {		
		case Documents.MENU_TITLE:				
			element = new Documents(driver, contextManager);
			break;			
		case EmployeeList.MENU_TITLE:
			element = new EmployeeList(driver, contextManager);
			break;
			
		// Employees
		case EmployeeDetails.MENU_TITLE:
			element = new EmployeeDetails(driver, contextManager);
			break;			
		case ContactNumbers.MENU_TITLE:
			element = new ContactNumbers(driver, contextManager);
			break;
		case Banks.MENU_TITLE:
			element = new Banks(driver, contextManager);
			break;
		case SalaryDetails.MENU_TITLE:
			element = new SalaryDetails(driver, contextManager);
			break;
		case CareerProgression.MENU_TITLE:
			element = new CareerProgression(driver, contextManager);
			break;	
		case Schedule.MENU_TITLE:
			element = new Schedule(driver, contextManager);
			break;	
		case PermanentAllowances.MENU_TITLE:
			element = new PermanentAllowances(driver, contextManager);
			break;		
		case PreviousEmployement.MENU_TITLE:
			element = new PreviousEmployement(driver, contextManager);
			break;		
		case Unions.MENU_TITLE:
			element = new Unions(driver, contextManager);
			break;
			
		// Employee Others
		case AbsenceEntitlements.MENU_TITLE:
			element = new AbsenceEntitlements(driver, contextManager);
			break;
		case AdvancesAndPayments.MENU_TITLE:
			element = new AdvancesAndPayments(driver, contextManager);
			break;	
		case TaxArrears.MENU_TITLE:
			element = new TaxArrears(driver, contextManager);
			break;		
		case Loans.MENU_TITLE:
			element = new Loans(driver, contextManager);
			break;	
		case Pensions.MENU_TITLE:
			element = new Pensions(driver, contextManager);
			break;	
		case Covid19Supplement.MENU_TITLE:
			element = new Covid19Supplement(driver, contextManager);
			break;
			
		// Additional Hours
		case ApplyAdditionalHours.MENU_TITLE:
			element = new ApplyAdditionalHours(driver, contextManager);
			break;
		case Authorisation.MENU_TITLE:
			element = new Authorisation(driver, contextManager);
			break;

		// Payroll
		case InitialisePayroll.MENU_TITLE:
			element = new InitialisePayroll(hp);
			break;
		case PayrollDetailsDrillDown.MENU_TITLE:
			element = new PayrollDetailsDrillDown(driver, contextManager);
			break;
		case DetailedAdjustments.MENU_TITLE:
			element = new DetailedAdjustments(driver, contextManager);
			break;
		case GlobalAdjustments.MENU_TITLE:
			element = new GlobalAdjustments(driver, contextManager);
			break;
		case GlobalAbsences.MENU_TITLE:
			element = new GlobalAbsences(driver, contextManager);
			break;
		case GlobalExtras.MENU_TITLE:
			element = new GlobalExtras(driver, contextManager);
			break;		
		case CalculatePayroll.MENU_TITLE:
			element = new CalculatePayroll(driver, contextManager);
			break;		
		case CloseAndLockPayroll.MENU_TITLE:
			element = new CloseAndLockPayroll(driver, contextManager);
			break;		
		case PayrollDetails.MENU_TITLE:
			element = new PayrollDetails(driver, contextManager);
			break;		
		case ExcelPayrollUploads.MENU_TITLE:
			element = new ExcelPayrollUploads(driver, contextManager);
			break;		
		case CalculationStatistics.MENU_TITLE:
			element = new CalculationStatistics(driver, contextManager);
			break;						
			
		// Employee Statistics
		case PayslipQuickView.MENU_TITLE:
			element = new PayslipQuickView(driver, contextManager);
			break;
		case Fs3QuickView.MENU_TITLE:
			element = new Fs3QuickView(driver, contextManager);
			break;
			
		// Payroll Statistics
		case PayrollStatistics.MENU_TITLE:
			element = new PayrollStatistics(driver, contextManager);
			break;
			
		// Absence Statistics
		case EmployeeAccruals.MENU_TITLE:
			element = new EmployeeAccruals(driver, contextManager);
			break;
		case OtherAbsenceStatistics.MENU_TITLE:
			element = new OtherAbsenceStatistics(driver, contextManager);
			break;	
			
		// Reports
		case PayrollReports.MENU_TITLE:
			element = new PayrollReports(driver, contextManager);
			break;
		case Payslips.MENU_TITLE:
			element = new Payslips(driver, contextManager);
			break;
		case DirectCredits.MENU_TITLE:
			element = new DirectCredits(driver, contextManager);
			break;
		case GlobalPayrollAnalysis.MENU_TITLE:
			element = new GlobalPayrollAnalysis(driver, contextManager);
			break;
		case ChequePrinting.MENU_TITLE:
			element = new ChequePrinting(driver, contextManager);
			break;
		case AdjustmentsReports.MENU_TITLE:
			element = new AdjustmentsReports(driver, contextManager);
			break;
		case HrRelatedReports.MENU_TITLE:
			element = new HrRelatedReports(driver, contextManager);
			break;
		case AbsenceRelatedReports.MENU_TITLE:
			element = new AbsenceRelatedReports(driver, contextManager);
			break;	
			
		// Monthly Reports
		case MonthlyReports.MENU_TITLE:
			element = new MonthlyReports(driver, contextManager);
			break;
			
		// Yearly Reports
		case YearlyReports.MENU_TITLE:
			element = new YearlyReports(driver, contextManager);
			break;

		// Bulk Updates
		case ColaSalaryUpdates.MENU_TITLE:
			element = new ColaSalaryUpdates(driver, contextManager);
			break;
		case EmployeeCreation.MENU_TITLE:
			element = new EmployeeCreation(driver, contextManager);
			break;

		// Payroll Settings
		case SettingsPayroll.MENU_TITLE:
			element = new SettingsPayroll(driver, contextManager);
			break;
			
		default:
			LogManager.getLogger().error("Could not create [" + elementName + "]");				
			break;
		}
		
		return element;
	}

}
