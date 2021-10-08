/**
 * 
 */
package object_models.panels;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class JsPanelContextManager {
//	private JsPanelContext panelContext;
	private FrameOrPanel frameOrPanel = FrameOrPanel.FRAME;
	private JSPanelWithIFrame panel;
	private boolean isChildLoaded = false;
	private WebDriver driver;
	
	public static enum FrameOrPanel {
		FRAME, PANEL
	}
	
	public JsPanelContextManager(JsPanelContext panelContext) {
//		this.panelContext = panelContext;
		panel = panelContext.getPanelWithIFrame();
		driver = panel.driver;
	}
	
	public void switchToMe() {
		panel.driver.switchTo().defaultContent();
		panel.switchToIFrame();		
		isChildLoaded = false;
		frameOrPanel = FrameOrPanel.FRAME;
	}
	
	public void switchBackToFormIfNecessary() {
		if(isChildLoaded == true) {
			switchToMe();
		}			
	}
		
	public void loadFrameIfNecessary() {
		if(frameOrPanel == FrameOrPanel.PANEL) {
			panel.switchToIFrame();
		}
	}
	
	protected void switchToIFrame() {
		panel.getIframe().switchUsingLocator();
		frameOrPanel = FrameOrPanel.FRAME;
	}	
	
	public void switchToPanelIfNecessary() {		
		if(frameOrPanel == FrameOrPanel.FRAME) {
//			System.out.println("->is frame going to panel");
			driver.switchTo().defaultContent();
			frameOrPanel = FrameOrPanel.PANEL;	
		}		
	}
	

	public FrameOrPanel getFrameOrPanel() {
		return frameOrPanel;
	}

}
