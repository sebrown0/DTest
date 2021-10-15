/**
 * 
 */
package context_manager;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {
		
	public StateIframe(ContextState context, Optional<State> prev) {
		super(context, prev);		
		context.setState(this);
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
//		return super.getPrev().get().close();
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
