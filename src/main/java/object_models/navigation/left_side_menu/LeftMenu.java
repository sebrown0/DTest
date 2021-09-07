/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.MenuMap;
import object_models.forms.ContainerAction;
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
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class LeftMenu {
	private Map<String, WebElement> anchors;	
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	private LeftMenuElements elements;
	
	public LeftMenu(WebDriver driver) {
		this.driver = driver;
		try {
			this.anchors = new MenuMap(new LeftMenuFactory(driver)).getAnchors().get();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Unable to get anchors from menu map");
		}
	}
		
	public void setElements(LeftMenuElements elements) {
		this.elements = elements;		
	}
	
	public LeftMenuElements getElements() {
		return elements;
	}
	
	public LeftMenu clickParent(String prntName) {
		WebElement e = anchors.get(prntName);			
//		System.out.println("href - " + e.getAttribute("href"));
		e.click();
		return this;
	}
	
	public ContainerAction load(String elementName) {
		WebElement e = anchors.get(elementName);
		logger.info("Loading [" + elementName + "]");
		ClickUsingJavaScript.performClick(driver, e.getAttribute("href"));
		return ChildElementFactory.getChild(elementName, driver);
	}

	private static class ChildElementFactory{
		public static ContainerAction getChild(String childName, WebDriver driver) {
			ContainerAction child = null;
			
			switch (childName) {
			case Documents.MENU_TITLE:
				child = new Documents(driver);
				break;
				
			case EmployeeList.MENU_TITLE:
				child = new EmployeeList(driver);
				break;
				
			// Employees
			case EmployeeDetails.MENU_TITLE:
				child = new EmployeeDetails(driver);
				break;			
			case ContactNumbers.MENU_TITLE:
				child = new ContactNumbers(driver);
				break;
			case Banks.MENU_TITLE:
				child = new Banks(driver);
				break;
			case SalaryDetails.MENU_TITLE:
				child = new SalaryDetails(driver);
				break;
			case CareerProgression.MENU_TITLE:
				child = new CareerProgression(driver);
				break;	
			case Schedule.MENU_TITLE:
				child = new Schedule(driver);
				break;	
			case PermanentAllowances.MENU_TITLE:
				child = new PermanentAllowances(driver);
				break;		
			case PreviousEmployement.MENU_TITLE:
				child = new PreviousEmployement(driver);
				break;		
			case Unions.MENU_TITLE:
				child = new Unions(driver);
				break;
				
			// Employee Others
			case AbsenceEntitlements.MENU_TITLE:
				child = new AbsenceEntitlements(driver);
				break;
			case AdvancesAndPayments.MENU_TITLE:
				child = new AdvancesAndPayments(driver);
				break;	
			case TaxArrears.MENU_TITLE:
				child = new TaxArrears(driver);
				break;		
			case Loans.MENU_TITLE:
				child = new Loans(driver);
				break;	
			case Pensions.MENU_TITLE:
				child = new Pensions(driver);
				break;	
			case Covid19Supplement.MENU_TITLE:
				child = new Covid19Supplement(driver);
				break;
				
			// Additional Hours
			case ApplyAdditionalHours.MENU_TITLE:
				child = new ApplyAdditionalHours(driver);
				break;
			case Authorisation.MENU_TITLE:
				child = new Authorisation(driver);
				break;

			// Payroll
			case InitialisePayroll.MENU_TITLE:
				child = new InitialisePayroll(driver);
				break;
			case PayrollDetailsDrillDown.MENU_TITLE:
				child = new PayrollDetailsDrillDown(driver);
				break;
			case DetailedAdjustments.MENU_TITLE:
				child = new DetailedAdjustments(driver);
				break;
			case GlobalAdjustments.MENU_TITLE:
				child = new GlobalAdjustments(driver);
				break;
			case GlobalAbsences.MENU_TITLE:
				child = new GlobalAbsences(driver);
				break;
			case GlobalExtras.MENU_TITLE:
				child = new GlobalExtras(driver);
				break;		
			case CalculatePayroll.MENU_TITLE:
				child = new CalculatePayroll(driver);
				break;		
			case CloseAndLockPayroll.MENU_TITLE:
				child = new CloseAndLockPayroll(driver);
				break;		
			case PayrollDetails.MENU_TITLE:
				child = new PayrollDetails(driver);
				break;		
			case ExcelPayrollUploads.MENU_TITLE:
				child = new ExcelPayrollUploads(driver);
				break;		
			case CalculationStatistics.MENU_TITLE:
				child = new CalculationStatistics(driver);
				break;						
				
			// Employee Statistics
			case PayslipQuickView.MENU_TITLE:
				child = new PayslipQuickView(driver);
				break;
			case Fs3QuickView.MENU_TITLE:
				child = new Fs3QuickView(driver);
				break;
				
			// Payroll Statistics
			case PayrollStatistics.MENU_TITLE:
				child = new PayrollStatistics(driver);
				break;
				
			// Absence Statistics
			case EmployeeAccruals.MENU_TITLE:
				child = new EmployeeAccruals(driver);
				break;
			case OtherAbsenceStatistics.MENU_TITLE:
				child = new OtherAbsenceStatistics(driver);
				break;	
				
			// Reports
			case PayrollReports.MENU_TITLE:
				child = new PayrollReports(driver);
				break;
			case Payslips.MENU_TITLE:
				child = new Payslips(driver);
				break;
			case DirectCredits.MENU_TITLE:
				child = new DirectCredits(driver);
				break;
			case GlobalPayrollAnalysis.MENU_TITLE:
				child = new GlobalPayrollAnalysis(driver);
				break;
			case ChequePrinting.MENU_TITLE:
				child = new ChequePrinting(driver);
				break;
			case AdjustmentsReports.MENU_TITLE:
				child = new AdjustmentsReports(driver);
				break;
			case HrRelatedReports.MENU_TITLE:
				child = new HrRelatedReports(driver);
				break;
			case AbsenceRelatedReports.MENU_TITLE:
				child = new AbsenceRelatedReports(driver);
				break;	
				
			default:
				LogManager.getLogger().error("Could not create [" + childName + "]");				
				break;
			}
			
			return child;
		}
	}
}
