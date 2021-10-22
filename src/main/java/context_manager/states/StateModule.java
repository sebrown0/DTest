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
 */
public class StateModule extends State {
	
	public StateModule(CurrentContextGetter getter, WebDriver driver) {
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
		driver.findElement(By.cssSelector("body > form > header > div > a"));
		return this;
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
