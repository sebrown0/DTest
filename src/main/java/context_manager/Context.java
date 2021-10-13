/**
 * 
 */
package context_manager;

/**
 * @author Steve Brown
 *
 */
public interface Context {
	void setState(State state);
	State getState();
	State getPreviousState();
}
