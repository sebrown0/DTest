/**
 * 
 */
package object_models.panels;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.helpers.ChildElement;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCreationWizard implements ChildElement {	
	private WebDriver driver;	
	private IFrame iFrame;
	public static final String PANEL_TITLE = "Employee Creation Wizard";
	
	public EmployeeCreationWizard(WebDriver driver) {
		this.driver = driver;
		iFrame = new IFrame(driver, PANEL_TITLE);		
		waitForLoad();
		switchToIFrame();
	}

	public void printInputBoxes() {
		WebElement el = driver.findElement(By.cssSelector("#wizard > div.content.clearfix"));
		List<WebElement> inputs = el.findElements(By.tagName("input"));
		for (WebElement e : inputs) {
			System.out.println("->" + el.getAttribute("placeholder"));
		}
//		System.out.println("->" + el.getAttribute("class"));
	}
//	@Override
//	public By getTitleLocator() {
//		return By.className("jsPanel-title");
//	}
//
//	@Override
//	public String getExpectedTitle() {
//		return PANEL_TITLE;
//	}
//
//	@Override
//	public String getActualTitle() {
//		return driver.findElement(this.getTitleLocator()).getAttribute("innerHTML");
//	}
//
//	@Override
	public void switchToIFrame() {
		iFrame.switchUsingTitle();
	}
//
//	@Override
	public void waitForLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.className("jsPanel-title"), "innerHTML", PANEL_TITLE));		
	}
//
//	@Override
//	public void close() {
//		WebElement we = driver.findElement(By.cssSelector(".jsPanel-btn.jsPanel-btn-close"));
//		we.click();		
//	}

}
