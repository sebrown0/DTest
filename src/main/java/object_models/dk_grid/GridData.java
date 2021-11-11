package object_models.dk_grid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import object_models.dk_grid.Row.KeyStrategyRow;
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
public class GridData <T extends KeyStrategyRow> {
//	private Map<String, Map<String, Row<?>>> containers = new HashMap<>();
//	private Logger logger = LogManager.getLogger();
	
	private Map<Integer, Row<?>> rows = new HashMap<>();
	
	public void addRow(Row<?> row) {
		rows.putIfAbsent(row.getRowIdx(), row);			
	}

	public Optional<Row<?>> getRow(Integer rowIdx) {
		return Optional.ofNullable(rows.get(rowIdx));
	}
	
//	public void addRow(String containerName, Row<?> row) {
//		mapRowToContainer(containerName, row);			
//	}

//	private void mapRowToContainer(String containerName, Row<?> row) {
//		Optional<Map<String, Row<?>>> container = Optional.ofNullable(containers.get(containerName));
//		
//		if(container.isPresent()) {
//			logger.debug("Adding to existing container [" + containerName +"]");
//			container.get().putIfAbsent(row.getRowIdx(), row);
//		}else {
//			logger.debug("Adding new container [" + containerName +"]");
//			Map<String, Row<?>> rowMap = new HashMap<>();
//			rowMap.put(row.getRowIdx(), row);
//			containers.put(containerName, rowMap);
//		}
//	}

	
//	public List<Row<?>> getRows(){
//		@SuppressWarnings("unchecked")
//		List<Row<?>> rows = 
//				(List<Row<?>>) containers.entrySet().stream()
//			.map(container -> container.getValue().entrySet().stream().map(row -> row.getValue()).collect(Collectors.toCollection(ArrayList::new)));
//		
//		for (Row<?> r : rows) {
//			System.out.println("->>>>>" + r.getKeyForRow()); // TODO - remove or log 	
//		}
//		return null;
//	}
	
//	public Optional<Map<String, Row<?>>> getLeftContainer() {
//		return Optional.ofNullable(containers.get("eLeftContainer"));
//	}
//	
//	public Optional<Map<String, Row<?>>> getCentreContainer() {
//		return Optional.ofNullable(containers.get("eCenterContainer"));
//	}
//	
//	public Optional<Map<String, Row<?>>> getRightContainer() {
//		return Optional.ofNullable(containers.get("eRightContainer"));
//	}
//	
//	public Optional<Map<String, Row<?>>> getContainer(String containerName) {
//		return Optional.ofNullable(containers.get(containerName));
//	}
	
//	@SuppressWarnings("unchecked")
//	public Optional<Row<?>> getRow(String container, String key) {
//		Optional<Map<String, Row<?>>> row = getContainer(container);
//		if(row.isPresent()) {
//			return Optional.ofNullable((Row<T>)row.get().get(key)); 
//		}else {
//			return Optional.empty();	
//		}		
//	}

//	public Map<String, Map<String, Row<?>>> getContainers() {
//		return containers;
//	}
}
