/**
 * 
 */
package context_manager;

/**
 * @author SteveBrown
 *
 */
public interface CallingState {
	State getState(ContextState context);	
}
