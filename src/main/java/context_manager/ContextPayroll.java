/**
 * 
 */
package context_manager;

import object_models.panels.JsPanelControlBar;

/**
 * @author Steve Brown
 *
 */
public final class ContextPayroll extends Context {
	
	public ContextPayroll(ContextManager contextManager, ContextIdGetter idGetter, JsPanelControlBar controlBar) {
		super(contextManager, idGetter);
		// TODO - NOT USING JsPanelControlBar - BUT WE MAY IN THE FUTURE.
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
}
