/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 * The current state of a Context.
 */
public abstract class State {	
	protected ContextState context;
	protected Optional<State> next;
	protected Logger logger = LogManager.getLogger();
	
	/*
	 *  Set by ContextState.setState
	 *  When a state is set the new state's
	 *  previous state is set to the current state.
	 */
	private Optional<State> prev;
	
	public State(ContextState context) {
		this.context = context;	
	}
	
	public abstract Optional<State> getNext();
	public abstract Optional<State> close();
	public abstract boolean isContextCloser();	
	public abstract boolean isDefaultState();
	public abstract void switchToMe();
	
	public void setPrev(Optional<State> prev) {
		this.prev = prev;
	}

	public Optional<State> getPrev() {
		return prev;
	}

	public void setNext(Optional<State> next) {
		this.next = next;
	}
	
}
