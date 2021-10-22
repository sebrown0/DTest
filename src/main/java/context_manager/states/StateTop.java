/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.CurrentContextGetter;

/**
 * @author Steve Brown
 *
 * The top state in a chain of states.
 * The Context will always have one of these.
 */
public class StateTop extends State {
	
	public StateTop(CurrentContextGetter getter, WebDriver driver) {
		super(getter, driver);
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
	public State switchToMe() {
		logger.debug("Switching to top state");

		/*
		 * Not strictly necessary to 'goto' the form.
		 * But for completeness we do.
		 */
		driver
			.switchTo()
			.defaultContent()
			.findElement(By.cssSelector("form[name='tb']"));
		
		return this;
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
