/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.By;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 */
public class StateModule extends State {
	
	public StateModule(ContextState context) {
		super(context);
	}

	@Override
	public Optional<State> getNext() {
		return super.next;
	}

	@Override
	public void close() {
		logger.debug("Cannot close state [" + this + "]");
	}

	@Override
	public void switchToMe() {		
		lastContext.getDriver().findElement(By.cssSelector("body > form > header > div > a"));		
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
