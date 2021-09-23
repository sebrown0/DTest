/**
 * 
 */
package object_models.forms;

import object_models.helpers.ChildElement;
import object_models.helpers.title.PageTitle;

/**
 * @author Steve Brown
 *
 */
public interface ContainerAction extends ChildElement{
	PageTitle getTitle();
//	void closeElement();	
}
