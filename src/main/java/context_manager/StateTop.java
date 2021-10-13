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
public class StateTop extends State {
	private State next;
	
	public StateTop(Context context, Optional<State> prev) {
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
		context.getDriver().close();
		return Optional.empty();
	}

	@Override
	public void switchToMe() {
		context.getDriver().findElement(By.cssSelector("body > form > div.app-body"));		
	}


}
