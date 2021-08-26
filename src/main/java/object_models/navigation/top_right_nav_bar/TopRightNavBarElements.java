/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;

/**
 * @author Steve Brown
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
		navElements = driver.findElements(By.cssSelector(".nav-item.dropdown.dropdown-slide.d-md-down-none"));
//		quickLinks = new QuickLinks(driver);
	}
	
//	public Optional<NavBarElement> getNavBarElement(String elementTitle) {
//		NavBarElement navBarElement = null;
//		if(navBarElements.containsKey(elementTitle)) {
//			navBarElement = navBarElements.get(elementTitle);
//		}
//		return Optional.of(navBarElement);
//	}
	
//	public WebElement getNavBarElement(NavBarElement elem) {
//		WebElement webElem = null;
//		String originalTitle;
////		WebDriverWait wait = new WebDriverWait(driver, 10);
//		
//		for (WebElement webElement : navElements) {
//			originalTitle = webElement.findElement(By.tagName("i")).getAttribute("data-original-title");			
////			System.out.println("originalTitle = [" + originalTitle + "] Looking for: [" + elem.getOriginalName() + "]");
//			if(originalTitle.equalsIgnoreCase(elem.getOriginalName())) {
//				System.out.println("found it");
//				webElem = webElement;
//				break;
//			}			
//		}
//		return webElem;
////		return wait.until(ExpectedConditions.elementToBeClickable(webElem));
////		return null;
//	}

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
