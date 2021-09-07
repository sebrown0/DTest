/**
 * 
 */
package object_models.helpers.title;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 * 
 * Check the actual element titles against a list of expected titles.
 */
public class WebElementTitleChecker {
	
	public static boolean allTitlesPresentAndCorrect(List<WebElement> navElements, List<String> expectedTitles) {
		boolean badElement = false;
		int elementCount = 0;
		String actualTitle;		
		
		for (WebElement webElement : navElements) {
			actualTitle = webElement.findElement(By.tagName("i")).getAttribute("data-original-title");
//			System.out.println(actualTitle );
			if(!expectedTitles.contains(actualTitle)) {
				badElement = true;
			}
			elementCount++;
		}
		return (badElement == false && elementCount == expectedTitles.size()) ? true : false;
	}
}
