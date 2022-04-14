/**
 * 
 */
package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import object_models.el_dk_grid.Cell;
import object_models.el_dk_grid.Row;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Increment the keys (row nums) of an existing map of Rows<?>.
 * Also, update the row nums int the rows' cells.
 * 
 */
public class KeyIncrementer {	
	
	public static Map<Integer, Row<?>> incrementKeyValues(Map<Integer, Row<?>> map) {
		Map<Integer, Row<?>> newMap = new HashMap<>();
		
		map.entrySet().forEach(e -> {
			Integer idx = e.getKey() + 1;
			updateCells(e.getValue().getCells(), idx);
			newMap.put(idx, e.getValue());
		});		
		return newMap;
	}
	
	private static void updateCells(List<Cell> cells, Integer idx) {
		cells.forEach(c -> c.setRowNum(idx));
	}
	
}
