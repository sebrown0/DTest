/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.WebDriver;

import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeCreationWizard {	
	private WebDriver driver;	
	private IFrame iFrame;
	public static final String PANEL_TITLE = "Employee Creation Wizard";
	
	public EmployeeCreationWizard(WebDriver driver) {
		this.driver = driver;
		iFrame = new IFrame(driver, PANEL_TITLE);		
//		waitForLoad();
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
//	public void switchToIFrame() {
//		iFrame.switchUsingTitle();
//	}
//
//	@Override
//	public void waitForLoad() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.attributeContains(By.className("jsPanel-title"), "innerHTML", PANEL_TITLE));		
//	}
//
//	@Override
//	public void close() {
//		WebElement we = driver.findElement(By.cssSelector(".jsPanel-btn.jsPanel-btn-close"));
//		we.click();		
//	}

}
