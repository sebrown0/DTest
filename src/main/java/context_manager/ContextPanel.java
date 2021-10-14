/**
 * 
 */
package context_manager;

import object_models.panels.JsPanelControlBar;

/**
 * @author Steve Brown
 *
 */
public final class ContextPanel extends Context{
//	private ContextManager contextManager;
//	private State firstState;
//	private State currentState;
//	private ContextId contextId;
	
	public ContextPanel(ContextManager contextManager, ContextIdGetter idGetter, JsPanelControlBar controlBar) {
		super(contextManager, idGetter);
//		this.contextManager = contextManager;		
//		this.firstState = new StateTop(this, null);
	
//		setContextId(idGetter);
//		setState(firstState);
//		setState(contextManager.getCallingState().getState(this, Optional.of(firstState)));		
	}

}
