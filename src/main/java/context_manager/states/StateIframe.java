/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import context_manager.CurrentContext;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {
	private IFrame iFrame;
	
	public StateIframe(CurrentContext getter, IFrame iFrame, WebDriver driver) {
		super(getter, driver);
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
			currentContext.setCurrentState(p);	
		}, 
			new Runnable() {				
				@Override
				public void run() {
					currentContext.switchToDefaultState();
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
}
