/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * Manages the contexts within the app.
 * 
 */
public class ContextManager {
	private ContextQueue queue = new ContextQueue();
	private ContextState contextState;
	private WebDriver driver;
	// The state that initiates the context, i.e. LeftMenu
	// This has to be added to the firstState as the next state.	
	private CallingState callingState;	
	
	public ContextManager(WebDriver driver) {
		this.driver = driver;
	}
		
	public ContextManager setNextState(State state) {
		State current = contextState.getState();
		current.setNext(Optional.ofNullable(state));
		return this;
	}
	
	public void switchToFirstState() {
		if(contextState != null) {
			contextState.getFirstState().switchToMe();
		}else {
			System.out.println("-> context state is null" ); // TODO - remove or log 	
		}			
	}
	
	public void moveNext(){
		contextState.moveNext();
	}
	
	public void closeCurrent(){
		System.out.println("closeCurrent->" + contextState.getState().toString());
		Optional<State> prev = closeStateAndGetPrev();
		
		if(prev.isPresent()) {
			System.out.println("->should have switched to header"); // TODO - remove or log
			revertToPreviousState(prev);
		}else {
			System.out.println("->remove context"); // TODO - remove or log
			queue.getAndRemoveCurrent();
		}		
	}
	
	private Optional<State> closeStateAndGetPrev(){
		return contextState.getState().close();
	}
	private void revertToPreviousState(Optional<State> prev) {
		prev.ifPresentOrElse(
				p -> contextState.setState(p), 
				new Runnable() {			
					@Override
					public void run() {
						contextState.setNullState();				
					}
		});
	}

	private void logInvalidContextIfNull() {
		if(contextState == null) {
			LogManager.getLogger().error("Context is null");
		}
	}
	
	/*
	 * Getters / Setters
	 */
	public ContextState getContext() {
		logInvalidContextIfNull();
		return contextState;
	}
	public void setContext(ContextState contextState) {
		this.contextState = contextState;
		queue.addContextToQueue(contextState);
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setCallingState(CallingState callingState) {
		this.callingState = callingState;
	}
	public CallingState getCallingState() {
		return callingState;
	}
	public String getContextId() {
		return contextState.getContextId().getId();
	}
	
	// Queue
	public ContextQueue getQueue() {
		return queue;
	}
	public Optional<ContextState> findContext(Object obj) {
		return queue.findContext(obj);
	}
	public ContextState getEndOfQueue() {
		return queue.getCurrent();
	}
	public ContextState getAndRemoveEndOfQueue() {
		return queue.getAndRemoveCurrent();
	}	
	public boolean removeContextFromQueue(Object obj) {
		return queue.removeContext(obj);
	}
}
