/**
 * 
 */
package object_models.common.nav;

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
