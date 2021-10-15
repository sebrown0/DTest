/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * Manages the contexts within the app.
 * 
 */
public class ContextManager {
	private ContextQueue queue = new ContextQueue();
	private Logger logger = LogManager.getLogger();
	private WebDriver driver;
	
	// The state that initiates the context, i.e. LeftMenu
	// This has to be added to the firstState as the next state.	
	private CallingState callingState;	
	
	public ContextManager(WebDriver driver) {
		this.driver = driver;
	}
		
	public ContextManager setNextState(State state) {
		State current = queue.getCurrentContext().getState();
		current.setNext(Optional.ofNullable(state));
		return this;
	}
	 
	/*
	 * Actions - Start
	 */
	public void switchToFirstStateInCurrentContext() {
		ContextState contextState = queue.getCurrentContext();
		if(contextState != null) {
			contextState.getFirstState().switchToMe();
		}else {
			logger.debug("Context state is null"); 	
		}			
	}
	
	public void moveToNextStateInCurrentContext(){
		queue.getCurrentContext().moveNext();
	}
	
	private void setDefaultStateAfterClosingContext() {				
		ContextState cs = queue.getCurrentContext(); 
		State defaultState = getDefaultState(cs);
		System.out.println("Current context is now [" + cs.getContextId() + "]. State (default) is [" + defaultState + "]"); // TODO - remove or log 	
		logger.debug("Current context is now [" + cs.getContextId() + "]. State (default) is [" + defaultState + "]"); 	
	}
	
	private State getDefaultState(ContextState cs) {
		boolean foundDefault = false;
		State s = cs.getState();
		while (foundDefault == false && s != null && s.isDefaultState() == false) {
			if(s.getNext().isPresent()) {
				s = s.getNext().get();
			}else {
				s = null;
			}
		}
		System.out.println("found ->" + s); // TODO - remove or log 	
		return s;
	}
	
	public void closeCurrentStateInCurrentContext(){
		ContextState cs = queue.getCurrentContext(); 
		if(cs != null) {
			State currentState = cs.getState();
			logger.debug("Closing state [" + currentState + "]"); 	
			if(currentState.isContextCloser()) {
				logger.debug("State [" + currentState + "] is a context closer so will close current context");						
				queue.removeCurrentContext();
				setDefaultStateAfterClosingContext();
			}else {
				Optional<State> prev = closeCurrentStateAndGetPrevForCurrentContext(currentState);
				
				if(prev.isPresent()) {
//					System.out.println("reverting from ->" + current.toString()); // TODO - remove or log
					revertToPreviousStateInCurrentContext(prev);
				}else {
//					System.out.println("->no prev, remove context"); // TODO - remove or log
					queue.getAndRemoveCurrentContext();
				}			
			}	
		}else {
			logger.debug("No context so cannot close current state");
		}		
	}
	
	
	private Optional<State> closeCurrentStateAndGetPrevForCurrentContext(State current){
		return current.close();
	}
	private void revertToPreviousStateInCurrentContext(Optional<State> prev) {
		prev.ifPresentOrElse(
				p -> { 
					queue.getCurrentContext().setState(p);
//					System.out.print("to ->" + p.toString()); // TODO - remove or log 	
				}, 
				new Runnable() {			
					@Override
					public void run() {
						queue.getCurrentContext().setNullState();				
					}
		});
	}
	// Actions - End
	
	// Helpers
	private void logInvalidContextIfNull(ContextState contextState) {
		if(contextState == null) {
			LogManager.getLogger().error("Context is null");
		}
	}
	
	/*
	 * Getters / Setters
	 */		
	public void setContext(ContextState contextState) {		 	
		queue.addContextToQueue(contextState);
	}
	
	// State
	public WebDriver getDriver() {
		return driver;
	}
	public void setCallingState(CallingState callingState) {
		this.callingState = callingState;
	}
	public CallingState getCallingState() {
		return callingState;
	}
	
	// Context
	public String getContextId() {
		return queue.getCurrentContext().getContextId().getId();
	}	
	public ContextState getContext() {
		ContextState cs = queue.getCurrentContext(); 
		logInvalidContextIfNull(cs);
		return cs;
	}
	
	// Queue
	public ContextQueue getQueue() {
		return queue;
	}
	public Optional<ContextState> findContext(Object obj) {
		return queue.findContext(obj);
	}
	public ContextState getEndOfQueue() {
		return queue.getCurrentContext();
	}
	public ContextState getAndRemoveEndOfQueue() {
		return queue.getAndRemoveCurrentContext();
	}	
	public boolean removeCurrentContextFromQueue() {
		return queue.removeCurrentContext();
	}
	public boolean removeContextFromQueueForContextId(Object contId) {
		return queue.removeContextForContextId(contId);
	}
}
