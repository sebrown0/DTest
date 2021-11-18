package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;


/** 
 * The rows in a grid.
 * 
 * @author SteveBrown
 * @since 1.0
 * @version 1.0
 */
public class GridDataRows <T extends KeyStrategyRow> {
	private Map<Integer, Row<?>> rows = new HashMap<>();
	
	public void addRow(Row<?> row) {
		rows.putIfAbsent(row.getRowIdx(), row);			
	}

	public void clearRows() {
		rows.clear();		
	}
	
	public Optional<Row<?>> getRow(Integer rowIdx) {
		return Optional.ofNullable(rows.get(rowIdx));
	}

	public Map<Integer, Row<?>> getRows() {
		return rows;
	}
	
	public Cell getCell(Integer rowIdx, String colName) {
		Cell cell = null;
		if(rows.containsKey(rowIdx)) {
			Row<?> row = rows.get(rowIdx);
			cell = row.getCell(colName);
		}else {
			LogManager.getLogger().error("Row does not exist");
		}
		return cell;
	}
}
