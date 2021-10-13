/**
 * 
 */
package object_models.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class FormWithIFrame extends FormModal {
	private IFrame iFrame;
		
	public FormWithIFrame(WebDriver driver, String expectedTitle, String iFrameName, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);	
				
		switchToIFrame(driver, iFrameName);
	}

	private void switchToIFrame(WebDriver driver, String iFrameName) {
		iFrame = new IFrame(driver, By.cssSelector("iframe[name='" + iFrameName + "']"));
		iFrame.switchUsingLocator();
	}
	
	public WebElement getIFrameElement() {
		return iFrame.getIFrameElement();
	}
}
