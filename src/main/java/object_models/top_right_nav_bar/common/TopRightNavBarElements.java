/**
 * 
 */
package object_models.top_right_nav_bar.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.top_right_nav_bar.all_elements.NavBarElementStrategy;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0 
 * 
 * All the components of the top right nav bar.
 * The elements are loaded according to the NavBarElementStrategy.
 * For example, for the payroll nav bar the NavBarPayrollElements are loaded.
 * 
 */
public class TopRightNavBarElements {
	private WebDriver driver;
	private List<WebElement> navElements;	
	private Map<String, NavBarElement> navBarElements;
			
	public TopRightNavBarElements(WebDriver driver, NavBarElementStrategy elementStrategy) {		
		this.driver = driver;		
		loadElements(elementStrategy);
	}
	
	private void loadElements(NavBarElementStrategy elementStrategy) {
		navBarElements = elementStrategy.getElements();		
		locateNavBar();
//		quickLinks = new QuickLinks(driver);
	}
	
	public TopRightNavBarElements locateNavBar() {
		navElements = driver.findElements(By.cssSelector(".nav-item.dropdown.dropdown-slide.d-md-down-none"));
		return this;
	}
	
	/*
	 * Getters below
	 */	
	public List<String> getNavBarElementTitles(){
		return new ArrayList<>(navBarElements.keySet());
	}
	public NavBarElement getElement(String elementName) {
		return navBarElements.get(elementName);
	}
	public List<WebElement> getNavElements() {
		return navElements;
	}
}
