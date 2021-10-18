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
public final class ContextPayroll extends Context {
	
	public ContextPayroll(ContextManager contextManager, ContextIdGetter idGetter) {
		super(contextManager, idGetter);	
	}
		
	@Override
	public void closeContext() {
		/*
		 * As this is the top context it cannot be closed
		 * However, it could switch to a different module 
		 * if one is passed to this method.
		 */
		super.logger.info("Cannot close module [Payroll]. This is the top context");
	}

	@Override
	public <T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState) {
		State state = null;				
		Constructor<?> ctor;
		
		try {
			Class<?> newClazz = Class.forName(clazzState.getName());
			ctor = newClazz.getConstructor(ContextState.class);
			state = (State) ctor.newInstance(this);
		} catch (Exception e) {
			logger.error("Failed getting new instance of state [" + clazzState.getSimpleName() + "]");
		}
		return Optional.ofNullable(state);
	}

}
