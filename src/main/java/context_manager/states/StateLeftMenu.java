/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.CurrentContext;

/**
 * @author Steve Brown
 *
 */
public class StateLeftMenu extends State {
	
	public StateLeftMenu(CurrentContext getter, WebDriver driver) {
		super(getter, driver);
	}

	@Override
	public Optional<State> getNext() {
		return super.next;
	}

	@Override
	public State switchToMe() {
		logger.debug("Switching to state [left menu]");
		switchToDefaultContentAndThenElement(By.cssSelector("body > form > div.app-body > div"));
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
		closeMyContext();
	}
}
