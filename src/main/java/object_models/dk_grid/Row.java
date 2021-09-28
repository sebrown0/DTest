package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 * 
 * A row in a DK Grid. 
 * The row can have any number of Cells.
 * Each row's key in the parent container is found 
 * using the key strategy.
 * 
 * @param
 * 	T = the cell that is used as the key for the row.
 */
public class Row <T extends KeyStrategyRow> {
	
	/*
	 * Get the column that is being used as the key for this row.	 * 
	 */	
	public interface KeyStrategyRow {
		Optional<String> getKey(Map<String, Cell> columns);
		String getStrategyName();
		Row<?> getNewRow();
	}
	
	private KeyStrategyRow rowKeyStrategy;
	private Map<String, Cell> cells = new HashMap<>();
		
	/*
	 * Instantiate with the strategy for 
	 */
	public Row(KeyStrategyRow rowKeyStrategy) {		
		this.rowKeyStrategy = rowKeyStrategy;
	}
	
	public void addCell(String key, Cell col) {
		cells.putIfAbsent(key, col);
	}
	
	public Cell getCell(String key){
		return cells.get(key);
	}
	
	public Optional<String> getKey() {
		return rowKeyStrategy.getKey(cells);
	}
	
	public KeyStrategyRow getKeyStrategy() {
		return rowKeyStrategy;
	}
}
