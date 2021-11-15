/**
 * 
 */
package object_models.dk_grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * @author Steve Brown
 *
 */
public class DkGridContentReader <T extends KeyStrategyRow> {
	private WebElement gridContainer;
	private WebElement contentElement;	
	private Logger logger = LogManager.getLogger();
	private DkGridContent<?> gridContent;
	private KeyStrategyRow keyStrategyRows;
	private String currentContainerName;
	private int currentLastRow = -1;	
	private List<Cell> currentCellList; 
	private Row<T> currentRow;
	
	public DkGridContentReader(WebElement gridContainer, DkGridContent<?> gridContent, KeyStrategyRow keyStrategyRows) {
		this.gridContainer = gridContainer;
		this.gridContent = gridContent;
		this.keyStrategyRows = keyStrategyRows;		
	}
	
	public void read() {
		setContentElement();
		loopContainers();
	}
	
	private void setContentElement() {
		contentElement = gridContainer.findElement(By.cssSelector("div[class='ag-body-viewport ag-layout-normal ag-row-animation']"));
	}
		
	private void loopContainers(){
		mapLeftPinned();
		mapCentre();
//		mapRightPinned();		
	}
	
	private void mapLeftPinned() {
		currentContainerName = "ag-pinned-left-cols-container";
		mapContainer(By.cssSelector("div[class='" + currentContainerName + "']"));
	}
	
	private void mapCentre() {
		currentContainerName = "ag-center-cols";
		mapContainer(By.cssSelector("div[class^='" + currentContainerName + "']"));
	}
	
//	private void mapRightPinned() {
		// currentContainerName = "ag-pinned-right-cols-container ag-hidden";
		// TODO
//	}
	
	private void mapContainer(By containerLocator) {
		WebElement container = contentElement.findElement(containerLocator);		
		getRowsInContainer(container);
	}
	
	private void getRowsInContainer(WebElement container) {
		if(container != null) {			
			String containerName = container.getAttribute("ref");						
			container
				.findElements(By.cssSelector("div[role='row']"))
				.forEach(row -> { 
					mapRowFromContainer(row, containerName); 

				});	
		}else {
			logger.info("No rows in container [" + currentContainerName + "]");
		}		
	}
		
	private void mapRowFromContainer(WebElement rowElement, String containerName) {				
		Integer rowIdx = Integer.valueOf(rowElement.getAttribute("row-index"));
		mapCellsInContainersRow(rowElement, rowIdx, containerName);		
	}

	@SuppressWarnings("unchecked")
	private void mapCellsInContainersRow(WebElement rowElement, Integer rowIdx, String containerName) {
		List<WebElement> cells = rowElement.findElements(By.cssSelector("div[role='gridcell']"));		
		String colId = null;
		String value = null;
	
		// check if row exists in map
		gridContent.getRowForRowIndex(rowIdx).ifPresentOrElse(
				r -> {
					currentRow = (Row<T>) r; 
					currentRow.setKeyColumnName(cells);
					currentCellList = r.getCells(); 
				}, 
				new Runnable() {					
					@Override
					public void run() {
						currentRow = getNewRowWithKey(cells, rowIdx);
						currentCellList = new ArrayList<>();	
					}
				});
			
		for (WebElement c : cells) {
			colId = c.getAttribute("col-id");			
			value = c.getText(); 	
			Cell newCell = new Cell(
					containerName,
					colId, 
					value, 
					c.getAttribute("comp-id"), 
					c.getAttribute("unselectable"), 
					rowIdx);		
				
			addCellToCurrentCellList(newCell);
			setAsRowKeyIfTheCellIsUsedAsKey(colId, newCell);
			updateLastRowIfNecessary(rowIdx);
		}	
		
		currentRow.addCells(currentCellList);
		gridContent.addRow(currentRow);
	}
	
	private Row<T> getNewRowWithKey(List<WebElement> cellElements, int rowNum){
		Row<T> newRow = keyStrategyRows.getNewRow();
		newRow.setRowIdx(rowNum);
		newRow.setKeyColumnName(cellElements);
		return newRow;
	}
	
	private void addCellToCurrentCellList(Cell newCell) {
		currentCellList.add(newCell);
	}
	
	private void setAsRowKeyIfTheCellIsUsedAsKey(String colId, Cell keyCell) {
		if(colId.equalsIgnoreCase(currentRow.getKeyColumnName())) {
			currentRow.setKeyForRow(keyCell);
		}
	}
					
	private void updateLastRowIfNecessary(int rowIdx) {
		if(rowIdx > currentLastRow) {
			currentLastRow = rowIdx;
			gridContent.setLastRowNum(currentLastRow);
		}
	}

}

