/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.FirstContext;
import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 */
public final class ContextPayroll extends Context implements FirstContext {
	
	public ContextPayroll(ContextManager contextManager, ContextIdGetter idGetter, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction);
		
	}
}
