/**
 * 
 */
package object_models.pages.homepage;

import java.util.Optional;

import object_models.forms.ContainerAction;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 */
public interface LeftMenuLoadItem {
	Optional<ContainerAction> loadLeftMenuItem(Class<?> clazz);
}
