/**
 * 
 */
package context_manager;

import java.util.Optional;

/**
 * @author SteveBrown
 *
 */
public interface CallingState {
	State getState(Context context, Optional<State> prev);
}
