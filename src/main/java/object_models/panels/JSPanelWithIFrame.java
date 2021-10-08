/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class JSPanelWithIFrame extends JSPanel implements JsPanelContext {
	private IFrame iFrame;
	protected JsPanelContextManager contextManager;
	
	public JSPanelWithIFrame(WebDriver driver, String expectedTitle) {
		super(driver, expectedTitle);
		
		setContextManager();
		switchToIFrame(driver, expectedTitle);
	}

	private void setContextManager() {
		contextManager = new JsPanelContextManager(this);
		super.setPanelContext(contextManager);
	}
	
	private void switchToIFrame(WebDriver driver, String expectedTitle) {
		By byLocator = By.cssSelector("iframe[title='" + expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);
		iFrame.switchUsingLocator();
	}	
	
	protected void switchToIFrame() {
		contextManager.switchToIFrame();
//		super.frameOrPanel = FrameOrPanel.FRAME;
//		iFrame.switchUsingLocator();
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
