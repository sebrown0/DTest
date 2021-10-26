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
		
	@Override
	public void closeContext() {	 	
		super.getContextManager().getQueue().getAndRemoveLastContext();
	}

//	@Override
//	public <T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState, StateFactorySetter factorySetter) {
//		State s = null;				
//		Constructor<?> ctor;
//		
//		try {
//			Class<?> newClazz = Class.forName(clazzState.getName());
//			ctor = newClazz.getConstructor(ContextState.class);
//			s = (State) ctor.newInstance(this);
//		} catch (Exception e) {
//			logger.error("Failed getting new instance of state [" + clazzState.getSimpleName() + "]");
//		}
//		return Optional.ofNullable(s);
//	}

}
