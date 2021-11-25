/**
 * 
 */
package object_models.dk_grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import utils.KeyIncrementer;


/**  
 * @author SteveBrown
 * @since 1.0
 * @version 1.0
 * 
 * Read the content of a DKGrid.
 *  
 */
public class DkGridContentReader <T extends KeyStrategyRow> {
	private WebElement gridContainer;
	private WebElement contentElement;	
	private WebElement currentContainerElement;
	private Logger logger = LogManager.getLogger();
	private DkGridContent<?> gridContent;
	private DkGridHeader dkGridHeader;
	private KeyStrategyRow keyStrategyRows;
	private String currentContainerName;
	private int currentLastRow = -1;
	private int currentLastCol = -1;	
	private List<Cell> currentCellList; 
	private Row<T> currentRow;
	private int readAttempt;
	private By contentLocator;
	
	private final int MAX_READ_ATTEMPTS = 5;
	
	public DkGridContentReader(WebElement gridContainer, DkGridContent<?> gridContent, KeyStrategyRow keyStrategyRows, DkGridHeader dkGridHeader) {
		this.gridContainer = gridContainer;
		this.gridContent = gridContent;
		this.keyStrategyRows = keyStrategyRows;
		this.dkGridHeader = dkGridHeader;
		this.contentLocator = By.cssSelector(
				"#dkrGrid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > " +
				"div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-animation");
	}
	

	/*
	 *  If a new row has been added we can use this to get it.
	 *  Any exisiting content has their row index incremented.	
	 */
	public void readFirstRow() {		
		incrementKeysOfExistingContent();
		read(By.cssSelector("div[row-index='0']"));		
	}	
	private void incrementKeysOfExistingContent() {
		if(gridContent != null && gridContent.hasData()) {
			gridContent.setRows(KeyIncrementer.incrementKeyValues(gridContent.getRows()));			
		}					
	}
	
	public void readContent() {		
		read(By.cssSelector("div[role='row']"));
	}
	
	private void read(By byRowSelector) {		
		if(++readAttempt < MAX_READ_ATTEMPTS) { 	
			setContentElement();
			loopContainers(byRowSelector);
		}
	}
	
	private void setContentElement() { 	
		contentElement = gridContainer.findElement(contentLocator); 	
	}
		
	private void loopContainers(By byRowLocator){ 	
		mapLeftPinned(byRowLocator);
		mapCentre(byRowLocator);		
//		mapRightPinned();		
	}
	
	private void mapLeftPinned(By byRowLocator) {
		currentContainerName = "ag-pinned-left-cols-container";
		mapContainer(By.cssSelector("div[class='" + currentContainerName + "']"), byRowLocator);
	}
	
	private void mapCentre(By byRowLocator) {
		currentContainerName = "ag-center-cols";
		mapContainer(By.cssSelector("div[class^='" + currentContainerName + "']"), byRowLocator);
	}
	
//	private void mapRightPinned() {
		// currentContainerName = "ag-pinned-right-cols-container ag-hidden";
		// TODO
//	}
	
	private void mapContainer(By containerLocator, By byRowLocator) {
		currentContainerElement = contentElement.findElement(containerLocator);
		getRowsInContainer(currentContainerElement, byRowLocator);
	}
	
	private void getRowsInContainer(WebElement container, By byRowLocator) {
		if(container != null) {			
			String containerName = container.getAttribute("ref");								 	
			List<WebElement> rows = container.findElements(byRowLocator);			
			rows.forEach(row -> { 
					mapRowFromContainer(row, containerName);
			});	
		}else {
			logger.info("No rows in container [" + currentContainerName + "]");
		}		
	}
		
	private void mapRowFromContainer(WebElement rowElement, String containerName) {
		try {
			Integer rowIdx = Integer.valueOf(rowElement.getAttribute("row-index"));		
			mapCellsInContainersRow(rowElement, rowIdx, containerName);
		} catch (StaleElementReferenceException e) {
			/*
			 * The grid could be changing because of a filter event.
			 * If a StaleElementReferenceException is thrown this could be the cause.
			 * Therefore, try to read the content again, up to MAX_READ_ATTEMPTS.
			 */
			gridContent.clearAll();
			this.readContent();
		} catch (Exception e) {
			logger.error("Error mapping row for container [" + containerName + "]");
		}
	}

	@SuppressWarnings("unchecked")
	private void mapCellsInContainersRow(WebElement rowElement, Integer rowIdx, String containerName) {		
		List<WebElement> cells = rowElement.findElements(By.cssSelector("div[role='gridcell']"));
		String colId = null;
		String value = null;
		Integer colIdx;
	
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
			colIdx = Integer.valueOf(c.getAttribute("aria-colindex"));

			Cell newCell = new Cell(currentContainerElement);
			newCell
				.setColName(dkGridHeader.getColNameForColIdx(colIdx))
				.setColNum(colIdx)
				.setCompId(c.getAttribute("comp-id"))
				.setContainerName(containerName)
				.setId(colId)
				.setRowNum(rowIdx)
				.setUnselectable(c.getAttribute("unselectable"))
				.setValue(value);
	
			addCellToCurrentCellList(newCell);
			setAsRowKeyIfTheCellIsUsedAsKey(colId, newCell);
			updateLastRowIfNecessary(rowIdx);
			updateLastColfNecessary(colIdx);
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
	
	private void updateLastColfNecessary(int colIdx) {
		if(colIdx > currentLastCol) {
			currentLastCol = colIdx;
			gridContent.setLastRowNum(currentLastRow);
		}
	}

}

