/**
 * 
 */
package object_models.top_right_nav_bar.payroll;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

import object_models.modules.common.nav.NavBarElement;
import object_models.modules.common.nav.nav_bar_elements.NavBarDakarIntelligence;
import object_models.modules.common.nav.nav_bar_elements.NavBarElementStrategy;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmpGridView;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCVPayroll;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCreation;
import object_models.modules.common.nav.nav_bar_elements.NavBarMyCoLastViewed;
import object_models.modules.common.nav.nav_bar_elements.NavBarNewEmployments;
import object_models.modules.common.nav.nav_bar_elements.NavBarNotifications;
import object_models.modules.common.nav.nav_bar_elements.NavBarTerminations;
import object_models.modules.common.nav.nav_bar_elements.NavBarUserAvatar;
import object_models.modules.common.nav.nav_bar_elements.NavBarUserManagment;
import object_models.modules.common.nav.nav_bar_elements.NavBarVisualReports;
import object_models.modules.common.nav.quick_links.QuickLinks;
import object_models.modules.common.nav.quick_links.QuickLinksPayroll;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 * 
 * Set a map of payroll elements for the top right nav bar.
 * 	Key: 	Name of the element.
 * 	Value:	Object representing the element.
 */
public class ZZZ_NavBarPayrollElements implements NavBarElementStrategy {
	private Map<String, NavBarElement> elements;	
	private WebDriver driver;
	private CoreData coreData;
	
	public ZZZ_NavBarPayrollElements(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		setElements();
	}

	private void setElements(){
		elements = Stream.of(new Object[][] {
			{NavBarEmployeeCreation.ORIGINAL_NAME, new NavBarEmployeeCreation(coreData)},
			{NavBarEmployeeCVPayroll.ORIGINAL_NAME, new NavBarEmployeeCVPayroll(coreData)},
			{NavBarEmpGridView.ORIGINAL_NAME, new NavBarEmpGridView(coreData)},
			{NavBarVisualReports.ORIGINAL_NAME, new NavBarVisualReports(coreData)},
			{NavBarDakarIntelligence.ORIGINAL_NAME, new NavBarDakarIntelligence(coreData)},
			{NavBarMyCoLastViewed.ORIGINAL_NAME, new NavBarMyCoLastViewed(coreData)},			
			{NavBarNotifications.ORIGINAL_NAME, new NavBarNotifications(coreData)},
			{NavBarNewEmployments.ORIGINAL_NAME, new NavBarNewEmployments(coreData)},
			{NavBarTerminations.ORIGINAL_NAME, new NavBarTerminations(coreData)},
			{NavBarUserManagment.ORIGINAL_NAME, new NavBarUserManagment(coreData)},
			{NavBarUserAvatar.ORIGINAL_NAME, new NavBarUserAvatar(coreData)}
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1]));		
	}

	@Override
	public Map<String, NavBarElement> getElements() {
		return elements;
	}

	@Override
	public QuickLinks getQuickLinks() {
		return new QuickLinksPayroll(driver);
	}
}
