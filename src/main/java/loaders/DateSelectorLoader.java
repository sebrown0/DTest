/**
 * 
 */
package loaders;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Steve Brown
 *
 */
public class DateSelectorLoader {	
	@FindBy(css = "input[title='Date selector']")
  private List<WebElement> dateSelectors;
	
	
	public DateSelectorLoader(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void printSelectors() {
		for (WebElement webElement : dateSelectors) {
			System.out.println("sel -> " + webElement.getAttribute("placeholder"));
		}
	}
}
