/**
 * 
 */
package context_manager.contexts;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.FirstContext;
import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 */
public final class ContextPayroll extends Context implements FirstContext {
	
	public ContextPayroll(ContextManager contextManager, ContextIdGetter idGetter, ContainerAction containerAction) {
		super(contextManager, idGetter, containerAction);
		
		addInitialStates();
	}
		
	private void addInitialStates() {
		System.out.println("ContextPayroll->addInitialStates"); // TODO - remove or log 	
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

//	@Override
//	public <T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState) {
//		StateFactory factory = new StateFactory(getContextManager(), getContextManager().getDriver());
//		return factory.getNewInstanceOfState(clazzState);
//	}
//		State state = null;				
//		Constructor<?> ctor = null;
//		Class<?> newClazz;
//		
//		System.out.println("clazz->" + clazzState); // TODO - remove or log
//		
//		if(clazzState.getSimpleName().equals("StateLeftMenu")) {
//			System.out.println("clazz is --------> StateLeftMenu"); // TODO - remove or log
//			try {
//				Class<?>[] args = new Class[2];
//				args[0] = CurrentContext.class;
//				args[1] = WebDriver.class;
//				newClazz = Class.forName(clazzState.getName());
//				ctor = newClazz.getConstructor(args);
//				state = (State) ctor.newInstance(super.getContextManager(), super.getContextManager().getDriver());
////				ctor = newClazz.getConstructor(CurrentContext.class);
//			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}else if (clazzState.getSimpleName().equals("StateHeaderPanel")) {
//			System.out.println("->NI"); // TODO - remove or log 	
//		}
//		
//		
//		try {
////			Class<?> newClazz = Class.forName(clazzState.getName());
////			ctor = newClazz.getConstructor(ContextState.class);
////			state = (State) ctor.newInstance(this);
//		} catch (Exception e) {
//			logger.error("Failed getting new instance of state [" + clazzState.getSimpleName() + "]");
//			System.out.println("->" + e); // TODO - remove or log 	
//		}
//		return Optional.ofNullable(state);
//	}

}
