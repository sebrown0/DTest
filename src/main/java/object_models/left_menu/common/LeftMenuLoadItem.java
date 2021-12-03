/**
 * 
 */
package object_models.left_menu.common;

import java.util.Optional;

import object_models.forms.ContainerAction;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface LeftMenuLoadItem {	
	Optional<ContainerAction> clickAndLoad(Class<?> clazz); 
}
