package object_models.dk_grid;

import java.util.Optional;

public class Cell {
	private String id;
	private String compId;
	private String unselectable;
	private String value;
	private Integer rowNum;
	private String containerName;
		
	public Cell(String containerName, String id, String value, String compId, String unselectable, Integer rowNum) {
		this.containerName = containerName;
		this.id = id;
		this.value = value;
		this.compId = compId;
		this.unselectable = unselectable;
		this.rowNum = rowNum;
	}

	public String getContainerName() {
		return containerName;
	}
	
	public String getColumnId() {
		return id;
	}
	
	public Optional<String> getValue() {
		return Optional.ofNullable(value);
	}

	public String getCompId() {
		return compId;
	}

	public String getUnselectable() {
		return unselectable;
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", compId=" + compId + ", unselectable=" + unselectable + ", value=" + value + ", rowNum="
				+ rowNum + "]";
	}
	
}
