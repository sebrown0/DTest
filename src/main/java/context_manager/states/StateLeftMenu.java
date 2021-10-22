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
public class StateLeftMenu extends State {
	
	public StateLeftMenu(CurrentContextGetter getter, WebDriver driver) {
		super(getter, driver);
	}

	@Override
	public Optional<State> getNext() {
		return super.next;
	}

	@Override
	public State switchToMe() {
		currentContext.switchToDefaultContent();
		driver.findElement(By.cssSelector("body > form > div.app-body > div"));
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
		// TODO Auto-generated method stub
		logger.error("Not implemented");
	}
}
