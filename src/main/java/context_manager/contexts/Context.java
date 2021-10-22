/**
 * 
 */
package context_manager.contexts;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.ContextCloser;
import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateTop;
import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 * Represents a context in the app, i.e. a panel, form etc.
 * Each context can have different states, i.e. header/container, iFrame etc.
 * 
 */
public abstract class Context implements ContextState, ContextCloser {
	protected Logger logger = LogManager.getLogger();
	
	private ContextManager contextManager;
	private ContextId contextId;
	private ContainerAction containerAction;
	private State firstState;
	private State callingState;
	private State currentState;
	private State searchedState;
		
	public Context(ContextManager contextManager, ContextIdGetter idGetter, ContainerAction containerAction) {
		this.contextManager = contextManager;		
		this.containerAction = containerAction;
		
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
		this.setState(callingState);
	}
			
	@Override
	public ContainerAction getContinerAction() {
		return containerAction;
	}
		
	@Override
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	@Override
	public <T extends State> Optional<State> setLastState(Class<T> clazz){				
		this.getNewInstanceOfState(clazz).ifPresentOrElse(s -> 
				{ this.setState(s); 
					logger.debug("Set last (and current state) to [" + s +"]");
				}, 
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Could not set last state to [" + clazz.getSimpleName() +"]");	
					}
				});
		
		return Optional.ofNullable(currentState);
	}
	
	@Override
	public void setState(State newState) { 			
		setCurrentsNextToNewState(newState);
		setNewStatesPrevToCurrent(newState);		
		logger.debug("Setting new state [" + newState.toString() + "]"); 	
	}	
	
	private void setCurrentsNextToNewState(State state) {
		if(currentState != null) {
			currentState.setNext(Optional.ofNullable(state));
		}
	}

	//TODO - HAVE TO CHECK THAT THIS DOES NOT MESS UP MOVING TO PRE-SET STATE, i.e. IFRAME.
	private void setNewStatesPrevToCurrent(State state) {
		State temp = currentState; 
		currentState = state; 
		currentState.setPrev(Optional.ofNullable(temp));	
	}

	@Override
	public boolean isStateInContext(Class<?> clazz) {
		String stateName = clazz.getSimpleName();		
		State start = getTopState();
		Optional<State> s = Optional.ofNullable(start);
		
		while(s != null) {			
			if(s.isPresent()) {
				State temp = s.get(); 	
				if(temp.getClass().getSimpleName().equals(stateName)) {
					searchedState = temp;
					return true;
				}
				s = temp.getNext();
			}else {
				s = null;
			}			
		}
		return false;
	}
	
	@Override
	public State getTopState() {
		State s = currentState;
		State top = s;

		while (s != null) {
			if(s.getPrev() != null && s.getPrev().isPresent()) {
				s = s.getPrev().get();
				top = s;
			}else {
				s = null;
			}
		} 	
		return top;
	}
	
	@Override
	public State getState() {
		return currentState;
	}
	
	@Override
	public <T extends State>  Optional<State> moveToState(Class<T> clazz) {
		if(isStateInContext(clazz)) {
			currentState = searchedState;
			return Optional.of(currentState);
		}
		return Optional.empty();
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
		Optional<State> next = currentState.getNext();
		next.ifPresentOrElse(n -> {	
			this.setState(n); 
			}, 
			new Runnable() {				
				@Override
				public void run() {
					logger.debug("No next state for [" + currentState.toString() + "]");
				}
			});		 	
	}
	
	@Override
	public void switchToDefaultContent() {
		contextManager.getDriver().switchTo().defaultContent();
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
	
	protected ContextManager getContextManager() {
		return contextManager;
	}
}

