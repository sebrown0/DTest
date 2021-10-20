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

/**
 * @author Steve Brown
 *
 */
public final class ContextForm extends Context {	
	
	public ContextForm(ContextManager contextManager, ContextIdGetter idGetter) {
		super(contextManager, idGetter); 	
	}
		
	@Override
	public void closeContext() {	 	
		super.getContextManager().getQueue().getAndRemoveCurrentContext();
	}

	@Override
	public <T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState) {
		State s = null;				
		Constructor<?> ctor;
		
		try {
			Class<?> newClazz = Class.forName(clazzState.getName());
			ctor = newClazz.getConstructor(ContextState.class);
			s = (State) ctor.newInstance(this);
		} catch (Exception e) {
			logger.error("Failed getting new instance of state [" + clazzState.getSimpleName() + "]");
		}
		return Optional.ofNullable(s);
	}

}
