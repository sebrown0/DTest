/**
 * 
 */
package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;



/**
 * @author Steve Brown
 *
 * The strategy for mapping & retrieving a row from a grid.
 * 
 */
public interface KeyStrategyRow {
	Optional<String> getKey(Map<String, Cell> columns);
	String getStrategyName();
	public <T extends KeyStrategyRow> Row<T> getNewRow();
}
