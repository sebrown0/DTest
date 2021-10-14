/**
 * 
 */
package context_manager;

import java.util.Optional;

import object_models.panels.JsPanelControlBar;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {
	private JsPanelControlBar controlBar;
	
	public StateHeaderPanel(ContextState context, Optional<State> prev, JsPanelControlBar controlBar) {
		super(context, prev);				
		this.controlBar = controlBar;
	}
	
	@Override
	public Optional<State> getNext() {		
		return Optional.of(new StateIframe(super.context, Optional.of(this)));
	}
	
	@Override
	public Optional<State> close() {
		// have to goto the prev xon from here.
		logger.debug("Closing state");
		controlBar.clickClose();
		/*
		 * Return empty to indicate that the context is closed.
		 * The context manager can then remove it.
		 */
		return Optional.empty();
//		return super.getPrev();
	}

	@Override
	public void switchToMe() {
		// TODO Auto-generated method stub
		logger.error("switchToMe not implemented!");
	}

}
