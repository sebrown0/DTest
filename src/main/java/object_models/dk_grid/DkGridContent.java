/**
 * 
 */
package object_models.dk_grid;

import java.util.Collection;
import java.util.Map;
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

	public GridData<T> getGridData() {
		return gridData;
	}

	public Optional<String> getRowNumForKey(String key) {
		Optional<String> rowIdx = Optional.empty();
		Optional<Map<String, Row<?>>> leftContainer = gridData.getLeftContainer();
		
	 	if(leftContainer.isPresent()) {
	 		rowIdx = leftContainer.get()
	 				.values()
	 				.stream()	 				
	 				.filter(r -> r.getKeyForRow().equals(key))	 				
	 				.map(m -> m.getRowIdx())
	 				.findFirst();	 		
	 	}
		return rowIdx;
	}
	
	public Optional<Row<?>> getRowForRowIndex(String rowIdx){		
		return getRow(rowIdx);
	}

	public Optional<Row<?>> getRowForKeyValue(String keyVal){		
		Optional<String> rowIdx = getRowNumForKey(keyVal);		

		if(rowIdx.isPresent()) {
			return getRow(rowIdx.get());			
		}		
		return Optional.empty();
	}

	private Optional<Row<?>> getRow(String rowIdx) {
		Optional<Row<?>> row = gridData
				.getContainers()								// Map<String, Map<String, Row<?>>>
				.values()												// Map<String, Row<?>>
				.stream()																
				.map(Map::values)								
				.flatMap(Collection::stream)
				.filter(k -> k.getRowIdx().equals(rowIdx))
				.findFirst();		
		
		return row;
	}	
	
	public int getLastRowNum() {
		return lastRowNum;
	}

	public void setLastRowNum(int lastRowNum) {
		this.lastRowNum = lastRowNum;
	}
}
