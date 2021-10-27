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
		
	// Not using 
	private void addInitialStates() {
//		System.out.println("ContextPayroll->addInitialStates"); // TODO - remove or log 	
	}
	
//	@Override
//	public void closeContext() {
//		/*
//		 * As this is the top context it cannot be closed
//		 * However, it could switch to a different module 
//		 * if one is passed to this method.
//		 */
//		super.logger.info("Cannot close module [Payroll]. This is the top context");
//	}

}
