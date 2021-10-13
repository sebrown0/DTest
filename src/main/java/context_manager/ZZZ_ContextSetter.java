/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import object_models.forms.ContainerAction;
import object_models.forms.FormModal;
import object_models.panels.JSPanel;

/**
 * @author Steve Brown
 *
 */
public class ZZZ_ContextSetter {
	private static Logger logger = LogManager.getLogger();
	
	public static void setContext(Optional<ContainerAction> child, ContextManager contextManager ) {
		System.out.println("->ContextSetter");
		child.ifPresent(c -> {
			if(c instanceof JSPanel) {
				contextManager.setContext(new ContextPanel());
				System.out.println("->ContextSetter->is panel");
//				JSPanel p = (JSPanel) c;
//				System.out.println("->ContextSetter->setting context");
//				p.setContextManager(contextManager);
			}else if (c instanceof FormModal) {				
				logger.error("FormModal not set as context !!!!!!!!!!!!");
			}else {
				logger.error("Could not set context");
			}
		});		
	}
}
