/**
 * 
 */
package context_manager;

/**
 * @author Steve Brown
 *
 */
public interface CurrentContext {
	ContextState getCurrentContextState();
	void setCurrentContextState(ContextState cs);
}
