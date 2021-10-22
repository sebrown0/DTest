/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
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
 * 5. Split states and contexts correctly. Add new helper classes as necessary. DONE
 * 
 * 6. When looking for a state, should the context be checked?
 * 
 * 7. Add a DQueue for states as well as contexts.
 *    This would eliminate the need to loop thru state to get to
 *    the top or bottom.
 * 
 * USE GIT BRANCH context_manager.
 */
public class ContextManager implements CurrentContextGetter {
	private ContextQueue queue = new ContextQueue();
//	private Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private StateManager stateManager;
	
	// The state that initiates the context, i.e. LeftMenu
	// This has to be added to the firstState as the next state.	
	private CallingState callingState;	
	
	public ContextManager(WebDriver driver) {
		this.driver = driver;
		this.stateManager = new StateManager(this);
	}		
	
	/*
	 * Context Start
	 */
	public void setFirstContext(Context context) {
		queue.addContextToQueue(context);
	}
	
	public void deleteContext(ContextState cs) {
		queue.removeContextForContextId(cs);
	}
	
	public ContextManager closeCurrentContext() {
		ContextCloser contextCloser = (ContextCloser) getCurrentContext();
		contextCloser.closeContext();
		// TODO - switch to default???????????????????????????????????????
		return this;
	}
		
//	public void updateContextAfterStateDeletion(State state) {
//		ContextUpdater updater = new ContextUpdater(stateManager);
//		updater.updateContextAfterStateDeletion(state);
//		
////		if(state.isContextCloser()) { 	
////			logger.debug("State [" + state + "] is a context closer so will close current context");						
////			queue.removeLastContext();
////			stateManager.setDefaultStateAfterClosingContext();
////		}else { 	
////			Optional<State> prev = stateManager.closeCurrentStateAndGetPrevForCurrentContext(state);
////			if(prev != null && prev.isPresent()) {
////				stateManager.revertToPreviousStateInCurrentContext(prev);
////			}else {
////				queue.getAndRemoveLastContext();
////			}			
////		}
//	}
	/*
	 * Context End
	 */

	 
	/*
	 * State - Start
	 */
	public ContextManager setNextState(State state) {		
		stateManager.setNextState(state);
		return this;
	}
	
	public Optional<ContextState> getPenultimateContext() {
		return queue.getPenultimate();
	}
	
	public <T extends State> Optional<State> switchToStateInCurrentContext(Class<T> clazzRequiredState) {
		return stateManager.switchToStateInCurrentContext(clazzRequiredState);
	}
	
	public <T extends State> Optional<State> switchToStateInContext(Class<T> clazzRequiredState, ContextState findCs) {
		/*
		 * load context if ness
		 */
		return stateManager.switchToStateInContext(clazzRequiredState, findCs);
	}
	
	public <T extends State> Optional<State> moveToStateInContext(Class<T> clazzRequiredState, ContextState cs) {
		/*
		 * load context if ness
		 */
		return stateManager.moveToStateInContext(clazzRequiredState, cs);						
	}
	
	public <T extends State> Optional<State> moveToStateInCurrentContext(Class<T> clazzRequiredState) {
		return stateManager.moveToStateInContext(clazzRequiredState, getCurrentContext());			
	}
	
	public void moveToStateInCurrentContext(State state) {
		stateManager.moveToStateInCurrentContext(state, this);	
	}
	
	public void switchToFirstStateInCurrentContext() {
		stateManager.switchToFirstStateInCurrentContext();
	}
	
	public State moveToNextStateInCurrentContext(){
		return stateManager.moveToNextStateInCurrentContext();
	}
		
	public boolean isStateInCurrentContext(Class<?> clazz) {
		return stateManager.isStateInCurrentContext(clazz, this);
	}
	
	public void closeCurrentStateInCurrentContext(){
		stateManager.closeCurrentStateInCurrentContext(this);
	}
	
//	public void closeStateInCurrentContext(State state){
//		stateManager.closeStateInCurrentContext(state);
//	}
	/*
	 * State - End
	 */
		
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
	public ContextManager moveToExistingContext(ContextState cs) {
		queue.moveToExistingContext(cs);
		return this;
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
//	public ContextState getAndRemoveEndOfQueue() {
//		return queue.getAndRemoveLastContext();
//	}	
	public boolean removeCurrentContextFromQueue() {
		return queue.removeLastContext();
	}
	public boolean removeContextFromQueueForContextId(Object contId) {
		return queue.removeContextForContextId(contId);
	}

	public void printContexts() {
		ContextManagerPrinter printer = new ContextManagerPrinter(queue);
		printer.printContexts();
	}	
	public void printQueue() {
		ContextManagerPrinter printer = new ContextManagerPrinter(queue);
		printer.printQueue();
	}

	@Override
	public ContextState getCurrentContextState() {
		return queue.getCurrentContextInQueue();
	}
}
