/**
 * 
 */
package object_models.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import context_manager.states.StateIframe;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public abstract class FormWithIFrame extends FormModal {
	private IFrame iFrame;
		
	public FormWithIFrame(WebDriver driver, String expectedTitle, String iFrameName, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);	
				
		switchToIFrame(driver, iFrameName);
	}
	
	private void switchToIFrame(WebDriver driver, String iFrameName) {
		iFrame = new IFrame(driver, By.cssSelector("iframe[name='" + iFrameName + "']"));
		super.contextManager
			.setNextState(new StateIframe(getMyContext(), iFrame, driver))
			.moveToNextStateInCurrentContext()
			.switchToMe();		
	}
		
	public WebElement getIFrameElement() {
		return iFrame.getIFrameElement();
	}
	
	@Override 	// StateFactorySetter
	public IFrame getIFrame() {
		return iFrame;
	}
}
