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
public class JSPanelWithIFrame extends JSPanel {
	private IFrame iFrame;
		
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle) {
		super(driver, expectedTitle);	
		iFrame = new IFrame(driver, expectedTitle);
	}

	public void switchToIFrame() {
		iFrame.switchUsingTitle();
	}
}
