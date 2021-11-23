/**
 * 
 */
package context_manager;

import java.util.List;

/**
 * @author Steve Brown
 *
 */
public interface CurrentContext {
	ContextState getCurrentContextState();
	List<ContextState> getContextQueue();
	void setCurrentContextState(ContextState cs);
}
