/**
 * 
 */
package object_models.dk_grid;

import java.util.Map.Entry;
import java.util.Optional;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class DkGridContent <T extends KeyStrategyRow> {	
	private GridData<T> gridData;
	private int lastRowNum;

	public DkGridContent() {
		gridData = new GridData<>();
	}

	public void addRow(Row<?> row) {
		gridData.addRow(row);
	}

	/*
	 * If the cell that acts as the key for the row is present.
	 * Compare it's value to that of the required key.
	 * If match return the row number.
	 */
	public Optional<Integer> getRowNumForValue(String key) {
		Optional<Integer> rowIdx = Optional.empty();		
		for(Entry<Integer, Row<?>> e : gridData.getRows().entrySet()) {
			Cell keyCell = e.getValue().getKeyCell(); 
			if(keyCell != null && keyCell.getValue().isPresent()) {
				String keyVal = keyCell.getValue().get();
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
		Optional<Integer> rowIdx = getRowNumForValue(keyVal);
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

	public void setLastRowNum(int lastRowNum) {
		this.lastRowNum = lastRowNum;
	}
}
