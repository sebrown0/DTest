package object_models.dk_grid;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author Steve Brown
 * 
 * The grid data is held in: Containers -> Rows -> Columns * 
 * 	Containers:
 * 		key = (String) name of the container.
 * 		val = (Row) of Map<String, Row>>
 * 	Row:
 * 		key = (String) this is found using the KeyStrategyRow, which 
 * 					return the value of the column that is used as the key.
 * 		val = Row
 */
public class GridData {
	private Map<String, Map<String, Row>> containers = new HashMap<>();
	private Logger logger = LogManager.getLogger();
	
	public void addRow(String containerName, Row row) {		
		row.getKey().ifPresentOrElse(
				k -> mapRowToContainer(containerName, row, k), 
				new Runnable() {					
					@Override
					public void run() {
						logger.debug("Could not get key using key strategy [" + row.getKeyStrategy().getStrategyName() + "]");
					}
				});		
	}

	private void mapRowToContainer(String containerName, Row row, String rowKey) {
		Optional<Map<String, Row>> container = Optional.ofNullable(containers.get(containerName));
		
		if(container.isPresent()) {		
			container.get().putIfAbsent(rowKey, row);
		}else {
			logger.debug("Adding new container [" + containerName +"]");
			Map<String, Row> rowMap = new HashMap<>();
			rowMap.put(rowKey, row);
			containers.put(containerName, rowMap);
		}
	}
	
	public Optional<Map<String, Row>> getContainer(String container) {
		return Optional.ofNullable(containers.get(container));
	}
	
	public Optional<Row> getRow(String container, String key) {
		Optional<Map<String, Row>> row = getContainer(container);
		if(row.isPresent()) {
			return Optional.ofNullable(row.get().get(key)); 
		}else {
			return Optional.empty();	
		}		
	}
	
//	public void prntRows() {
//		System.out.println("S ->" +	getContainer("leftContainer").get().size());
//		getContainer("leftContainer").ifPresent(c -> c.forEach((k,v) -> { System.out.println("Cont key ->" + k + "<-"); }));
//	}
}
