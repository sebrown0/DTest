/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.CurrentContext;
import object_models.helpers.IFrame;
import object_models.panels.JsPanel;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {
	private JsPanelHeaderBar bar;
	private IFrame iFrame;
	private ContextManager manager;
//	private WebDriver driver;
	
	public StateHeaderPanel(CurrentContext getter, JsPanelHeaderBar bar, IFrame iFrame, WebDriver driver) {
		super(getter, driver);
		this.manager = (ContextManager) getter;
		this.bar = bar;
		this.iFrame = iFrame;
//		this.driver = driver;
	}
	
	//here
	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
//		ClassFieldGetter fieldGetter = new ClassFieldGetter(panel);		
//		Optional<String> panelTitle = fieldGetter.getPanelTitle();
//		
//		//Here
//		panelTitle.ifPresent(title -> {			
//			Optional<ContextState> csCurr = manager.findContext(title);
//			csCurr.ifPresentOrElse(cs -> {
//				driver.switchTo().defaultContent(); 
//				manager.moveToExistingContext(cs);
//				manager.moveToStateInCurrentContext(this); 		
//				bar.getToolBar().switchToPanel(cs.getContextId().getActualId());
//				logger.debug("Switched to panel [" + cs.getContextId() + "]"); 	
//			}, 
//			new PanelException("Could not switch to panel [" + panel + "]"));			
//		});		
	}
	
////	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel, ContextState cs) {		
//	public void switchToExistingPanel(JsPanel panel, ContextState cs) {
//		manager.moveToExistingContext(cs);
//		manager.moveToStateInCurrentContext(this); 		
//		bar.getToolBar().switchToPanel(panel.getContextId().getActualId());
////		bar.getToolBar().switchToPanel(cs.getContextId().getActualId());
//		
//		logger.debug("Switched to panel [" + cs.getContextId() + "]"); 	
//			
//	}

	private void switchToExistingPanel() {
		ContextId contextId = currentContext.getContextId();
//		manager.moveToExistingContext(currentContext); // already done????????
//		manager.moveToStateInCurrentContext(this); 	// already done????????	
		
		Optional<JsPanel> panelSwitcher = manager.getContextThatIsPanel();
		panelSwitcher.ifPresent(s -> {
			bar
			.getToolBar()
			.switchToPanelFromPanel(contextId.getActualId(), s);
			
			System.out.println("Switched to panel [" + contextId+ "]"); // TODO - remove or log 	
			logger.debug("Switched to panel [" + contextId+ "]");	
		});
		
	}
	
	@Override
	public Optional<State> getNext() {		
		return Optional.of(new StateIframe(manager, iFrame, driver));
	}
	
	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		bar.getControlBar().clickClose();		
		closeMyContext();
	}
	
	@Override
	public State switchToMe() {
		System.out.println("StateHeaderPanel->switchToMe"); // TODO - remove or log
		if(manager.isCurrentContext(currentContext) == false){			
//			driver.switchTo().defaultContent(); // TODO - REMOVE AND REINSTATE BELOW!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			switchToExistingPanel();
//			switchToDefaultContent();
//			switchToExistingPanel();		 		
		}
		
		return this;
	}
	
	
	/*
	 * THIS HAS TO BE USED WHEN 
	 * 	SWITCHING CONTEXTS
	 * AND/OR
	 * 	StateHeaderPanel.switchToMe()
	 * 
	 * StateHeaderPanel SHOULD BE THE DEFAULT STATE.
	 * THEN WHEN THE CONTEXT IS LOADED THE PANEL
	 * SHOULD AUTOMATICALLY BE SWITCHED.
	 */
	

	@Override
	public boolean isContextCloser() {
		return true;
	}

	@Override
	public boolean isDefaultState() {
		return true;
	}
}
