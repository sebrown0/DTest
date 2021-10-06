package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 * 
 */
public class FindRowByEmpCode implements KeyStrategyRow {
	private static final String KEY = ColumnName.EMP_CODE.value;
	
	@Override
	public Optional<String> getKey(Map<String, Cell> columns) {
		return CellVerifier.getValueForKey(KEY, columns);
	}
	
	@Override
	public String getStrategyName() {
		return KEY;
	}
	
	@Override
	public <T extends KeyStrategyRow> Row<T> getNewRow() {
		return new Row<>(this);
	}

}
