/**
 * 
 */
package context_manager;

import org.apache.logging.log4j.LogManager;

/**
 * @author Steve Brown
 *
 * 
 */
public class ContextManager {
	private Context context;

	public Context getContext() {
		logInvalidContextIfNull();
		return context;
	}

	public void setContext(Context context) {
		if(context == null) {
			System.out.println("->ContextManager->is null");
		}else {
			System.out.println("->ContextManager->is NOT null");
		}
			
		this.context = context;
	}	
	
	private void logInvalidContextIfNull() {
		if(context == null) {
			LogManager.getLogger().error("Context is null");
		}
	}
}
