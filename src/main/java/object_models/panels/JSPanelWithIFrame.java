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
import context_manager.states.StateIframe;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public abstract class JSPanelWithIFrame extends JSPanel implements JsPanelContext {
	private IFrame iFrame;
		
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);
		
		
//		setToIFrame(driver, expectedTitle);
		switchToIFrame();
	}
	
	public void setContextState() {
		By byLocator = By.cssSelector("iframe[title='" + super.expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
		
		ContextState con = contextManager.getCurrentContext();			 	
		State header = new StateHeaderPanel(con, super.getHeaderBar().getControlBar(), iFrame);		
		con.setState(header);
	}
	
//	private void setToIFrame(WebDriver driver, String expectedTitle) {
//		By byLocator = By.cssSelector("iframe[title='" + expectedTitle + "']");
//		iFrame = new IFrame(driver, byLocator);
//	}	
//	
	private void switchToIFrame() {
		super.contextManager
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

//	public ZZZ_ContextManager getContextManager() {
////		return contextManager;
//		return null;
//	}
		
}
