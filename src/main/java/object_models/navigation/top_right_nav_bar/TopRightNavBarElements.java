/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinks;

/**
 * @author SteveBrown
 * 
 */
public class TopRightNavBarElements {
	private WebDriver driver;
	private List<WebElement> navElements;
	private QuickLinks quickLinks;
	private Map<String, NavBarElement> navBarElements;
	
	private static final List<String> NAV_LINK_TITLES = Arrays.asList( 
			"Employee Creation",
			"Employee CV",
			"Employee Grid View",
			"Visual Reports",
			"Dakar Intelligence",
			"Organisation Chart",
			"My Company / Last Viewed",
			"Notifications",
			"New Employments",			
			"Terminations");	
	
	public TopRightNavBarElements(WebDriver driver, NavBarElementStrategy elementStrategy) {		
		this.driver = driver;
		navBarElements = elementStrategy.getElements();
		loadElements();
	}

	private void loadElements() {
//		navElements = driver.findElements(By.cssSelector(".nav-item.dropdown.dropdown-slide.d-md-down-none"));
		quickLinks = new QuickLinks(driver);
	}
	
	public boolean allElementsPresentAndCorrect() {
		return navElementCheck();
	}
	
	private boolean navElementCheck() {
		boolean badElement = false;
		int elementCount = 0;
		String originalTitle;		
		
		for (WebElement webElement : navElements) {
			originalTitle = webElement.findElement(By.tagName("i")).getAttribute("data-original-title");
			System.out.println(originalTitle );
			if(!NAV_LINK_TITLES.contains(originalTitle)) {
				badElement = true;
			}
			elementCount++;
		}
		return (badElement == false && elementCount == NAV_LINK_TITLES.size()) ? true : false;
	}	
	
	public NavBarElement getElement(String elementName) {
		return navBarElements.get(elementName);
	}
	
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
//		return wait.until(ExpectedConditions.elementToBeClickable(webElem));
//		return null;
//	}

	public QuickLinks getQuickLinks() {
		return quickLinks;
	}
}
