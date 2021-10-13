/**
 * 
 */
package context_manager;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public abstract class State {	
	protected Context context;
	protected Optional<State> next;
	
	private Optional<State> prev;
	
	public State(Context context) {
		this.context = context;	
	}
	
	public State(Context context, Optional<State> prev) {
		this.context = context;
		this.prev = prev;
	}

	public abstract Optional<State> getNext();
	public abstract Optional<State> close();	
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
