/**
 * 
 */
package object_models.left_menu.common;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.MenuMap;
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
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class LeftMenu implements LeftMenuActions {
	private Map<String, WebElement> anchors;	
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	private LeftMenuElements elements;	
	private LeftMenuMapper menuMapper;
	
	public LeftMenu(WebDriver driver) {
		this.driver = driver;
		this.menuMapper = new LeftMenuMapper(driver);
		try {
			this.anchors = new MenuMap(new LeftMenuFactory(driver)).getAnchors().get();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Unable to get anchors from menu map");
		}
	}
		
	/*
	 * Get a map of all the names in the menu.
	 */
	public Map<String, Optional<List<String>>> getActualMenu(){
		return menuMapper.map().getMenu();		
	}
	
	public void setElements(LeftMenuElements elements) {
		this.elements = elements;		
	}
	
	public LeftMenuElements getElements() {
		return elements;
	}

	@Override
	public LeftMenuActions clickParent(String prntName) {
		WebElement activeMenuItem = getActiveMenuItem();
		if(!(activeMenuItem == null)) {
			String currentlyActive = activeMenuItem.getText().trim();			
			if(!currentlyActive.equalsIgnoreCase(prntName)) {
				anchors.get(prntName).click();
			}		else {
			}	
		}	else {
			anchors.get(prntName).click();
		}
		return this;
	}
	
	@Override
	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {
		Optional<ContainerAction> item = null;
		try {
			String prntName = (String) clazz.getField("MENU_PARENT_NAME").get(null);
			String menuName = (String) clazz.getField("MENU_TITLEX").get(null);
			item = clickParent(prntName).clickAndLoad(menuName);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			logger.error("Failed to get parent or menu name using reflection");
		}		
		return item;
	}

	@Override
	public Optional<ContainerAction> clickAndLoad(String elementName) {
		WebElement e = anchors.get(elementName);
		logger.info("Loading [" + elementName + "]");
		ClickUsingJavaScript.performClick(driver, e.getAttribute("href"));
		Optional<ContainerAction> child = Optional.empty();
		
		try {
			child = Optional.of(getElement(elementName).get());
		} catch (Exception ex) {
			logger.error("Could not get menu element [" + elementName + "]");
		}
		return child;
	}

	@Override
	public WebElement getActiveMenuItem() {
		WebElement activeMenuItem = null;
		try {
			activeMenuItem = menuMapper
					.getMenuElement()
					.findElement(By.cssSelector("a[class='dcjq-parent active']"));
		} catch (Exception e) {
			logger.error("Failed to find active menu element");
		}
		return activeMenuItem;
	}

	private Future<ContainerAction> getElement(String elementName) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		return executor.submit(() -> {
			return ChildElementFactory.getChild(elementName, driver);
		});
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
				
			// Monthly Reports
			case MonthlyReports.MENU_TITLE:
				child = new MonthlyReports(driver);
				break;
				
			// Yearly Reports
			case YearlyReports.MENU_TITLE:
				child = new YearlyReports(driver);
				break;

			// Bulk Updates
			case ColaSalaryUpdates.MENU_TITLE:
				child = new ColaSalaryUpdates(driver);
				break;
			case EmployeeCreation.MENU_TITLE:
				child = new EmployeeCreation(driver);
				break;

			// Payroll Settings
			case SettingsPayroll.MENU_TITLE:
				child = new SettingsPayroll(driver);
				break;
				
			default:
				LogManager.getLogger().error("Could not create [" + childName + "]");				
				break;
			}
			
			return child;
		}
	}

}
