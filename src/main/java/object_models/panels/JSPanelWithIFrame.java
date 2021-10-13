/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import context_manager.ZZZ_ContextManager;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class JSPanelWithIFrame extends JSPanel implements JsPanelContext {
	private IFrame iFrame;
//	private ZZZ_ContextManager contextManager;
	
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);
		
//		setContextManager();
//		contextManager.setContext(new ContextPanel());
		setToIFrame(driver, expectedTitle);
		switchToIFrame();
	}

//	private void setContextManager() {
//		contextManager = new ZZZ_ContextManager(this);
//		super.setPanelContext(contextManager);
//	}
	
	private void setToIFrame(WebDriver driver, String expectedTitle) {
		By byLocator = By.cssSelector("iframe[title='" + expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
	}	
	
	public void switchToIFrame() {
		iFrame.switchUsingLocator();
//		super.contextManager.getContext().setState(new StateIframe(contextManager));
		
//		contextManager.switchToIFrame();
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

	public ZZZ_ContextManager getContextManager() {
//		return contextManager;
		return null;
	}
		
}
