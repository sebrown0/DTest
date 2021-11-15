/**
 * 
 */
package object_models.dk_grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 * Get the various attributes of the column header
 * and perform functions like sort, filter etc.
 * 
 * TODO - create header menu & filter menu.
 */
public class ColumnHeader {
	private WebElement myContainer;
	private WebElement colLabel;
	private HeaderCell headerCell;
	private String labelTxt;
	private String colId;
	private int colNum;
	
	public ColumnHeader(WebElement headerCell) {
		this.myContainer = headerCell;
		this.colLabel = headerCell.findElement(By.cssSelector("div[class^='ag-cell-label-container']"));
		setLabelTxt();
		setColId();
		setColNum();
	}
	
	private void setLabelTxt() {
		labelTxt = colLabel.findElement(By.className("ag-header-cell-label")).getText();	  	
	}
	
	private void setColId() {
		colId = myContainer.getAttribute("col-id"); 	
	}

	private void setColNum() {
		colNum = Integer.valueOf(colLabel.findElement(By.cssSelector("span[ref='eText']")).getAttribute("aria-colindex"));  	
	}
	
	public void setCellHeader(WebElement hdrCell) {
		headerCell = new HeaderCell(hdrCell); 	
	}
	
	public void filterColumn(String filterTxt) {
		headerCell.getFilter().filterColumn(filterTxt);		
	}
	
	public void sortColumn(SortDirection sortDir) {
		ColumnSorter sorter = new ColumnSorter(colLabel);
		sorter.sortColumn(sortDir);
	}

	public String getLabelTxt() {
		return labelTxt;
	}

	public String getColId() {
		return colId;
	}

	public int getColNum() {
		return colNum;
	}
	
}
