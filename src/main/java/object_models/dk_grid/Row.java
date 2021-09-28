package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Cell.ColumnName;

/**
 * @author Steve Brown
 * 
 * A row in a DK Grid. 
 * The row can have any number of Cells.
 * Each row's key in the 
 */
public class Row {
	
	/*
	 * Get the column that is being used as the key for this row.	 * 
	 */	
	public interface KeyStrategyRow {
		Optional<String> getKey(Map<ColumnName, Cell> columns);
		String getStrategyName();
	}
	
	private KeyStrategyRow rowKeyStrategy;
	private Map<ColumnName, Cell> cells = new HashMap<>();
		
	public Row(KeyStrategyRow rowKeyStrategy) {		
		this.rowKeyStrategy = rowKeyStrategy;
	}
	
	public void addCell(ColumnName key, Cell col) {
		cells.putIfAbsent(key, col);
	}
	
	public Cell getCell(ColumnName key){
		return cells.get(key);
	}
	
	public Optional<String> getKey() {
		return rowKeyStrategy.getKey(cells);
	}
	
	public KeyStrategyRow getKeyStrategy() {
		return rowKeyStrategy;
	}
}
