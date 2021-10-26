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
//	private JsPanelHeaderBar hdrBar;
	
	public ContextPanel(ContextManager contextManager, ContextIdGetter idGetter, 
												JsPanelHeaderBar hdrBar, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction);
	
//		this.hdrBar = hdrBar;
	}
		
	@Override
	public void closeContext() {	 	
		super.getContextManager().getQueue().getAndRemoveLastContext();
	}

//	@Override
//	public <T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState) {
//		StateFactory factory = new StateFactory(getContextManager(), getContextManager().getDriver(), hdrBar, null);
//		return factory.getNewInstanceOfState(clazzState);
//	}

}
