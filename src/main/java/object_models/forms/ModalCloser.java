/**
 * 
 */
package object_models.forms;

import context_manager.ContextManager;
import context_manager.ContextState;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Check if there is a modal form open.
 * If there is, close it.
 */
public class ModalCloser {

	public static void closeAnyOpenModalForms(ContextManager cm) {
		try {
			if(modalFormIsOpen(cm)) {
				closeModalForm(cm, cm.getCurrentContext());
			}	
		} catch (Exception e) {
			// Nothing. Assume no open modal form.
		}		
	}
	
	private static boolean modalFormIsOpen(ContextManager cm) {		
		ContainerAction current = cm.getCurrentContext().getContinerAction();
		return (current instanceof FormModal) ? true : false;
	}
	
	private static void closeModalForm(ContextManager cm, ContextState modalForm) {
		ContainerAction current = cm.getCurrentContext().getContinerAction();
		current.close();
	}
}
