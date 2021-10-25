/**
 * 
 */
package context_manager;

import java.util.Optional;

import context_manager.states.State;

/**
 * @author SteveBrown
 *
 */
public class ContextUpdater {
	private StateManager manager;
	
	public ContextUpdater(StateManager manager) {
		this.manager = manager;
	}

	public void updateContextAfterStateDeletion(State state) {
		if(state.isContextCloser()) { 	
			manager.getLogger().debug("State [" + state + "] is a context closer so will close current context");						
			manager.getQueue().getAndRemoveCurrentContext();
			manager.setDefaultStateAfterClosingContext();
		}else { 	
			Optional<State> prev = manager.closeCurrentStateAndGetPrevForCurrentContext(state);
			if(prev != null && prev.isPresent()) {
				manager.revertToPreviousStateInCurrentContext(prev);
			}else {
				manager.getQueue().getAndRemoveLastContext();
			}			
		}
	}
}
