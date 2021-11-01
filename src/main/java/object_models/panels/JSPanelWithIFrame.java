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
		super.getHeaderBar(); // CAUSES NEW STATE HEADER TO BE CREATED & ADDED TO THE QUEUE
		
//		ContextState con = manager.getCurrentContext();			 	
		// super.getHeaderBar() CAUSES NEW STATE HEADER TO BE CREATED
//		JsPanelHeaderBar hDRBar = super.getHeaderBar();
//		State hdr = super.getMyContext().getState();
		
//		State header = new StateHeaderPanel(con, super.getHeaderBar(), iFrame, driver);		
//		con.setState(header);
				
//		con.setState(hdr);
	}
	
	private void switchToIFrame() {
		super.manager
			.moveToNextStateInCurrentContext()
			.switchToMe();		
	}	
		
	public WebElement getIFrameAsElement() {
		return iFrame.getIFrameElement();
	}

	@Override
	public JSPanelWithIFrame getPanelWithIFrame() {
		return this;
	}
	
	@Override 	// StateFactorySetter
	public IFrame getIFrame() {
		return iFrame;
	}
		
}
