/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * The top state in a chain of states.
 * The Context will always have one of these.
 */
public class StateTop extends State {
	private State next;
	
	public StateTop(ContextState context, Optional<State> prev) {
		super(context, prev);
	}

	public void setNextState(State next) {
		this.next = next;
	}
	@Override
	public Optional<State> getNext() {		
		return Optional.ofNullable(next);
	}

	@Override
	public Optional<State> close() {
		logger.debug("Closing state. This will end the session");
		context.getDriver().close();
		return Optional.empty();
	}

	@Override
	public void switchToMe() {
		System.out.println("->switching to top");
		WebDriver driver = context.getDriver();
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("body > form > div.app-body"));		
	}

}
