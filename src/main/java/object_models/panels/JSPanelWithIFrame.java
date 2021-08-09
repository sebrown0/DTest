/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.WebDriver;

import object_models.helpers.IFrame;
import object_models.helpers.Title;

/**
 * @author Steve Brown
 *
 */
public class JSPanelWithIFrame extends JSPanel {
	private IFrame iFrame;
	
	public JSPanelWithIFrame(WebDriver driver, Title title) {
		super(driver, title);
		iFrame = new IFrame(driver, title.getExpectedTitle());
	}

	public void switchToIFrame() {
		iFrame.switchUsingTitle();
	}
}
