package object_models.dk_grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebElement;

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
		public <T extends KeyStrategyRow> Row<T> getNewRow();
	}
	
	private KeyStrategyRow rowKeyStrategy;
	private Map<String, Cell> cells = new HashMap<>();
	private String keyColumnName;
	private String keyRow;
	private String rowIdx;
		
	public Map<String, Cell> getCells() {
		return cells;
	}

	/*
	 * Instantiate with the strategy for 
	 */
	public Row(KeyStrategyRow rowKeyStrategy) {		
		this.rowKeyStrategy = rowKeyStrategy;
	}
	
	public void setRowIdx(String rowIdx) {
		this.rowIdx = rowIdx;
	}
	
	public String getRowIdx() {
		return rowIdx;
	}
	
	public void setKeyColumnName(List<WebElement> cellElements) {
		String id = null;
		String idCol = rowKeyStrategy.getStrategyName();
		
		for (WebElement e : cellElements) {
			id = e.getAttribute("col-id");		
			if(id.equalsIgnoreCase(idCol)) {
				keyColumnName = id;				
				break;
			}
		}
	}	
	
	public String getKeyColumnName() {
		return keyColumnName;
	}
	
	public void setKeyForRow(String keyRow) {
		this.keyRow = keyRow;
	}
	
	public String getKeyForRow() {
		return keyRow;
	}
	
	public void addCells(Map<String, Cell> cells) {
		this.cells = cells;
	}
	
	public Cell getCell(String key){
		return cells.get(key);
	}
	
//	public Optional<String> getKey() {
//		return rowKeyStrategy.getKey(cells);
//	}
	
	public KeyStrategyRow getKeyStrategy() {
		return rowKeyStrategy;
	}
}

//public void addCell(String key, Cell cell) {
//System.out.println("addCell -> key =" + key + " " + cell.toString() );
//cells.putIfAbsent(key, cell);
//}
//public void addCell(Cell cell) {
//System.out.println("addCell -> key =" + key + " " + cell.toString() );
//cells.putIfAbsent(cell.getColumnId(), cell);
//}
//