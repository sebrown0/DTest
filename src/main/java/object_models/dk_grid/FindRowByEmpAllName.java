package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Row.KeyStrategyRow;

public class FindRowByEmpAllName implements KeyStrategyRow {
	private static final String KEY = ColumnName.ALL_NAME.value;
	
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
