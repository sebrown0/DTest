/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class TopRightNavBar {
	private TopRightNavBarElements navBarElements; 
	
	public TopRightNavBar(WebDriver driver) {
		navBarElements = new TopRightNavBarElements(driver);
	}
	
	public boolean isAllElementsCorrect() {
		return navBarElements.allElementsPresentAndCorrect();
	}
	
}
