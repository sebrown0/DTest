/**
 * 
 */
package context_manager;

/**
 * @author Steve Brown
 *
 */
public abstract class State {	
//	protected State prev;
	protected ContextManager contextManager;
	
	public State(ContextManager contextManager) {
		this.contextManager = contextManager;
//		this.prev = contextManager.getContext().getPreviousState();
	}
	
	public abstract State getNext();
	public abstract State close();
	
	public State getPrev() {
		return contextManager.getContext().getPreviousState();
//		return prev;
	}
	
}
