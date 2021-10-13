/**
 * 
 */
package context_manager;

/**
 * @author Steve Brown
 *
 */
public class ContextPanel implements Context {
	private State state;
	
	public ContextPanel() {
		state = new StateHeaderPanel(null);
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public State getState() {
		return state;
	}

	@Override
	public State getPreviousState() {
		return state.getPrev();
	}

}
