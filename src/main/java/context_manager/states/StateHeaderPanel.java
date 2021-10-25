/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.CurrentContext;
import exceptions.PanelException;
import object_models.helpers.ClassFieldGetter;
import object_models.helpers.IFrame;
import object_models.left_menu.parents.MonthlyReports;
import object_models.panels.JsPanel;
import object_models.panels.JsPanelHeaderBar;
import object_models.panels.PanelSwitcher;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {
	private JsPanelHeaderBar bar;
	private IFrame iFrame;
	private ContextManager manager;
	
	public StateHeaderPanel(CurrentContext getter, JsPanelHeaderBar bar, IFrame iFrame, WebDriver driver) {
		super(getter, driver);
		this.manager = (ContextManager) getter;
		this.bar = bar;
		this.iFrame = iFrame;
	}
	
	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
		ClassFieldGetter fieldGetter = new ClassFieldGetter(panel);		
		Optional<String> panelTitle = fieldGetter.getPanelTitle();
		
		panelTitle.ifPresent(title -> {			
			Optional<ContextState> csCurr = manager.findContext(title);
			csCurr.ifPresentOrElse(cs -> {
				manager.moveToExistingContext(cs);
				manager.moveToStateInCurrentContext(this); 		
				bar.getToolBar().switchToPanel(cs.getContextId().getActualId());
				logger.debug("Switched to panel [" + cs.getContextId() + "]"); 	
			}, 
			new PanelException("Could not switch to panel [" + panel + "]"));			
		});		
	}

	private void switchToExistingPanel() {
		ContextId contextId = currentContext.getContextId();
//		manager.moveToExistingContext(currentContext); // already done????????
		manager.moveToStateInCurrentContext(this); 	// already done????????	
		bar
			.getToolBar()
			.switchToPanel(contextId.getActualId());
		System.out.println("Switched to panel [" + contextId+ "]"); // TODO - remove or log 	
		logger.debug("Switched to panel [" + contextId+ "]");
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
		switchToDefaultContent();
		switchToExistingPanel();		 	
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
