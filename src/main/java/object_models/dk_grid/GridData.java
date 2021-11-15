package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author Steve Brown
 * 
 * 
 */
public class GridData <T extends KeyStrategyRow> {
	private Map<Integer, Row<?>> rows = new HashMap<>();
	
	public void addRow(Row<?> row) {
		rows.putIfAbsent(row.getRowIdx(), row);			
	}

	public Optional<Row<?>> getRow(Integer rowIdx) {
		return Optional.ofNullable(rows.get(rowIdx));
	}

	public Map<Integer, Row<?>> getRows() {
		return rows;
	}
}
