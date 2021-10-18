/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.openqa.selenium.By;

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
	public Optional<State> close() {
		return super.getPrev();
	}

	@Override
	public void switchToMe() {		
		context.getDriver().findElement(By.cssSelector("body > form > header > div > a"));		
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
