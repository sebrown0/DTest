/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 * The top state in a chain of states.
 * The Context will always have one of these.
 */
public class StateTop extends State {
	
	public StateTop(ContextState context) {
		super(context);
	}
	
	@Override
	public Optional<State> getNext() {		
		return super.next;
//		return Optional.ofNullable(super.next);
	}

	@Override
	public Optional<State> close() {
		logger.debug("Closing state. This will end the session");
		context.getDriver().close();
		return Optional.empty();
	}

	@Override
	public void switchToMe() {
		logger.debug("Switching to top state");
		WebDriver driver = context.getDriver();
		/*
		 * Not strictly necessary to 'goto' the form.
		 * But for completeness we do.
		 */

		driver
			.switchTo()
			.defaultContent()
			.findElement(By.cssSelector("form[name='tb']"));				
	}

	@Override
	public boolean isContextCloser() {
		return true;
	}

	@Override
	public boolean isDefaultState() {
		return false;
	}
}
