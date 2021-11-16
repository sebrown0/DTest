/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public abstract class JsPanelWithIFrame extends JsPanel implements JsPanelContext {
	private IFrame iFrame;
		
	public JsPanelWithIFrame(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);
	
		switchToIFrame();
	}
	
	@Override //JsPanel
	protected void setContextState() {
		By byLocator = By.cssSelector("iframe[title='" + super.expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);		
		super.getHeaderBar(); // CAUSES NEW STATE HEADER TO BE CREATED & ADDED TO THE QUEUE. TODO - remove this side effect.		
	}
	
	private void switchToIFrame() {		
		super.manager
			.moveToNextStateInCurrentContext()
			.switchToMe();	 	
		super.manager.setLatestCallingStateToCurrent();
	}	
		
	public WebElement getIFrameAsElement() {
		return iFrame.getIFrameElement();
	}

	@Override
	public JsPanelWithIFrame getPanelWithIFrame() {
		return this;
	}
	
	@Override 	// StateFactorySetter
	public IFrame getIFrame() {
		return iFrame;
	}
		
}
