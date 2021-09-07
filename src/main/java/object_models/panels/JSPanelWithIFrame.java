/**
 * 
 */
package object_models.panels;

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
		iFrame = new IFrame(driver, expectedTitle);
		iFrame.switchUsingTitle();
	}
		
	public WebElement getIFrameElement() {
		return iFrame.getIFrameElement();
	}
}
