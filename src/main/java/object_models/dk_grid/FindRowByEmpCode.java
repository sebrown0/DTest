package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Cell.ColumnName;
import object_models.dk_grid.Row.KeyStrategyRow;

public class FindRowByEmpCode implements KeyStrategyRow {
	private static final ColumnName KEY = ColumnName.EMP_CODE;
	
	@Override
	public Optional<String> getKey(Map<ColumnName, Cell> columns) {
		return CellVerifier.getValueForKey(KEY, columns);
	}
	
	@Override
	public String getStrategyName() {
		return KEY.name();
	}
}
