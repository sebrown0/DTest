/**
 * 
 */
package object_models.navigation.top_right_nav_bar.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.helpers.ChildElement;
import object_models.navigation.NavBarElement;

/**
 * @author SteveBrown
 *
 */
public class NavBarTerminations extends NavBarElement {
	private static final By LOCATOR = By.xpath("html/body/form/header/ul[4]/li[10]/a/i");
	public static final String ORIGINAL_NAME = "Terminations";
	
	public NavBarTerminations(WebDriver driver) {
		super(driver, LOCATOR);
	}
	
	@Override
	public String getOriginalName() {
		return ORIGINAL_NAME;
	}

	@Override
	public ChildElement clickElement() {
		// TODO Auto-generated method stub
		return null;
	}	
}