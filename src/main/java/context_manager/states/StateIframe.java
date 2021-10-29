/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.CallingState;
import context_manager.ContextState;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State implements CallingState {
	private IFrame iFrame;
	
	public StateIframe(ContextState cs, IFrame iFrame, WebDriver driver) {
		super(cs, driver);
		this.iFrame = iFrame;
	}

	@Override
	public Optional<State> getNext() {
		return Optional.empty();
	}

	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		switchToDefaultContent();
		moveToPrevOrDefaultState();
	}	
		
	private void moveToPrevOrDefaultState() {
		Optional<State> prev = this.getPrev();
		prev.ifPresentOrElse( p -> {
			myContext.setCurrentState(p);	
		}, 
			new Runnable() {				
				@Override
				public void run() {
					myContext.switchToDefaultState();
				}			
		});	
	}
	
	@Override
	public State switchToMe() {
		iFrame.switchUsingLocator();
		return this;
	}

	@Override
	public boolean isContextCloser() {
		return false;
	}

	@Override
	public boolean isDefaultState() {
		return false;
	}

	@Override
	public State getState(ContextState context) {
		return this;
	}
}
