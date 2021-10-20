/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 */
public class StateModalForm extends State {
	
	public StateModalForm(ContextState context) {
		super(context);
	}
	
	@Override
	public Optional<State> getNext() {		
		return Optional.empty();
	}	
	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		logger.error("NOT IMPLEMENTED");
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
		return true;
	}
}
