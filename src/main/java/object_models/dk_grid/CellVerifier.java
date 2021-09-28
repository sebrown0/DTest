/**
 * 
 */
package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public class CellVerifier {
	public static Optional<String> getValueForKey(String key, Map<String, Cell> columns) {
		Optional<String> value = Optional.empty();
		if(columns.containsKey(key)) {
			value = columns.get(key).getValue();
		}
		return value;
	}
}
