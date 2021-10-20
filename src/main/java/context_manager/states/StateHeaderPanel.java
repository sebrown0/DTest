/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import context_manager.ContextState;
import object_models.helpers.IFrame;
import object_models.panels.JsPanelControlBar;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {
	private JsPanelControlBar controlBar;
	private IFrame iFrame;
	
	public StateHeaderPanel(ContextState context, JsPanelControlBar controlBar, IFrame iFrame) {
		super(context);
		this.controlBar = controlBar;
		this.iFrame = iFrame;
	}
	
	@Override
	public Optional<State> getNext() {		
		return Optional.of(new StateIframe(super.context, iFrame));
	}
	
	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		controlBar.clickClose();		
	}

	@Override
	public void switchToMe() {
		// TODO Auto-generated method stub
		logger.error("switchToMe not implemented!");
	}

	@Override
	public boolean isContextCloser() {
		return true;
	}

	@Override
	public boolean isDefaultState() {
		return false;
	}
}
