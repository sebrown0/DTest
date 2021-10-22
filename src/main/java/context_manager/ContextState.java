/**
 * 
 */
package context_manager;

import java.util.Optional;

import context_manager.states.State;
import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 * Manipulate states within a Context.
 */
public interface ContextState {
	void setNullState();
	void setCurrentState(State currentState);	
	void setState(State state);
	void moveNext();
	void switchToDefaultContent();
	
	State getState();
	State getFirstState();
	State getCallingState();
	State getTopState();
	
	<T extends State> Optional<State> moveToState(Class<T> clazzState);
	<T extends State> Optional<State> setLastState(Class<T> clazzState);
	<T extends State> Optional<State> getNewInstanceOfState(Class<T> clazzState);

	boolean isStateInContext(Class<?> clazz);	
	Optional<State> getPreviousState();
	ContextId getContextId();
	ContainerAction getContinerAction();	
}
