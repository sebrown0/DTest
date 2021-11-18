package object_models.dk_grid;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import exceptions.ElementDoesNotExistException;

/**
 * Cell in a DKGrid.
 * 
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public class Cell {
	private String id;
	private String compId;
	private String unselectable;
	private String value;
	private String containerName;
	private String colName;
	private Integer rowNum;
	private Integer colNum;
	private WebElement gridContainer;
		
	public Cell(WebElement gridContainer) {
		this.gridContainer = gridContainer;
	}
	
	public WebElement getMyElement() {
		WebElement cell = null;
		try {
			WebElement row = gridContainer.findElement(By.xpath("//div[@role='row' and @row-index='" + rowNum + "']"));
			cell = row.findElement(By.xpath("//div[@role='gridcell' and @col-id='" + id + "']"));	
		} catch (Exception e) {
			new ElementDoesNotExistException("Could not get cell as element").run();
		} 	
		return cell;
	}
	
	public String getContainerName() {
		return containerName;
	}	
	public String getColumnId() {
		return id;
	}
	public Optional<String> getOriginalValue() {
		return Optional.ofNullable(value);
	}
	public Optional<String> getCurrentValue() {
		return Optional.ofNullable(getMyElement().getText());
	}
	public String getCompId() {
		return compId;
	}
		public String getColName() {		
		return colName;
	}
	public String getUnselectable() {
		return unselectable;
	}

	public Cell setId(String id) {
		this.id = id;
		return this;
	}

	public Cell setCompId(String compId) {
		this.compId = compId;
		return this;
	}

	public Cell setUnselectable(String unselectable) {
		this.unselectable = unselectable;
		return this;
	}

	public Cell setValue(String value) {
		this.value = value;
		return this;
	}

	public Cell setContainerName(String containerName) {
		this.containerName = containerName;
		return this;
	}

	public Cell setColName(String colName) {
		this.colName = colName;
		return this;
	}

	public Cell setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
		return this;
	}

	public Cell setColNum(Integer colNum) {
		this.colNum = colNum;
		return this;
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", compId=" + compId + ", unselectable=" + unselectable + ", value=" + value
				+ ", containerName=" + containerName + ", colName=" + colName + ", rowNum=" + rowNum + ", colNum=" + colNum
				+ "]";
	}

	
}
