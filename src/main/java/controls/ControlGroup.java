/**
 * 
 */
package controls;

import java.util.Optional;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ControlGroup {
	Optional<Control> getControlByTitle(String title);
}
