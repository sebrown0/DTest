/**
 * 
 */
package object_models.forms;

import object_models.helpers.Closable;
import object_models.helpers.title.PageTitle;

/**
 * @author Steve Brown
 *
 */
public interface ContainerAction extends Closable{
	PageTitle getTitle();
//	void closeElement();	
}
