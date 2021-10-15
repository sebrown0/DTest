/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * Represents a context in the app, i.e. a panel, form etc.
 * Each context can have different states, i.e. header/container, iFrame etc.
 * 
 */
public abstract class Context implements ContextState {
	private ContextManager contextManager;
	private ContextId contextId;
	private State firstState;
	private State callingState;
	private State currentState;
		
	public Context(ContextManager contextManager, ContextIdGetter idGetter) {
		this.contextManager = contextManager;		
	
		setContextId(idGetter);
		setFirstState();
		setCallingState();
	}
		
	public void setContextId(ContextIdGetter idGetter) {
		contextId = idGetter.getContextId();
	}

	private void setFirstState() {
		firstState = new StateTop(this); 	
		this.setState(firstState);
	}
	
	private void setCallingState() {
		callingState = contextManager.getCallingState().getState(this);
		firstState.setNext(Optional.ofNullable(callingState));
		this.setState(callingState);
	}
	
	@Override
	public void setState(State state) { 	
		State temp = currentState;
		currentState = state;
		currentState.setPrev(Optional.ofNullable(temp));			 	
		LogManager.getLogger().debug("Setting new state [" + state.toString() + "]");
	}	
	
	@Override
	public State getState() {
		return currentState;
	}

	@Override
	public State getCallingState() {
		return callingState;
	}

	@Override
	public Optional<State> getPreviousState() {
		return currentState.getPrev();
	}

	@Override
	public void moveNext() {
//		System.out.println("Context.moveNext for context ->" + this.contextId.getId()); // TODO - remove or log 	
		Optional<State> next = currentState.getNext();
		next.ifPresentOrElse(n -> {	
			this.setState(n); 
			}, 
			new Runnable() {				
				@Override
				public void run() {
					LogManager.getLogger().debug("No next state for [" + currentState.toString() + "]");
				}
			});		
	}
	@Override
	public WebDriver getDriver() {		
		return contextManager.getDriver();
	}

	@Override
	public void setNullState() {
		currentState = null;		
	}

	@Override
	public State getFirstState() {
		return firstState;
	}

	@Override
	public ContextId getContextId() {
		return contextId;
	}
}

