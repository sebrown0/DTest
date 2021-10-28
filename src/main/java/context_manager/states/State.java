/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 * The current state of a Context.
 */
public abstract class State {	
	protected WebDriver driver;
	protected ContextState myContext; 
	protected Optional<State> next;
	protected Logger logger = LogManager.getLogger();
	
	/*
	 *  Set by ContextState.setState
	 *  When a state is set the new state's
	 *  previous state is set to the current state.
	 */
	private Optional<State> prev;
	
	public State(ContextState cs, WebDriver driver) {
		this.myContext = cs;
		this.driver = driver;
	}
	
	public abstract Optional<State> getNext();
	public abstract void close();
	public abstract boolean isContextCloser();	
	public abstract boolean isDefaultState();
	public abstract State switchToMe();
		
	public void setPrev(Optional<State> prev) {
		this.prev = prev;
	}

	public Optional<State> getPrev() {
		return prev;
	}

	public void setNext(Optional<State> next) {
		this.next = next;
	}
	
	protected WebDriver switchToDefaultContent() {		
		driver.switchTo().defaultContent();
		return driver;
	}
	
	protected void switchToDefaultContentAndThenElement(By byLocator) {		
		switchToDefaultContent().findElement(byLocator);
	}
	
	protected void setCurrentContextToThisStatesContext() {
		if(myContext != null) {//			
			myContext.getContextManager().setCurrentContextState(myContext);
		}		
	}
	
	public ContextState getMyContext() {
		return myContext;
	}
}
