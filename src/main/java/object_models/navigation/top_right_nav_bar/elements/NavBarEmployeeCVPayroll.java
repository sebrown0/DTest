/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.ChildElement;
import object_models.helpers.HasChild;
import object_models.navigation.NavBarElement;
import object_models.panels.EmployeeCV;

/**
 * @author Steve Brown
 *
 */
public class NavBarEmployeeCVPayroll extends NavBarElement implements HasChild {
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[2]/a/i");  
//	private static final By LOCATOR = By.cssSelector(".fa.fa-user[title.data-original-title='Employee CV']");
	
	public static final String ORIGINAL_NAME = "Employee CV";
	
	public NavBarEmployeeCVPayroll(WebDriver driver) {
		super(driver, LOCATOR);
	}
		
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}

	@Override
	public ChildElement loadChild() {
		super.clickElement();
		return new EmployeeCV(super.driver, "Employee Payroll CV");	
	}	

}
