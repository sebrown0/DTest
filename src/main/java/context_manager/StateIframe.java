/**
 * 
 */
package context_manager;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {

//	public StateIframe(ContextManager cm) {
//		super(cm);
//	}


	@Override
	public Optional<State> getNext(Optional<State> prev) {
		prev.ifPresent(p -> super.prev = p);
		return Optional.empty();
	}

	@Override
	public State close() {
		// TODO Add close logic
		System.out.println("->Close StateIframe");
		return null;
	}

}
