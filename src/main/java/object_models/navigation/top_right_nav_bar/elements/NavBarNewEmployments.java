/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.navigation.NavBarElement;

/**
 * @author Steve Brown
 *
 */
public class NavBarNewEmployments extends NavBarElement {
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[9]/a/i");
	public static final String ORIGINAL_NAME = "New Employments";
	
	public NavBarNewEmployments(WebDriver driver) {
		super(driver, LOCATOR);
	}
	
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}	
}
