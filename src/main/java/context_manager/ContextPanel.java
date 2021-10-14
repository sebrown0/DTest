/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import object_models.panels.JsPanelControlBar;

/**
 * @author Steve Brown
 *
 */
public final class ContextPanel implements ContextState {
	private ContextManager contextManager;
	private State firstState;
	private State currentState;
	private ContextId contextId;
	
	public ContextPanel(ContextManager contextManager, JsPanelControlBar controlBar, ContextIdGetter idGetter) {
		this.contextManager = contextManager;		
		this.firstState = new StateTop(this, null);
	
		setContextId(idGetter);
		setState(firstState);
		setState(contextManager.getCallingState().getState(this, Optional.of(firstState)));		
	}

	public void setContextId(ContextIdGetter idGetter) {
		contextId = idGetter.getContextId();
	}
	
	@Override
	public void setState(State state) {
		State temp = currentState;
		currentState = state;
		currentState.setPrev(Optional.ofNullable(temp));
		LogManager.getLogger().debug("Setting new state [" + state.toString() + "]");
	}
	
	@Override
	public State getState() {
		return currentState;
	}

	@Override
	public Optional<State> getPreviousState() {
		return currentState.getPrev();
	}

	@Override
	public void moveNext() {
		Optional<State> next = currentState.getNext();
		next.ifPresentOrElse(n -> 
			{	this.currentState = n; System.out.println("moveNext->" + n.toString()); }, 
			new Runnable() {				
				@Override
				public void run() {
					LogManager.getLogger().debug("No next state for [" + currentState.toString() + "]");
				}
			});
		
	}

	@Override
	public WebDriver getDriver() {		
		return contextManager.getDriver();
	}

	@Override
	public void setNullState() {
		currentState = null;		
	}

	@Override
	public State getFirstState() {
		return firstState;
	}

	@Override
	public ContextId getContextId() {
		return contextId;
	}

}
