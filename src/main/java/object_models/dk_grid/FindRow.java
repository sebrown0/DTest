package object_models.dk_grid;

import java.util.Map;
import java.util.Optional;


/**
 * @author Steve Brown
 * 
 */
public abstract class FindRow implements KeyStrategyRow {
	private String key = ColumnName.EMP_CODE.value;
	
	protected FindRow(String key) {
		this.key = key;
	}

	@Override
	public Optional<String> getKey(Map<String, Cell> columns) {
		return CellVerifier.getValueForKey(key, columns);
	}
	
	@Override
	public String getStrategyName() {
		return key;
	}
	
	@Override
	public <T extends KeyStrategyRow> Row<T> getNewRow() {
		return new Row<>(this);
	}

}
