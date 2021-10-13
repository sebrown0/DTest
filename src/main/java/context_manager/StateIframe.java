/**
 * 
 */
package context_manager;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {

	public StateIframe(ContextManager cm) {
		super(cm);
	}

	@Override
	public State getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State close() {
		// TODO Add close logic
		System.out.println("->Close StateIframe");
		return null;
	}

}
