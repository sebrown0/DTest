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
	public void switchToMe() {
		WebDriver driver = context.getDriver();
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("body > form > div.app-body > div"));		
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
