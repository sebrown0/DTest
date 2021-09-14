/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public interface LeftMenuElements {
	Map<String, Optional<List<String>>> getAll();
}
