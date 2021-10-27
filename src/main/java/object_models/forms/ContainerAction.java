/**
 * 
 */
package object_models.forms;

import context_manager.states.StateFactorySetter;
import object_models.helpers.Closable;
import object_models.helpers.title.PageTitle;

/**
 * @author Steve Brown
 *
 */
public interface ContainerAction extends Closable {
	PageTitle getTitle();	
	StateFactorySetter getStateFactorySetter();
}
