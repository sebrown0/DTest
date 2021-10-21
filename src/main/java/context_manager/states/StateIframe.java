/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import context_manager.ContextState;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {
	private IFrame iFrame;
	
	public StateIframe(ContextState context, IFrame iFrame) {
		super(context);
		this.iFrame = iFrame;
	}

	@Override
	public Optional<State> getNext() {
		return Optional.empty();
	}

	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		context.switchToDefaultContent();		
	}
	
	@Override
	public void switchToMe() {
		iFrame.switchUsingLocator();		 	
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
