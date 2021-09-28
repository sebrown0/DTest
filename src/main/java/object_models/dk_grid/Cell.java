package object_models.dk_grid;

import java.util.Optional;

public class Cell {
	private String id;
	private String compId;
	private String unselectable;
	private String value;
	
	public enum ColumnName {
		COL_ID, EMP_CODE, ALL_NAME, IDENTITY_CARD_NO, TITLE, SURNAME, 
		NAME, TOWN, POST_CODE, COUNTRY, DOB
	}
	
	public Cell(String id, String value, String compId, String unselectable) {
		this.id = id;
		this.value = value;
		this.compId = compId;
		this.unselectable = unselectable;
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
	
}
