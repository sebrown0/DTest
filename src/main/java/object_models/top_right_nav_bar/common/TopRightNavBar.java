/**
 * 
 */
package object_models.top_right_nav_bar.common;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import object_models.helpers.title.WebElementTitleChecker;
import object_models.top_right_nav_bar.all_elements.NavBarElementGetter;
import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;
import object_models.top_right_nav_bar.quick_links.QuickLinks;

/**
 * @author Steve Brown
 *
 */
public class TopRightNavBar implements ElementChecker, NavBarElementGetter {
	private WebDriver driver;
	private TopRightNavBarElements navBarElements; 
	private QuickLinks quickLinks;
		
	public TopRightNavBar(WebDriver driver) {
		this.driver = driver;
	}
	
	public void loadElements(NavBarElementStrategy elementStrategy) {
		navBarElements = new TopRightNavBarElements(driver, elementStrategy);
		quickLinks = elementStrategy.getQuickLinks();
	}
			
	@Override
	public boolean checkElementTitles() {
		return WebElementTitleChecker.allTitlesPresentAndCorrect(navBarElements.getNavElements(), navBarElements.getNavBarElementTitles());
	}
	
	/*
	 * Getters below
	 */
	public TopRightNavBarElements getNavBarElements() {
		return navBarElements.locateNavBar();
	}
	public QuickLinks getQuickLinks() {
		return quickLinks;
	}		

	@Override
	public Optional<NavBarElement> getNavBarElement(String elementName) {		
		return Optional.ofNullable(navBarElements.getElement(elementName));
	}
}
