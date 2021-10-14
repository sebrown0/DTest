/**
 * 
 */
package context_manager;

import object_models.panels.JsPanelControlBar;

/**
 * @author Steve Brown
 *
 */
public final class ContextPanel extends Context {
	
	public ContextPanel(ContextManager contextManager, ContextIdGetter idGetter, JsPanelControlBar controlBar) {
		super(contextManager, idGetter);
		// TODO - NOT USING JsPanelControlBar - BUT WE MAY IN THE FUTURE.
	}

}
