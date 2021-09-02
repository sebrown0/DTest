/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author SteveBrown
 *
 */
public class LeftMenuFactory {
	private List<WebElement> anchors;
	
	@FindBy(css = "#nav-accordion")
	private WebElement nevAccordion;
	
	public LeftMenuFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		anchors = nevAccordion.findElements(By.tagName("a"));
	}
		
	public List<WebElement> getList() {
		return anchors;
	}
	
}
