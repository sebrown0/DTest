/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.CurrentContextGetter;
import object_models.helpers.IFrame;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {
	private IFrame iFrame;
	
	public StateIframe(CurrentContextGetter getter, IFrame iFrame, WebDriver driver) {
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
		currentContext.switchToDefaultContent();		
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
