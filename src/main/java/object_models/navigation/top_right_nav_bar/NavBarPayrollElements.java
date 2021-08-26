/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarDakarIntelligence;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmpGridView;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCVPayroll;
import object_models.navigation.top_right_nav_bar.elements.NavBarMyCoLastViewed;
import object_models.navigation.top_right_nav_bar.elements.NavBarNewEmployments;
import object_models.navigation.top_right_nav_bar.elements.NavBarNotifications;
import object_models.navigation.top_right_nav_bar.elements.NavBarTerminations;
import object_models.navigation.top_right_nav_bar.elements.NavBarUserManagment;
import object_models.navigation.top_right_nav_bar.elements.NavBarVisualReports;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinksPayroll;
import object_models.navigation.top_right_nav_bar.xx_nav_bar_clicker.XX_NavBarClicker;
import object_models.navigation.top_right_nav_bar.xx_nav_bar_clicker.XX_PayrollNavBarClicker;

/**
 * @author Steve Brown
 * 
 * Set a map of payroll elements for the top right nav bar.
 * 	Key: 	Name of the element.
 * 	Value:	Object representing the element.
 */
public class NavBarPayrollElements implements NavBarElementStrategy {
	private Map<String, NavBarElement> elements;	
	private WebDriver driver;
	
	public NavBarPayrollElements(WebDriver driver) {
		this.driver = driver;
		setElements();
	}

	private void setElements(){
		elements = Stream.of(new Object[][] {
			{NavBarEmployeeCreation.ORIGINAL_NAME, new NavBarEmployeeCreation(driver)},
			{NavBarEmployeeCVPayroll.ORIGINAL_NAME, new NavBarEmployeeCVPayroll(driver)},
			{NavBarEmpGridView.ORIGINAL_NAME, new NavBarEmpGridView(driver)},
			{NavBarVisualReports.ORIGINAL_NAME, new NavBarVisualReports(driver)},
			{NavBarDakarIntelligence.ORIGINAL_NAME, new NavBarDakarIntelligence(driver)},
			{NavBarMyCoLastViewed.ORIGINAL_NAME, new NavBarMyCoLastViewed(driver)},			
			{NavBarNotifications.ORIGINAL_NAME, new NavBarNotifications(driver)},
			{NavBarNewEmployments.ORIGINAL_NAME, new NavBarNewEmployments(driver)},
			{NavBarTerminations.ORIGINAL_NAME, new NavBarTerminations(driver)},
			{NavBarUserManagment.ORIGINAL_NAME, new NavBarUserManagment(driver)}
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1]));		
	}

	@Override
	public Map<String, NavBarElement> getElements() {
		return elements;
	}

	@Override
	public XX_NavBarClicker getNavBarClicker() {
		return new XX_PayrollNavBarClicker(this);
	}

	@Override
	public QuickLinks getQuickLinks() {
		return new QuickLinksPayroll(driver);
	}
}
