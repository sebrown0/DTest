/**
 * 
 */
package context_manager;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {

	public StateHeaderPanel(ContextManager cm) {
		super(cm);		
	}

	@Override
	public State getNext() {
		return new StateIframe(super.contextManager);
	}

	@Override
	public State close() {
		// TODO Add close logic
		System.out.println("->Close StateHeaderPanel");
		return null;
	}


}
