/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public class TopRightNavBar {
//	private WebDriver driver;
	private TopRightNavBarElements navBarElements; 
	
	public TopRightNavBar(WebDriver driver) {	
//		this.driver = driver;
		navBarElements = new TopRightNavBarElements(driver);
	}
	
	public boolean isAllElementsCorrect() {
		return navBarElements.allElementsPresentAndCorrect();
	}
	
}
