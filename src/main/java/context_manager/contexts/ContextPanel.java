/**
 * 
 */
package context_manager.contexts;

import java.lang.reflect.Constructor;
import java.util.Optional;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import object_models.forms.ContainerAction;
import object_models.panels.JsPanelControlBar;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public final class ContextPanel extends Context {
	private JsPanelControlBar bar;
	
	public ContextPanel(ContextManager contextManager, ContextIdGetter idGetter, 
												JsPanelHeaderBar bar, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction);
		
		this.bar = bar.getControlBar();
	}
		
	@Override
	public void closeContext() {	 	
		super.getContextManager().getQueue().getAndRemoveLastContext();
	}

	@Override
	public <T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState) {
		State s = null;				
		Constructor<?> ctor;
		
		try {
			Class<?> newClazz = Class.forName(clazzState.getName());
			ctor = newClazz.getConstructor(ContextState.class, JsPanelControlBar.class);
			s = (State) ctor.newInstance(this, this.bar);
		} catch (Exception e) {
			logger.error("Failed getting new instance of state [" + clazzState.getSimpleName() + "]");
		}
		return Optional.ofNullable(s);
	}

}
