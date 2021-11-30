/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.FirstContext;
import object_models.forms.ContainerAction;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class ContextPersonnel extends Context implements FirstContext {
	
	public ContextPersonnel(ContextManager contextManager, ContextIdGetter idGetter, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction);
		
	}
}
