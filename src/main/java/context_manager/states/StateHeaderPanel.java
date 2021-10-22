/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.CurrentContextGetter;
import exceptions.PanelException;
import object_models.helpers.ClassFieldGetter;
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
	
	public StateHeaderPanel(CurrentContextGetter getter, JsPanelHeaderBar bar, IFrame iFrame, WebDriver driver) {
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
	
	@Override
	public Optional<State> getNext() {		
		return Optional.of(new StateIframe(manager, iFrame, driver));
	}
	
	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		bar.getControlBar().clickClose();		
	}
	
	/*
	 * HAVE TO GO THRU EACH STATE AND CHECK THAT THEY 
	 * PERFORM THE CORRECT ACTIONS FOR close & switchToMe.
	 */

	/*
	 * in context -> with this state
	 * has the context and state been set as current?
	 * 
	 */
	@Override
	public State switchToMe() {
		currentContext.switchToDefaultContent();
		System.out.println("StateHeaderPanel->switchToMe"); // TODO - remove or log 	
		return this;
	}

	@Override
	public boolean isContextCloser() {
		return true;
	}

	@Override
	public boolean isDefaultState() {
		return true;
	}
}
