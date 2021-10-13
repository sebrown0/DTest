/**
 * 
 */
package context_manager;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {

//	public StateHeaderPanel(ContextManager cm) {
//		super(cm);		
//	}

	@Override
	public Optional<State> getNext(Optional<State> prev) {
		prev.ifPresent(p -> super.prev = p);
		return Optional.of(new StateIframe());
	}

	@Override
	public State close() {
		// TODO Add close logic
		System.out.println("->Close StateHeaderPanel");
		return null;
	}


}
