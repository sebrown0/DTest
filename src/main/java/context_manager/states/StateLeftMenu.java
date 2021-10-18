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
public class StateLeftMenu extends State {
	
	public StateLeftMenu(ContextState context) {
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
		context.getDriver().findElement(By.cssSelector("body > form > div.app-body > div"));		
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
