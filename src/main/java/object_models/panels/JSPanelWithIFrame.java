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
public class JSPanelWithIFrame extends JSPanel implements JsPanelContext {
	private IFrame iFrame;

	public JSPanelWithIFrame(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);
		
		setToIFrame(driver, expectedTitle);
		switchToIFrame();
	}
	
	private void setToIFrame(WebDriver driver, String expectedTitle) {
		By byLocator = By.cssSelector("iframe[title='" + expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
	}	
	
	public void switchToIFrame() {
		iFrame.switchUsingLocator();
		super.contextManager.moveToNextStateInCurrentContext();		
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
