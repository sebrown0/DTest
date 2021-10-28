/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import object_models.forms.ContainerAction;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public final class ContextPanel extends Context {

	public ContextPanel(ContextManager contextManager, ContextIdGetter idGetter, 
												JsPanelHeaderBar hdrBar, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction);
	
	}	

}
