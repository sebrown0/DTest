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
public class StateLeftMenu extends State {
	
	public StateLeftMenu(Context context, Optional<State> prev) {
		super(context, prev);
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
		context.getDriver().findElement(By.cssSelector("body > form > div.app-body > div"));		
	}

}
