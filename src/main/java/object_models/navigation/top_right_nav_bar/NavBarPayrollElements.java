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
import object_models.navigation.top_right_nav_bar.elements.NavBarVisualReports;

/**
 * @author SteveBrown
 *
 */
public class NavBarPayrollElements implements NavBarElementStrategy {
	private Map<String, NavBarElement> elements;	
	
	public NavBarPayrollElements(WebDriver driver) {		
		setElements(driver);
	}

	private void setElements(WebDriver driver){
		elements = Stream.of(new Object[][] {
			{NavBarEmployeeCreation.ORIGINAL_NAME, new NavBarEmployeeCreation(driver)},
			{NavBarEmployeeCVPayroll.ORIGINAL_NAME, new NavBarEmployeeCVPayroll(driver)},
			{NavBarEmpGridView.ORIGINAL_NAME, new NavBarEmpGridView(driver)},
			{NavBarVisualReports.ORIGINAL_NAME, new NavBarVisualReports(driver)},
			{NavBarDakarIntelligence.ORIGINAL_NAME, new NavBarDakarIntelligence(driver)},
			{NavBarMyCoLastViewed.ORIGINAL_NAME, new NavBarMyCoLastViewed(driver)}
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1]));		
	}

	@Override
	public Map<String, NavBarElement> getElements() {
		return elements;
	}
}
