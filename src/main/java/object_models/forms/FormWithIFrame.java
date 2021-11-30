/**
 * 
 */
package object_models.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.states.StateIframe;
import object_models.helpers.IFrame;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public abstract class FormWithIFrame extends FormModal {
	private IFrame iFrame;
	private String iFrameName;
	
	public FormWithIFrame(CoreData coreData, String expectedTitle, String iFrameName) {
		super(coreData, expectedTitle);	
	
		this.iFrameName = iFrameName;
		
		switchToIFrame(driver, iFrameName);
//		System.out.println("->WOULD HAVE SWITCHED TO IFRAME NOW DO IT FROM CHILD. CHECK ALL CHILDREN ARE DOING IT!!" ); // TODO - remove or log 	
	}
	
	/*
	 * The child object decides when to switch to the iFrame.
	 * This allows the child to load elements before the frame is loaded.
	 */
	protected void switchToIFrame() {
		switchToIFrame(driver, iFrameName);
//		super.setIFrame(iFrame); // Not required unless we want to use it in FormModal 
	}
	
	private void switchToIFrame(WebDriver driver, String iFrameName) {
		iFrame = new IFrame(driver, By.cssSelector("iframe[name='" + iFrameName + "']"));
		super.contextManager
			.setNextState(new StateIframe(getMyContext(), iFrame, driver))
			.moveToNextStateInCurrentContext()
			.switchToMe();		
	}
		
	public WebElement getIFrameElement() {
		return iFrame.getIFrameElement();
	}

	/*
	 * THIS IS IN JsPanelWithIframe for JsPanel 
	 */
//	public void setContextState() {
//		By byLocator = By.cssSelector("iframe[title='" + super.expectedTitle + "']");
//		iFrame = new IFrame(driver, byLocator);
//		System.out.println("->SETTING CONTEXT STATE IN FORM WITH I F" ); // TODO - remove or log 	
//		ContextState con = contextManager.getCurrentContext();			 	
//		State header = new StateHeaderForm(con, formContainerElement, byLocator, driver);		
//		con.setState(header);
//	}
	
	@Override 	// StateFactorySetter
	public IFrame getIFrame() {
		return iFrame;
	}
}
