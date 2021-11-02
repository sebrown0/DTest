/**
 * 
 */
package context_manager.states;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 */
public class StateLeftMenu extends State {
	
	public StateLeftMenu(ContextState cs, WebDriver driver) {
		super(cs, driver);
	}

	@Override
	public State getNewNextState() {
		return super.next;
	}

	@Override
	public State switchToMe() {
		logger.debug("Switching to state [left menu]");
		switchToDefaultContentAndThenElement(By.cssSelector("body > form > div.app-body > div"));
		//CHANGE THIS
		setCurrentContextToThisStatesContext();		
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

	@Override
	public void close() {
		//N/A
	}
}
