/**
 * 
 */
package object_models.forms;

import object_models.helpers.title.PageTitle;

/**
 * @author SteveBrown
 *
 */
public interface ContainerAction {
	PageTitle getTitle();
	void closeElement();	
}
