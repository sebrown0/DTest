/**
 * 
 */
package context_manager;

import org.openqa.selenium.WebDriver;

import object_models.panels.JSPanelWithIFrame;
import object_models.panels.JsPanelContext;

/**
 * @author Steve Brown
 *
 */
public class ZZZ_ContextManager {
	private FrameOrPanel frameOrPanel = FrameOrPanel.FRAME;
	private JSPanelWithIFrame panel;
	private boolean isChildLoaded = false;
	private WebDriver driver;
	
	/*
	 * HAS TO HAVE 
	 *  DRIVER FROM HOMEPAGE
	 *  CURRENT PANEL, FORM, IFRAME ETCs
	 *  
	 *  HOW IS EACH REPRESENTED? ContainerAction?
	 *  WHAT DO WE NEED FROM EACH?
	 */
	public static enum FrameOrPanel {
		FRAME, PANEL
	}
	
	public ZZZ_ContextManager(JsPanelContext panelContext) {
		panel = panelContext.getPanelWithIFrame();
		driver = panel.getDriver();
	}
	
	public void switchToMe() {
		panel.getDriver().switchTo().defaultContent();
		panel.switchToIFrame();		
		isChildLoaded = false;
		frameOrPanel = FrameOrPanel.FRAME;
	}
	
	public void childIsLoaded() {
		isChildLoaded = true;
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
	
	public void switchToIFrame() {
		panel.getIframe().switchUsingLocator();
		frameOrPanel = FrameOrPanel.FRAME;
	}	
	
	public void switchToPanelIfNecessary() {		
		if(frameOrPanel == FrameOrPanel.FRAME) {
			driver.switchTo().defaultContent();
			frameOrPanel = FrameOrPanel.PANEL;	
		}		
	}
	
	public FrameOrPanel getFrameOrPanel() {
		return frameOrPanel;
	}

}
