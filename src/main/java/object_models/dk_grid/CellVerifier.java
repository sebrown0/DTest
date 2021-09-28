/**
 * 
 */
package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Cell.ColumnName;

/**
 * @author Steve Brown
 *
 */
public class CellVerifier {
	public static Optional<String> getValueForKey(ColumnName key, Map<ColumnName, Cell> columns) {
		Optional<String> value = Optional.empty();
		if(columns.containsKey(key)) {
			value = columns.get(key).getValue();
		}
		return value;
	}
}
