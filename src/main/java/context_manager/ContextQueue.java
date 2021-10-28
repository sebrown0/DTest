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
		if(cs != null) {
			boolean foundContext = false;
			for (ContextState contextState : queue) {
				if(cs == contextState) {
					current = cs;					
//					current.switchToDefaultState(); // IN_CS
					current.moveToDefaultState();
					foundContext = true;
					break;
				}
			}
			if(!foundContext) {
				logger.error("Could not find existing context [" + cs.getContextId() + "]");
			}
		}else {
			logger.error("Cannot find NULL context");
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
	
	public boolean removeLastContext() {
		if(queue.isEmpty() == false) {
			removeAndCloseContext(getLastContextInQueue());		
			return true;
		}else {
			return false;
		}		
	}
	
	public boolean removeContext(ContextState cs) {
		if(cs instanceof FirstContext) {
			logger.debug("Cannot remove first context");
			return false;
		}else {
			logger.debug("Removing context [" + cs.getContextId() + "] from context queue"); 	
			if(cs == current) {
				resetCurrent();
			}
			queue.remove(cs);
			return true;
		}				
	}
	
	/*
	 * This removes the context from the queue, and
	 * gets the context's closer to close the context.
	 * 
	 * If the context has already been closed do not use this method.
	 */	
	public void removeAndCloseContext(ContextState cs) {
		if(removeContext(cs)) {			
			cs.getContextCloser().close();			
		}		
	}
	
	private void resetCurrent() {
		ContextState newCurr = null;
		int idxOfContext = queue.indexOf(current);
		
		if(hasPrev(idxOfContext)) {
			newCurr = queue.get(idxOfContext-1);
		}else if(hasNext(idxOfContext)){
			newCurr = queue.get(idxOfContext+1);
		}
		
		current = newCurr;		
		if(current != null) {
			current.switchToDefaultState();
		}
	}

	private boolean hasPrev(int idxOfContext) {
		return (idxOfContext >= 1); // Module is the first context, but we don't move to that.
		/*
		 * CHANGE THIS WE WILL MOVE TO THE FIRST MODULE ^^^
		 */
	}
	
	private boolean hasNext(int idxOfContext) {
		return (idxOfContext <= 1 && (idxOfContext+1) < getSize());
	}
	
	public Optional<ContextState> findContext(Object obj) {
		ContextState returnVal = null;
		for (ContextState cs : queue) {
			ContextId id = cs.getContextId();
			if(id.equals(obj)) {
				returnVal = cs;
				break;
			}
//			logger.debug("Unable to find context for [" + obj + "]");
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
