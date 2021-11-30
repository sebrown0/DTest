/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import object_models.forms.ContainerAction;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public final class ContextPanel extends Context {

	public ContextPanel(CoreData coreData, ContextIdGetter idGetter, 
												JsPanelHeaderBar hdrBar, ContainerAction containerAction) {
		super(coreData, idGetter, containerAction);
	
	}	

}
