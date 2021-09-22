/**
 * 
 */
package object_models.top_right_nav_bar.personnel;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

import object_models.top_right_nav_bar.all_elements.NavBarDakarIntelligence;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.all_elements.NavBarEmpGridView;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCVPayroll;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCreation;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCvHr;
import object_models.top_right_nav_bar.all_elements.NavBarMyCoLastViewed;
import object_models.top_right_nav_bar.all_elements.NavBarNewEmployments;
import object_models.top_right_nav_bar.all_elements.NavBarNotifications;
import object_models.top_right_nav_bar.all_elements.NavBarOrganisationChart;
import object_models.top_right_nav_bar.all_elements.NavBarTerminations;
import object_models.top_right_nav_bar.all_elements.NavBarVisualReports;
import object_models.top_right_nav_bar.common.NavBarElement;
import object_models.top_right_nav_bar.quick_links.QuickLinks;
import object_models.top_right_nav_bar.quick_links.QuickLinksPersonnel;

/**
 * @author Steve Brown
 * 
 * Set a map of personnel elements for the top right nav bar.
 * 	Key: 	Name of the element.
 * 	Value:	Object representing the element.
 */
public class NavBarPersonnelElements implements NavBarElementStrategy {
	private Map<String, NavBarElement> elements;	
	private WebDriver driver;
	
	public NavBarPersonnelElements(WebDriver driver) {
		this.driver = driver;
		setElements();
	}

	private void setElements(){
		elements = Stream.of(new Object[][] {
			{NavBarEmployeeCreation.ORIGINAL_NAME, new NavBarEmployeeCreation(driver)},
			{NavBarEmployeeCVPayroll.ORIGINAL_NAME, new NavBarEmployeeCvHr(driver)},
			{NavBarEmpGridView.ORIGINAL_NAME, new NavBarEmpGridView(driver)},			
			{NavBarVisualReports.ORIGINAL_NAME, new NavBarVisualReports(driver)},
			{NavBarDakarIntelligence.ORIGINAL_NAME, new NavBarDakarIntelligence(driver)},
			{NavBarOrganisationChart.ORIGINAL_NAME, new NavBarOrganisationChart(driver)},
			{NavBarMyCoLastViewed.ORIGINAL_NAME, new NavBarMyCoLastViewed(driver)},			
			{NavBarNotifications.ORIGINAL_NAME, new NavBarNotifications(driver)},
			{NavBarNewEmployments.ORIGINAL_NAME, new NavBarNewEmployments(driver)},
			{NavBarTerminations.ORIGINAL_NAME, new NavBarTerminations(driver)}			
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1]));		
	}

	@Override
	public Map<String, NavBarElement> getElements() {
		return elements;
	}

	@Override
	public QuickLinks getQuickLinks() {
		return new QuickLinksPersonnel(driver);
	}
}