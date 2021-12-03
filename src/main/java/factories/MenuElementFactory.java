/**
 * 
 */
package factories;

import object_models.forms.ContainerAction;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface MenuElementFactory {
	ContainerAction getElementForName(String elementName);
}
