/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.By;

import object_models.helpers.IFrame;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Remove getIFrameAsElement().
 * @since 1.0
 *
 */
public abstract class JsPanelWithIFrame extends JsPanel implements JsPanelContext { //, IframeSwitcher {
	private IFrame iFrame;
		
	public JsPanelWithIFrame(CoreData coreData, String expectedTitle) {
		super(coreData, expectedTitle);
	
		loadIframe();
	}
	
	@Override //JsPanel
	protected void setContextState() {
		By byLocator = By.cssSelector("iframe[title='" + super.expectedTitle + "']");
		iFrame = new IFrame(driver, byLocator);		
		super.getHeaderBar(); // CAUSES NEW STATE HEADER TO BE CREATED & ADDED TO THE QUEUE. TODO - remove this side effect.		
	}
	
	private void loadIframe() {		
		super.manager
			.moveToNextStateInCurrentContext()
			.switchToMe();	 	
		super.manager.setLatestCallingStateToCurrent();
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
