/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import context_manager.contexts.Context;
import context_manager.states.State;
import context_manager.states.StateLeftMenu;
import object_models.forms.ContainerAction;
import object_models.panels.JsPanel;
import object_models.panels.PanelSwitcher;

/**
 * @author Steve Brown
 *
 * Manages the contexts within the app.
 * 
 */
public class ContextManager implements CurrentContext {
	private ContextQueue queue = new ContextQueue();
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
	
	public void switchToDefaultStateInContext(ContextState cs) {
		stateManager.switchToDefaultStateInContext(cs);
	}
	
	public void switchToDefaultStateInCurrentContext() {
		stateManager.switchToDefaultStateInCurrentContext();
	}
	
	public <T extends State> Optional<State> switchToStateInCurrentContext(Class<T> clazzRequiredState) {
		return stateManager.switchToStateInCurrentContext(clazzRequiredState);
	}
	
	public void switchToLeftMenu() {
		Optional<ContextState> first = getContextThatIsFirstContext();
		first.ifPresentOrElse(f -> {			 		
			stateManager.switchToStateInContext(StateLeftMenu.class, f);	
		},
			new Runnable() {				
				@Override
				public void run() {					
					System.out.println("CM -> switchToLeftMenu -> LOAD NEW CONTEXT *NOT IMPLENTED*"); // TODO - remove or log
					LogManager.getLogger().error("CM -> switchToLeftMenu -> LOAD NEW CONTEXT *NOT IMPLENTED*");
				}
		});
	}
	
	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
		PanelSwitcher<T> switcher = new PanelSwitcher<>(panel, this);
		switcher.switchToExistingPanel();
	}
	
	public <T extends State> Optional<State> moveToStateInContext(Class<T> clazzRequiredState, ContextState cs) {		
		return stateManager.moveToStateInContext(clazzRequiredState, cs);						
	}
	
	public <T extends State> void moveToDefaultStateInContext(ContextState cs) {	
		stateManager.moveToDefaultStateInContext(cs);
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
		
	public boolean isCurrentContext(ContextState cs) {
		return cs.equals(getCurrentContext());
	}
	
	public boolean isStateInCurrentContext(Class<?> clazz) {
		return stateManager.isStateInCurrentContext(clazz, this);
	}
	
	public void closeCurrentStateInCurrentContext(){
		stateManager.closeCurrentStateInCurrentContext(this);
	}
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
	public Optional<ContextState> getContextThatIsFirstContext() {
		ContextState curr = getCurrentContext();
		if(curr != null && !(curr instanceof FirstContext)){
			curr = null;
			for (ContextState cs : queue.getQueue()) {
				curr = cs;
				if(curr instanceof FirstContext){
					break;
				}
			}			
		}
		return Optional.ofNullable(curr);
	}
	
	public Optional<JsPanel> getContextThatIsPanel() {
		ContainerAction curr = getCurrentContext().getContinerAction();
		if(!(curr instanceof JsPanel)){
			curr = null;
			for (ContextState cs : queue.getQueue()) {
				curr = cs.getContinerAction();
				if(curr instanceof JsPanel){
					break;
				}
			}			
		}
		return Optional.ofNullable((JsPanel) curr);
	}
	
	public String getContextIdOfCurrentContext() {
		ContextState cs = queue.getCurrentContextInQueue();
		return getContextId(cs);
	}	
//	public String getContextIdOfLastContext() {
//		ContextState cs = queue.getLastContextInQueue();
//		return getContextId(cs);
//	}	
	private String getContextId(ContextState cs) {
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

	public void deleteContext(ContextState cs) {
		queue.removeAndCloseContext(cs);		
	}
	public boolean removeCurrentContextFromQueue() {
		return queue.removeLastContext();
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
	public void setCurrentContextState(ContextState cs) {
		queue.moveToExistingContext(cs);		
	}

	@Override
	public ContextState getCurrentContextState() {
		return queue.getCurrentContextInQueue();
	}
	
}
