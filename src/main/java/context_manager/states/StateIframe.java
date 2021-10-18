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
public class StateIframe extends State {
		
public StateIframe(ContextState context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Optional<State> getNext() {
		return Optional.empty();
	}

	@Override
	public Optional<State> close() {
		logger.debug("Closing state");
		context.getDriver().switchTo().defaultContent();
		return super.getPrev();
	}
	
	@Override
	public void switchToMe() {
		// TODO Auto-generated method stub
		logger.error("switchToMe not implemented!");
	}

	@Override
	public boolean isContextCloser() {
		return false;
	}

	@Override
	public boolean isDefaultState() {
		return false;
	}
}
