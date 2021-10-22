/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateHeaderPanel;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public abstract class JSPanelWithIFrame extends JsPanel implements JsPanelContext {
	private IFrame iFrame;
		
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);
	
		switchToIFrame();
	}
	
	public void setContextState() {
		By byLocator = By.cssSelector("iframe[title='" + super.expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
		
		ContextState con = manager.getCurrentContext();			 	
		State header = new StateHeaderPanel(manager, super.getHeaderBar(), iFrame);		
		con.setState(header);
	}
	
	private void switchToIFrame() {
		super.manager
			.moveToNextStateInCurrentContext()
			.switchToMe();		
	}	
	
	public IFrame getIframe() {
		return iFrame;
	}
	
	public WebElement getIFrameAsElement() {
		return iFrame.getIFrameElement();
	}

	@Override
	public JSPanelWithIFrame getPanelWithIFrame() {
		return this;
	}
		
}
