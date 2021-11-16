/**
 * 
 */
package object_models.dk_grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 * The elements [ColumnHeader] that make up the column headers
 * and filters.
 * 
 * A map of ColumnHeader[s] are created the key is the 
 * cell header name.
 * 
 */
public class DkGridHeader {
	private WebElement headerContainer;
	private WebElement headerRow;
	private Map<String, ColumnHeader> columnHeaders;
	
	public DkGridHeader() {
		this.columnHeaders = new HashMap<>();
	}

	public void setColumnHeaders(WebElement gridContainer) {
		headerContainer = gridContainer.findElement(By.cssSelector("div[class='ag-header-container']"));
		headerRow = headerContainer.findElement(By.cssSelector("div[class='ag-header-row ag-header-row-column']"));
		setHeaders();		
		setCellHeaders(); 	
	}

	private void setHeaders() {
		List<WebElement> headers = 
				headerRow.findElements(By.cssSelector("div[class^='ag-header-cell ag']")); // More than 1 element with ag-header-cell
			
		for (WebElement e : headers) {			
			ColumnHeader currentHeader = new ColumnHeader(e);
			columnHeaders.putIfAbsent(currentHeader.getLabelTxt(), currentHeader);
		}
	}
	
	private void setCellHeaders() {
		WebElement floatingFilter = headerContainer.findElement(By.cssSelector("div[class='ag-header-row ag-header-row-floating-filter']")); 
		List<WebElement> filters = floatingFilter.findElements(By.cssSelector("div[class='ag-header-cell ag-focus-managed']"));			
		for (WebElement e : filters) {			
			int colNum = Integer.valueOf(e.findElement(By.className("ag-floating-filter-body")).getAttribute("aria-colindex"));
			ColumnHeader header = getHeaderForColNum(colNum);			
			header.setCellHeader(e);			
		}
	}
	
	private ColumnHeader getHeaderForColNum(int colNum) {
		ColumnHeader header = null;
		for(Entry<String, ColumnHeader> entry: columnHeaders.entrySet()) {
			if(entry.getValue().getColNum() == colNum) {
				header = entry.getValue();
				break;
			}
		}
		return header;
	}
	
	public Map<String, ColumnHeader> getColumnHeaders(){
		return columnHeaders;
	}
	
	public void filterColumn(String filterCol, String filterTxt) {
		ColumnHeader header = columnHeaders.get(filterCol);
		header.filterColumn(filterTxt);		
	}
}
