/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.ContextState;
import object_models.helpers.IFrame;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {
	private JsPanelHeaderBar bar;
	private IFrame iFrame;
//	private ContextManager manager;
	
	public StateHeaderPanel(ContextState cs, JsPanelHeaderBar bar, IFrame iFrame, WebDriver driver) {
		super(cs, driver);
//		this.manager = cs.getContextManager();
		this.bar = bar;
		this.iFrame = iFrame;
	}
	
	@Override
	public Optional<State> getNewNextState() {		
//		return Optional.empty();
		return Optional.of(new StateIframe(myContext, iFrame, driver));
	}
	
	@Override
	public void close() { 	
		logger.debug("Closing state [" + this + "]");		
		switchToDefaultContent();		
		bar.getControlBar().clickClose();		
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
