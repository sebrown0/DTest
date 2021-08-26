/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import object_models.helpers.WebElementTitleChecker;
import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementGetter;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;

/**
 * @author Steve Brown
 *
 */
public class TopRightNavBar implements ElementChecker, NavBarElementGetter {
	private TopRightNavBarElements navBarElements; 
	private QuickLinks quickLinks;
	
	public TopRightNavBar(WebDriver driver, NavBarElementStrategy elementStrategy) {
		navBarElements = new TopRightNavBarElements(driver, elementStrategy);
		quickLinks = elementStrategy.getQuickLinks();
	}
		
	public void clickElement(String elementName) {
		navBarElements.getElement(elementName);
	}
	
	@Override
	public boolean checkElementTitles() {
		return WebElementTitleChecker.allTitlesPresentAndCorrect(navBarElements.getNavElements(), navBarElements.getNavBarElementTitles());
	}
	
	/*
	 * Getters below
	 */
	public TopRightNavBarElements getNavBarElements() {
		return navBarElements;
	}
	public QuickLinks getQuickLinks() {
		return quickLinks;
	}		

	@Override
	public Optional<NavBarElement> getNavBarElement(String elementName) {		
		return Optional.ofNullable(navBarElements.getElement(elementName));
	}
}
