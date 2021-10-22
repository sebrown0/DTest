/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.contexts.Context;
import context_manager.states.State;

/**
 * @author Steve Brown
 *
 * Manages the contexts within the app.
 * 
 * TODO - ISSUES
 * -------------
 * 1. Get ContextManagerTests to run all tests.
 * 
 * 2. Do we revert to default context (of prev con)
 *    when closing current context?
 *    
 * 3. Clicking a left-menu element (that has children, i.e. Employees)
 *    does not close the element if it's already open. 
 *    
 * 4. Add more UTs for different scenarios.
 * 
 * 5. Split states and contexts correctly. Add new helper classes as necessary.
 * 
 * 6. When looking for a state, should the context be checked?
 * 
 * 7. Add a DQueue for states as well as contexts.
 *    This would eliminate the need to loop thru state to get to
 *    the top or bottom.
 * 
 * USE GIT BRANCH context_manager.
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
		
	public void setFirstContext(Context context) {
		queue.addContextToQueue(context);
	}
	
	public ContextManager setNextState(State state) {
		State current = queue.getLastContextInQueue().getState();
		current.setNext(Optional.ofNullable(state));
		return this;
	}
	 
	/*
	 * Actions - Start
	 */
	public Optional<ContextState> getPenultimateContext() {
		return queue.getPenultimate();
	}
	
	public <T extends State> Optional<State> switchToStateInCurrentContext(Class<T> clazzRequiredState) {
		String requiredStateName = clazzRequiredState.getSimpleName();		
		if(isCurrentStateRequiredState(requiredStateName)) {
			logger.debug("State [" + requiredStateName + "] is current state in context");
			return Optional.ofNullable(getLastContext().getState());
		}else {
			Optional<State> state = moveToStateInCurrentContext(clazzRequiredState);
			state.ifPresent(s -> s.switchToMe());
			return state;
		}				
	}
	
	public <T extends State> Optional<State> switchToStateInContext(Class<T> clazzRequiredState, ContextState findCs) {
		Optional<State> state = Optional.empty();		
		if(findCs == getLastContext()) {
			state = moveToStateInCurrentContext(clazzRequiredState);			
		}else {
			state = moveToStateInContext(clazzRequiredState, findCs);
		}				
		state.ifPresent(s -> s.switchToMe());
		return state;
	}
	
	public <T extends State> Optional<State> moveToStateInContext(Class<T> clazzRequiredState, ContextState cs) {
		String requiredStateName = clazzRequiredState.getSimpleName();
		Optional<State> state = cs.moveToState(clazzRequiredState);

		if(state.isPresent()) {			
			logger.debug("State [" + requiredStateName + "] is present in context. Will move to this state");				
			return state;			
		}else {
			logger.debug("State [" + requiredStateName + "] is not present in context. Adding as the last state in context");			
			return getLastContext().setLastState(clazzRequiredState);
		}						
	}
	
	public <T extends State> Optional<State> moveToStateInCurrentContext(Class<T> clazzRequiredState) {
		return moveToStateInContext(clazzRequiredState, getLastContext());			
	}
	
	public void moveToStateInCurrentContext(State state) {
		getLastContext().setCurrentState(state);			
	}
	
	private boolean isCurrentStateRequiredState(String requiredStateName) {
		String currentStatesName = getLastContext().getState().getClass().getSimpleName(); 	
		return currentStatesName.equals(requiredStateName);
	}
	
	public void switchToFirstStateInCurrentContext() {
		ContextState contextState = queue.getLastContextInQueue();
		if(contextState != null) {
			contextState.getFirstState().switchToMe();
		}else {
			logger.debug("Context state is null"); 	
		}			
	}
	
	public State moveToNextStateInCurrentContext(){
		ContextState contextState = queue.getLastContextInQueue();
		contextState.moveNext();
		return contextState.getState();
	}
	
	private void setDefaultStateAfterClosingContext() {				
		ContextState cs = queue.getLastContextInQueue(); 
		State defaultState = getDefaultState(cs);
		if(defaultState != null) {
//			System.out.println("Current context is now [" + cs.getContextId() + "]. State (default) is [" + defaultState + "]"); // TODO - remove or log 	
			logger.debug("Current context is now [" + cs.getContextId() + "]. State (default) is [" + defaultState + "]");
			cs.setCurrentState(defaultState);
		}else {
//			System.out.println("Current context is now [" + cs.getContextId() + "]. Default state not found so state is current [" + cs.getState() + "]"); // TODO - remove or log 	
			logger.debug("Current context is now [" + cs.getContextId() + "]. Default state not found so state is current [" + cs.getState() + "]");
		} 	
	}
	
	public boolean isStateInCurrentContext(Class<?> clazz) {
		return getLastContext().isStateInContext(clazz);
	}
	
	private State getDefaultState(ContextState cs) {				
		State start = cs.getTopState();
		State defaultState = goForwardThruStates(start); 	
		return defaultState;
	}

	private State goForwardThruStates(State s) {
		boolean foundDefault = false;
		
		while (foundDefault == false && s != null && s.isDefaultState() == false) {
			if(s.getNext().isPresent()) {				
				s = s.getNext().get();
			}else {
				s = null;
			}
		} 	
		return s;
	}

	public void printContexts() {
		for (ContextState cs : queue.getQueue()) {
			System.out.println("CONTEXT: " + cs.getContextId()); // TODO - remove or log			
		}
	}
	
	public void printQueue() {
		for (ContextState cs : queue.getQueue()) {
			System.out.println("CONTEXT: " + cs.getContextId()); // TODO - remove or log
			printStates(cs);
		}
	}
		
	public boolean printStates(ContextState cs) {		
		State start = cs.getTopState();
		Optional<State> s = Optional.ofNullable(start);

//		System.out.println("*CURRENT STATES IN CURRENT CONTEXT*"); 
		while(s != null) {			
			if(s.isPresent()) {
				State temp = s.get(); 	
				System.out.println("State: " + temp.getClass().getSimpleName()); 
				s = temp.getNext();
			}else {
				s = null;
			}			
		}
		return false;
	}

	public ContextManager closeCurrentContext() {
		ContextCloser contextCloser = (ContextCloser) getLastContext();
		contextCloser.closeContext();
		// TODO - switch to default???????????????????????????????????????
		return this;
	}
	
	public void closeStateInCurrentContext(State state){
		closeState(state);
		updateContext(state);
	}

	private void closeState(State closeState) {
		logger.debug("Closing state [" + closeState + "]"); 	
		closeState.close();
	}
	
	private void updateContext(State state) {
		if(state.isContextCloser()) { 	
			logger.debug("State [" + state + "] is a context closer so will close current context");						
			queue.removeLastContext();
			setDefaultStateAfterClosingContext();
		}else { 	
			Optional<State> prev = closeCurrentStateAndGetPrevForCurrentContext(state);
			if(prev != null && prev.isPresent()) {
				revertToPreviousStateInCurrentContext(prev);
			}else {
				queue.getAndRemoveLastContext();
			}			
		}
	}
	
	public void closeCurrentStateInCurrentContext(){
		ContextState cs = queue.getLastContextInQueue(); 
		if(cs != null) {
			closeStateInCurrentContext(cs.getState());
		}else {
			logger.debug("No context so cannot close current state");
		}		
	}
		
	private Optional<State> closeCurrentStateAndGetPrevForCurrentContext(State current){
		Optional<State> prev = current.getPrev();
		current.close();
		return prev;
	}
	private void revertToPreviousStateInCurrentContext(Optional<State> prev) {
		prev.ifPresentOrElse(
				p -> { 
					queue.getLastContextInQueue().setState(p);
//					System.out.print("to ->" + p.toString()); // TODO - remove or log 	
				}, 
				new Runnable() {			
					@Override
					public void run() {
						queue.getLastContextInQueue().setNullState();				
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
		ContextState cs = queue.getLastContextInQueue();
		if(cs != null) {
			return cs.getContextId().getId();			
		}else {
			return null;
		}
	}	
	public ContextState getLastContext() {
		ContextState cs = queue.getLastContextInQueue(); 
		logInvalidContextIfNull(cs);
		return cs;
	}
	public ContextState getCurrentContext() {
		ContextState cs = queue.getCurrentContextInQueue(); 
		logInvalidContextIfNull(cs);
		return cs;
	}
	public void moveToExistingContext(ContextState cs) {
		queue.moveToExistingContext(cs);
	}
	
	// Queue
	public ContextQueue getQueue() {
		return queue;
	}
	public Optional<ContextState> getPrevContext(ContextState curr){
		return queue.getPrev(curr);
	}
	public Optional<ContextState> findContext(Object obj) {
		return queue.findContext(obj);
	}
	public ContextState getEndOfQueue() {
		return queue.getLastContextInQueue();
	}
	public ContextState getAndRemoveEndOfQueue() {
		return queue.getAndRemoveLastContext();
	}	
	public boolean removeCurrentContextFromQueue() {
		return queue.removeLastContext();
	}
	public boolean removeContextFromQueueForContextId(Object contId) {
		return queue.removeContextForContextId(contId);
	}
}
