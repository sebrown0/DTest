package object_models.dk_grid;

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
//	private Map<String, Cell> cells = new HashMap<>();
	private List<Cell> cells;
	private String keyColumnName;
	private Cell keyCell;	
//	private String keyRow;
	private int rowIdx;
		
	/*
	 * Instantiate with the strategy for 
	 */
	public Row(KeyStrategyRow rowKeyStrategy) {		
		this.rowKeyStrategy = rowKeyStrategy;
	}
	
	public void setRowIdx(int rowIdx) {
		this.rowIdx = rowIdx;
	}
	
	public Integer getRowIdx() {
		return rowIdx;
	}
	
	public void setKeyColumnName(List<WebElement> cellElements) {
		String id = null;
		String idCol = rowKeyStrategy.getStrategyName();
		//employee_code
		for (WebElement e : cellElements) {
			id = e.getAttribute("col-id");		
			if(id.equalsIgnoreCase(idCol)) {
				keyColumnName = id;				
				break;
			}
		}
	}	
	
//	public void addCell(String key, Cell cell) {
////		System.out.println("addCell -> key =" + key + " " + cell.toString() );
//		cells.putIfAbsent(key, cell);
//	}
	
	public String getKeyColumnName() {
		return keyColumnName;
	}
	
//	public void setKeyForRow(String keyRow) {
//		this.keyRow = keyRow;
//	}
	public void setKeyForRow(Cell keyCell) {
		this.keyCell = keyCell;
	}
	
//	public String getKeyForRow() {
//		return keyRow;
//	}
	
	public void addCells(List<Cell> cells) {
		this.cells = cells;
	}
	public List<Cell> getCells() {
		return cells;
	}
	
	public Cell getCell(String key){
		Cell cell = null;
		for (Cell c : cells) {
			if(c.getColumnId().equalsIgnoreCase(key)) {
				cell = c;
				break;
			}
		}
		return cell;
	}
		
//	public Cell getCell(String key){
//		return cells.get(key);
//	}
	
//	public Optional<String> getKey() {
//		return rowKeyStrategy.getKey(cells);
//	}

	public Cell getKeyCell() {
		return keyCell;
	}

	public KeyStrategyRow getKeyStrategy() {
		return rowKeyStrategy;
	}
}



//