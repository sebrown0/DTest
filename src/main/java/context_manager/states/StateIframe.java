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
		context.getDriver().switchTo().defaultContent();		
	}
	
	@Override
	public void switchToMe() {
		System.out.println("StateIframe->switchToMe"); // TODO - remove or log
		iFrame.switchUsingLocator();
		 	
		// TODO Auto-generated method stub
//		logger.error("switchToMe not implemented!");
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
