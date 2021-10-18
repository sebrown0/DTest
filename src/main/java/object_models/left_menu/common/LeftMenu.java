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
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.CallingState;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.State;
import context_manager.StateLeftMenu;
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

/**
 * @author Steve Brown
 *
 */
public class LeftMenu implements LeftMenuActions, CallingState {
	private Map<String, WebElement> anchors;	
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	private LeftMenuElements elements;	
	private LeftMenuMapper menuMapper;	
	private ContextManager contextManager;
	
	public LeftMenu(WebDriver driver, ContextManager contextManager) {
		this.driver = driver;
		this.contextManager = contextManager;
		this.contextManager.setCallingState(this);		
		
		mapAnchors();
	}

	private void mapAnchors() {
		menuMapper = new LeftMenuMapper(driver);
		
		try {
			this.anchors = new MenuMap(new LeftMenuFactory(driver)).getAnchors().get();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Unable to get anchors from menu map");
		}
	}
	
	// Getters & Setters
		
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

	// LeftMenuActions	
	@Override	
	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {		
		contextManager.switchToFirstStateInCurrentContext();
		Optional<ContainerAction> item = null;
		Optional<String> prntName = getParentName(clazz);
		Optional<String> menuItem = getMenuItemName(clazz);
		
		if(isChildMenuItem(prntName, menuItem)) {		
			item = clickParent(prntName.get()).clickAndLoad(menuItem.get());			
		}else if (isParentMenuItem(prntName)) {
			item = clickAndLoad(menuItem.get());
		}		
		return item;
	}
	
	private Optional<String> getParentName(Class<?> clazz) {
		try {
			return Optional.ofNullable((String) clazz.getField("MENU_PARENT_NAME").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get parent name using reflection");
			return Optional.empty();
		}
	}
	
	private Optional<String> getMenuItemName(Class<?> clazz) {
		try {
			return Optional.ofNullable((String) clazz.getField("MENU_TITLE").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get menu name using reflection");
			return Optional.empty();
		}
	}
	
	private boolean isParentMenuItem(Optional<String> prntName) {
		boolean retVal = false;
		
		if(prntName.isPresent()) {
			String name = prntName.get();
			if(name.equals("") || name.length() < 1) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	private boolean isChildMenuItem(Optional<String> prntName, Optional<String> menuIem) {
		boolean retVal = false;
		
		if(prntName.isPresent()) {
			if(prntName.get().length() > 1 && menuIem.isPresent()) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	@Override
	public Optional<ContainerAction> clickAndLoad(String elementName) {
		WebElement e =  anchors.get(elementName);

		contextManager.switchToFirstStateInCurrentContext();		
		logger.info("Loading [" + elementName + "]");		 	
		
		Optional<ContainerAction> child = Optional.empty();		
		try {
			e.click();			
			child = Optional.of(getElement(elementName).get());			
		} catch (Exception ex) {
			logger.error("Could not get menu element [" + elementName + "] [" + ex.getMessage() + "]");
		}
		return child;
	}
	
	@Override
	public LeftMenuActions clickParent(String prntName) {
		WebElement activeMenuItem = getActiveMenuItem();
		if(activeMenuItem != null) {
			String currentlyActive = activeMenuItem.getText().trim();			
			if(!currentlyActive.equalsIgnoreCase(prntName)) {
				anchors.get(prntName).click();
			}	
		}	else {
			anchors.get(prntName).click();
		}
		return this;
	}
	
	@Override
	public WebElement getActiveMenuItem() {
		WebElement activeMenuItem = null;
		try {
			activeMenuItem = menuMapper
					.getMenuElement()
					.findElement(By.cssSelector("a[class='dcjq-parent active']"));
		} catch (NoSuchSessionException e2) {
			logger.error("No session found. Driver has probably been closed");
			System.out.println("No session found. Driver has probably been closed"); // TODO - remove or log 	
		} catch (Exception e1) {
			logger.error("Failed to find active menu element");
		}
		return activeMenuItem;
	}
	
	private Future<ContainerAction> getElement(String elementName) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		return executor.submit(() -> {
			return ChildElementFactory.getChild(elementName, driver, contextManager);
		});
	}

	@Override
	public State getState(ContextState context) { 
		return new StateLeftMenu(context);
	}
	
	private static class ChildElementFactory{
		public static ContainerAction getChild(String childName, WebDriver driver, ContextManager contextManager) {
			ContainerAction child = null;
			switch (childName) {
			case Documents.MENU_TITLE:
				child = new Documents(driver, contextManager);
				break;
				
			case EmployeeList.MENU_TITLE:
				child = new EmployeeList(driver, contextManager);
				break;
				
			// Employees
			case EmployeeDetails.MENU_TITLE:
				child = new EmployeeDetails(driver, contextManager);
				break;			
			case ContactNumbers.MENU_TITLE:
				child = new ContactNumbers(driver, contextManager);
				break;
			case Banks.MENU_TITLE:
				child = new Banks(driver, contextManager);
				break;
			case SalaryDetails.MENU_TITLE:
				child = new SalaryDetails(driver, contextManager);
				break;
			case CareerProgression.MENU_TITLE:
				child = new CareerProgression(driver, contextManager);
				break;	
			case Schedule.MENU_TITLE:
				child = new Schedule(driver, contextManager);
				break;	
			case PermanentAllowances.MENU_TITLE:
				child = new PermanentAllowances(driver, contextManager);
				break;		
			case PreviousEmployement.MENU_TITLE:
				child = new PreviousEmployement(driver, contextManager);
				break;		
			case Unions.MENU_TITLE:
				child = new Unions(driver, contextManager);
				break;
				
			// Employee Others
			case AbsenceEntitlements.MENU_TITLE:
				child = new AbsenceEntitlements(driver, contextManager);
				break;
			case AdvancesAndPayments.MENU_TITLE:
				child = new AdvancesAndPayments(driver, contextManager);
				break;	
			case TaxArrears.MENU_TITLE:
				child = new TaxArrears(driver, contextManager);
				break;		
			case Loans.MENU_TITLE:
				child = new Loans(driver, contextManager);
				break;	
			case Pensions.MENU_TITLE:
				child = new Pensions(driver, contextManager);
				break;	
			case Covid19Supplement.MENU_TITLE:
				child = new Covid19Supplement(driver, contextManager);
				break;
				
			// Additional Hours
			case ApplyAdditionalHours.MENU_TITLE:
				child = new ApplyAdditionalHours(driver, contextManager);
				break;
			case Authorisation.MENU_TITLE:
				child = new Authorisation(driver, contextManager);
				break;

			// Payroll
			case InitialisePayroll.MENU_TITLE:
				child = new InitialisePayroll(driver, contextManager);
				break;
			case PayrollDetailsDrillDown.MENU_TITLE:
				child = new PayrollDetailsDrillDown(driver, contextManager);
				break;
			case DetailedAdjustments.MENU_TITLE:
				child = new DetailedAdjustments(driver, contextManager);
				break;
			case GlobalAdjustments.MENU_TITLE:
				child = new GlobalAdjustments(driver, contextManager);
				break;
			case GlobalAbsences.MENU_TITLE:
				child = new GlobalAbsences(driver, contextManager);
				break;
			case GlobalExtras.MENU_TITLE:
				child = new GlobalExtras(driver, contextManager);
				break;		
			case CalculatePayroll.MENU_TITLE:
				child = new CalculatePayroll(driver, contextManager);
				break;		
			case CloseAndLockPayroll.MENU_TITLE:
				child = new CloseAndLockPayroll(driver, contextManager);
				break;		
			case PayrollDetails.MENU_TITLE:
				child = new PayrollDetails(driver, contextManager);
				break;		
			case ExcelPayrollUploads.MENU_TITLE:
				child = new ExcelPayrollUploads(driver, contextManager);
				break;		
			case CalculationStatistics.MENU_TITLE:
				child = new CalculationStatistics(driver, contextManager);
				break;						
				
			// Employee Statistics
			case PayslipQuickView.MENU_TITLE:
				child = new PayslipQuickView(driver, contextManager);
				break;
			case Fs3QuickView.MENU_TITLE:
				child = new Fs3QuickView(driver, contextManager);
				break;
				
			// Payroll Statistics
			case PayrollStatistics.MENU_TITLE:
				child = new PayrollStatistics(driver, contextManager);
				break;
				
			// Absence Statistics
			case EmployeeAccruals.MENU_TITLE:
				child = new EmployeeAccruals(driver, contextManager);
				break;
			case OtherAbsenceStatistics.MENU_TITLE:
				child = new OtherAbsenceStatistics(driver, contextManager);
				break;	
				
			// Reports
			case PayrollReports.MENU_TITLE:
				child = new PayrollReports(driver, contextManager);
				break;
			case Payslips.MENU_TITLE:
				child = new Payslips(driver, contextManager);
				break;
			case DirectCredits.MENU_TITLE:
				child = new DirectCredits(driver, contextManager);
				break;
			case GlobalPayrollAnalysis.MENU_TITLE:
				child = new GlobalPayrollAnalysis(driver, contextManager);
				break;
			case ChequePrinting.MENU_TITLE:
				child = new ChequePrinting(driver, contextManager);
				break;
			case AdjustmentsReports.MENU_TITLE:
				child = new AdjustmentsReports(driver, contextManager);
				break;
			case HrRelatedReports.MENU_TITLE:
				child = new HrRelatedReports(driver, contextManager);
				break;
			case AbsenceRelatedReports.MENU_TITLE:
				child = new AbsenceRelatedReports(driver, contextManager);
				break;	
				
			// Monthly Reports
			case MonthlyReports.MENU_TITLE:
				child = new MonthlyReports(driver, contextManager);
				break;
				
			// Yearly Reports
			case YearlyReports.MENU_TITLE:
				child = new YearlyReports(driver, contextManager);
				break;

			// Bulk Updates
			case ColaSalaryUpdates.MENU_TITLE:
				child = new ColaSalaryUpdates(driver, contextManager);
				break;
			case EmployeeCreation.MENU_TITLE:
				child = new EmployeeCreation(driver, contextManager);
				break;

			// Payroll Settings
			case SettingsPayroll.MENU_TITLE:
				child = new SettingsPayroll(driver, contextManager);
				break;
				
			default:
				LogManager.getLogger().error("Could not create [" + childName + "]");				
				break;
			}
			
			return child;
		}
	}

}
