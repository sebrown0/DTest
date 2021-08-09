/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import org.openqa.selenium.WebDriver;

import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;

/**
 * @author Steve Brown
 *
 */
public class TopRightNavBar {
	private TopRightNavBarElements navBarElements; 
	private NavBarClicker navBarClicker;
	
	public TopRightNavBar(WebDriver driver, NavBarElementStrategy elementStrategy) {
		navBarElements = new TopRightNavBarElements(driver, elementStrategy);
		navBarClicker = new NavBarClicker(elementStrategy);
	}
	
	public boolean isAllElementsCorrect() {
		return navBarElements.allElementsPresentAndCorrect();
	}
	
	public NavBarClicker clickElement() {
		return navBarClicker;
	}
		
//	public void clickNavbarElement(NavBarElement elem) {
//		elem.clickElement(navBarElements.getNavBarElement(elem));
//	}

	public TopRightNavBarElements getNavBarElements() {
		return navBarElements;
	}
		
}
