/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 *
 * The current state of a Context.
 */
public abstract class State {	
	protected ContextState context;
	protected Optional<State> next;
	protected Logger logger = LogManager.getLogger();
	
	private Optional<State> prev;
	
	public State(ContextState context) {
		this.context = context;	
	}
	
	public State(ContextState context, Optional<State> prev) {
		this.context = context;
		this.prev = prev;
		System.out.println("State: new state with prev ->" + this + "---" + prev); // TODO - remove or log 	
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

//	@Override
//	public String toString() {
//		return 
//				"State [name =" + this.getClass().getSimpleName() + 
//				", context=" + context.getContextId() + 
//				", next=" + next + 
//				", prev=" + prev + "]";
//	}
	
}
