/**
 * 
 */
package context_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 *
 * A queue of context (states).
 */
public class ContextQueue {
	private List<ContextState> queue = new ArrayList<>();
//	private Deque<ContextState> queue = new LinkedList<>();
	private Logger logger = LogManager.getLogger();
	
	public void addContextToQueue(ContextState contextState) {
		if(contextState != null) {
			logger.debug("Adding context [" + contextState.getContextId() + "] this will now be the current context");
//			System.out.println("Adding context [" + contextState.getContextId() + "] this will now be the current context");  // TODO - remove or log
			queue.add(contextState);	
		}else {
			logger.error("Cannot add null context to queue");
		}
		
	}

	public Optional<ContextState> getPenultimate() {
//		int numElements = getSize();
		if(getSize()>1) {
			return Optional.of(queue.get(lastIdx()-1));	
		}else {
			return Optional.empty();
		}
		
	}

	private int lastIdx() {
		return getSize()-1;
	}
	public int getSize() {		
		return queue.size();
	}
	
	public ContextState getCurrentContext() {
		return queue.get(lastIdx());
	}
	
	public ContextState getAndRemoveCurrentContext() {
		logger.debug("Removing context [" + getCurrentContext().getContextId() + "] from context queue");
		return removeLast();
	}
	
	private ContextState removeLast() {
		ContextState cur = getCurrentContext(); 
		queue.remove(cur);
		return cur;
	}
	
	public boolean removeCurrentContext() {
		if(queue.isEmpty() == false) {			
			logger.debug("Removing context [" + getCurrentContext().getContextId() + "]");
//			queue.removeLast();
			removeLast();		
			return true;
		}else {
			return false;
		}		
	}
	
	public boolean removeContextForContextId(Object contextId) {
		Optional<ContextState> cs = findContext(contextId);
		if(cs.isPresent()) { 	
			return queue.remove(cs.get());
		}else {
			logger.debug("Could not remove context for object [" + contextId + "]");
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
	
	public int getPositionInQueue(Object obj) {		
		int pos = 0;
		for (ContextState cs : queue) {			
			ContextId id = cs.getContextId();			
			if(id.equals(obj)) { break;	}
			pos++;
		}
		return pos;
	}
	
	public Optional<ContextState> getPrev(ContextState curr){
		int currPos = getPositionInQueue(curr);
		if(currPos >= 1) {
			return Optional.of(queue.get(currPos-1));
		}else {
			return Optional.empty();
		}
	}

	public List<ContextState> getQueue() {
		return queue;
	}
	
//	public Deque<ContextState> getQueue() {
//		return queue;
//	}
	
}
