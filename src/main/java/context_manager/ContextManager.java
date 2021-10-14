/**
 * 
 */
package context_manager;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 * 
 */
public class ContextManager {
	private Queue<ContextState> contextQueue = new LinkedList<>();
	private ContextState context;
	private WebDriver driver;
	// The state that initiates the context, i.e. LeftMenu
	// This has to be added to the firstState as the next state.	
	private CallingState callingState;	
	
	public ContextManager(WebDriver driver) {
		this.driver = driver;
	}
		
	public ContextManager setNextState(State state) {
		State current = context.getState();
		current.setNext(Optional.ofNullable(state));
		return this;
	}
	
	public void switchToFirstState() {
		context.getFirstState().switchToMe();
	}
	
	public void moveNext(){
		context.moveNext();
	}
	
	public void closeCurrent(){
//		System.out.println("closeCurrent->" + context.getState().toString());
		Optional<State> prev = closeStateAndGetPrev();
		revertToPreviousState(prev);
		
		System.out.println("size->" + contextQueue.size());
		contextQueue.remove();
		System.out.println("size->" + contextQueue.size());
		
	}
	
	private Optional<State> closeStateAndGetPrev(){
		return context.getState().close();
	}
	private void revertToPreviousState(Optional<State> prev) {
		prev.ifPresentOrElse(
				p -> context.setState(p), 
				new Runnable() {			
					@Override
					public void run() {
						context.setNullState();				
					}
		});
	}

	private void logInvalidContextIfNull() {
		if(context == null) {
			LogManager.getLogger().error("Context is null");
		}
	}
	
	/*
	 * Getters / Setters
	 */
	public ContextState getContext() {
		logInvalidContextIfNull();
		return context;
	}
	public void setContext(ContextState context) {
		this.context = context;
		contextQueue.add(context);
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
}
