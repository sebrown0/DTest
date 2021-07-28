/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 *
 */
public class TopRightNavBarElements {
	private WebDriver driver;
	
	private static final List<String> NAV_LINK_TITLES = Arrays.asList( 
			"Employee Grid View",
			"Employee CV",
			"Employee Grid View",
			"Visual Reports",
			"Dakar Intelligence",
			"Organisation Chart",
			"My Company / Last Viewed",
			"Notifications",
			"New Employments",			
			"Terminations");
	
	public TopRightNavBarElements(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean allElementsPresentAndCorrect() {
		return navElementCheck();
	}
	
	private boolean navElementCheck() {
		boolean badElement = false;
		int elementCount = 0;
		String originalTitle;
		List<WebElement> navElements = driver.findElements(By.cssSelector(".nav-item.dropdown.dropdown-slide.d-md-down-none"));
		
		for (WebElement webElement : navElements) {
			originalTitle = webElement.findElement(By.tagName("i")).getAttribute("data-original-title");
			if(!NAV_LINK_TITLES.contains(originalTitle)) {
				badElement = true;
			}
			elementCount++;
		}
		return (badElement == false && elementCount == NAV_LINK_TITLES.size()) ? true : false;
	}	
}
