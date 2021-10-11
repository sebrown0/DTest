/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.JsPanelContextManager;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class JSPanelWithIFrame extends JSPanel implements JsPanelContext {
	private IFrame iFrame;
	private JsPanelContextManager contextManager;
	
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle) {
		super(driver, expectedTitle);
		
		setContextManager();
		setToIFrame(driver, expectedTitle);
		switchToIFrame();
	}

	private void setContextManager() {
		contextManager = new JsPanelContextManager(this);
		super.setPanelContext(contextManager);
	}
	
	private void setToIFrame(WebDriver driver, String expectedTitle) {
		By byLocator = By.cssSelector("iframe[title='" + expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
	}	
	
	public void switchToIFrame() {
		contextManager.switchToIFrame();
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

	public JsPanelContextManager getContextManager() {
		return contextManager;
	}
		
}
