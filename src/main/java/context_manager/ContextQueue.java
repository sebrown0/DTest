/**
 * 
 */
package context_manager;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 *
 * A queue of context (states).
 */
public class ContextQueue {
	private Queue<ContextState> queue = new LinkedList<>();
	private Logger logger = LogManager.getLogger();
	
	public void addContextToQueue(ContextState contextState) {
		queue.add(contextState);
	}
	
	public ContextState getCurrent() {
		return queue.peek();
	}
	
	public ContextState getAndRemoveCurrent() {
		return queue.remove();		
	}
	
	public boolean removeContext(Object obj) {
		Optional<ContextState> cs = findContext(obj);
//		System.out.println("->" + cs.get());
		if(cs.isPresent()) {
			return queue.remove(cs.get());
		}else {
			logger.debug("Could not remove context for object [" + obj + "]");
			return false;
		}
	}
	
	public Optional<ContextState> findContext(Object obj) {
		ContextState returnVal = null;
		for (ContextState cs : queue) {
			ContextId id = cs.getContextId();
			if(id.equals(obj)) {
				returnVal = cs;
				break;
			}
			logger.debug("Unable to find context for [" + obj + "]");
		}
		return Optional.ofNullable(returnVal);
	}
}
