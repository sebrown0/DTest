/**
 * 
 */
package object_models.navigation.left_side_menu;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class LeftMenu {
	protected WebDriver driver;
//	private String moduleName;
	
	public LeftMenu(WebDriver driver, String moduleName) {
		this.driver = driver;
//		this.moduleName = moduleName;
	}
}
