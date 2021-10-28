/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 */
public final class ContextForm extends Context {	
	
	public ContextForm(ContextManager contextManager, ContextIdGetter idGetter, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction); 	
	}
		
}
