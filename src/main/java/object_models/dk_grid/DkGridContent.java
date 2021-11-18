/**
 * 
 */
package object_models.dk_grid;

import java.util.Map.Entry;
import java.util.Optional;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public class DkGridContent <T extends KeyStrategyRow> {	
	private GridDataRows<T> gridData;
	private int lastRowNum;
	private int lastColNum;

	public DkGridContent() {
		gridData = new GridDataRows<>();
		lastRowNum = -1; // Rows are zero based, so to begin with we have no rows.
	}

	public void addRow(Row<?> row) {
		gridData.addRow(row);
	}
	
	public void clearAll() {
		gridData.clearRows();
	}

	/*
	 * If the cell that acts as the key for the row is present.
	 * Compare it's value to that of the required key.
	 * If match return the row number.
	 */
	public Optional<Integer> getRowNumForKeyValue(String key) {
		Optional<Integer> rowIdx = Optional.empty();		
		for(Entry<Integer, Row<?>> e : gridData.getRows().entrySet()) {
			Cell keyCell = e.getValue().getKeyCell(); 
			if(keyCell != null && keyCell.getOriginalValue().isPresent()) {
				String keyVal = keyCell.getOriginalValue().get();
				if(keyVal.equalsIgnoreCase(key)){
					rowIdx = Optional.ofNullable(e.getKey());
					break;
				}
			}
		}
		return rowIdx;
	}
	
	public Optional<Row<?>> getRowForRowIndex(Integer rowIdx){		
		return getRow(rowIdx);
	}

	public Optional<Row<?>> getRowForKeyValue(String keyVal){		
		Optional<Integer> rowIdx = getRowNumForKeyValue(keyVal);
		if(rowIdx.isPresent()) {
			return getRow(rowIdx.get());			
		}		
		return Optional.empty();
	}

	private Optional<Row<?>> getRow(Integer rowIdx) {
		return gridData.getRow(rowIdx);
	}
	
	public int getLastRowNum() {
		return lastRowNum;
	}
	public int getLastColNum() {
		return lastColNum;
	}
	public Cell getCell(Integer rowIdx,String colName) {
		return gridData.getCell(rowIdx, colName);
	}
	public void setLastRowNum(int lastRowNum) {
		this.lastRowNum = lastRowNum;
	}
	public void setLastColNum(int lastColNum) {
		this.lastColNum = lastColNum;
	}
}
