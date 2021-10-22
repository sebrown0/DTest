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
	private ContextState current = null;
	private Logger logger = LogManager.getLogger();
	
	public void addContextToQueue(ContextState contextState) {
		if(contextState != null) {
			logger.debug("Adding context [" + contextState.getContextId() + "] this will now be the current context");
			queue.add(contextState);	
			current = contextState;
		}else {
			logger.error("Cannot add null context to queue");
		}		
	}
	
	public void moveToExistingContext(ContextState cs) {
		for (ContextState contextState : queue) {
			if(cs == contextState) {
				System.out.println("moveToExistingContext->" + cs.getContextId()); // TODO - remove or log
				current = cs;
			}
		}
	}

	public Optional<ContextState> getPenultimate() {
		if(getSize()>1) {
			return Optional.of(queue.get(lastIdx()-1));	
		}else {
			return Optional.empty();
		}		
	}
	
	public ContextState getCurrentContextInQueue() {
		return current;
	}
	
	public ContextState getLastContextInQueue() {
		return queue.get(lastIdx());
	}
	
	public ContextState getAndRemoveCurrentContext() {
		return removeContext(current);
	}
	
	public ContextState getAndRemoveLastContext() {		
		return removeContext(getLastContextInQueue());
	}

	public boolean removeLastContext() {
		if(queue.isEmpty() == false) {
			removeContext(getLastContextInQueue());		
			return true;
		}else {
			return false;
		}		
	}
	
	private ContextState removeContext(ContextState cs) {
		logger.debug("Removing context [" + cs.getContextId() + "] from context queue");
		queue.remove(cs);
		return cs;
	}
	
	
	public boolean removeContextForContextId(Object contextId) {
		Optional<ContextState> cs = findContext(contextId);
		if(cs.isPresent()) { 	
			return removeContext(cs.get()) == null;
//			return queue.remove(cs.get());
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

	private int lastIdx() {
		return getSize()-1;
	}
	public int getSize() {		
		return queue.size();
	}
		
}
