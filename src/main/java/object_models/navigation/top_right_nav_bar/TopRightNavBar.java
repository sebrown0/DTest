/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import org.openqa.selenium.WebDriver;

import object_models.helpers.WebElementTitleChecker;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.nav_bar_clicker.NavBarClicker;

/**
 * @author Steve Brown
 *
 */
public class TopRightNavBar implements ElementChecker {
	private TopRightNavBarElements navBarElements; 
	private NavBarClicker navBarClicker;
	
	public TopRightNavBar(WebDriver driver, NavBarElementStrategy elementStrategy) {
		navBarElements = new TopRightNavBarElements(driver, elementStrategy);
//		navBarClicker = new NavBarClicker(elementStrategy);
		navBarClicker = elementStrategy.getNavBarClicker();
	}
		
//	public NavBarClicker clickElement() {
//		return navBarClicker;
//	}
//		
//	public void clickNavbarElement(String originalName) {
//		NavBarElement elem = navBarElements.getElement(originalName);		
//		System.out.println("clickNavbarElement = " + elem.getOriginalName());
////		elem.clickElement();
//	}

	/*
	 * Getters below
	 */
	public TopRightNavBarElements getNavBarElements() {
		return navBarElements;
	}
	
	public NavBarClicker getNavBarClicker() {
		return navBarClicker;
	}

	@Override
	public boolean checkElementTitles() {
		return WebElementTitleChecker.allTitlesPresentAndCorrect(navBarElements.getNavElements(), navBarElements.getNavBarElementTitles());
	}
}
