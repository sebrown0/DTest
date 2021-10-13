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
	
	public StateHeaderPanel(Context context, Optional<State> prev, JsPanelControlBar controlBar) {
		super(context, prev);
		
		/*
		 * no need to set this state in the context 
		 * as it's the first state so it's set by JsPanel.
		 */
		
		this.controlBar = controlBar;
	}
	
	@Override
	public Optional<State> getNext() {		
		return Optional.of(new StateIframe(super.context, Optional.of(this)));
	}
	
	@Override
	public Optional<State> close() {
		System.out.println("->Close StateHeaderPanel"); // TODO - remove
		controlBar.clickClose();
		return super.getPrev();
	}

	@Override
	public void switchToMe() {
		// TODO Auto-generated method stub
	}


}
