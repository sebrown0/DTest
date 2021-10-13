/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public interface Context {
	void setNullState();	
	void setState(State state);
	void moveNext();
	State getState();
	State getFirstState();
	Optional<State> getPreviousState();
	WebDriver getDriver();
}
