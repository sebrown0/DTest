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
public interface ContextState {
	void setNullState();	
	void setState(State state);
	void moveNext();
	
	State getState();
	State getFirstState();
	State getCallingState();

//	State getClosingState();
	
	Optional<State> getPreviousState();
	WebDriver getDriver();
	ContextId getContextId(); // Should not be in here! This is state not context. THINK IT'S OK!!
}
