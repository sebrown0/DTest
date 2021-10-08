/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class JSPanelWithIFrame extends JSPanel {
	private IFrame iFrame;
		
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle) {
		super(driver, expectedTitle);	
				
		switchToIFrame(driver, expectedTitle);
	}

	private void switchToIFrame(WebDriver driver, String expectedTitle) {
		By byLocator = By.cssSelector("iframe[title='" + expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
		iFrame.switchUsingLocator();
	}	
	
	protected void switchToIFrame() {
		iFrame.switchUsingLocator();
	}	
	
	public WebElement getIFrameAsElement() {
		return iFrame.getIFrameElement();
	}
		
}
