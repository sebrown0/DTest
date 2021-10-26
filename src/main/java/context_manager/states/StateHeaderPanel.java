/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import context_manager.CurrentContext;
import object_models.helpers.IFrame;
import object_models.panels.JsPanelHeaderBar;

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
		//Done by JsPanelToolBar
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
