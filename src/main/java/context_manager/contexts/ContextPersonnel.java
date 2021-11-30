/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import context_manager.FirstContext;
import object_models.forms.ContainerAction;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class ContextPersonnel extends Context implements FirstContext {
	
	public ContextPersonnel(CoreData coreData, ContextIdGetter idGetter, ContainerAction containerAction) {
		super(coreData, idGetter, containerAction);
		
	}
}
