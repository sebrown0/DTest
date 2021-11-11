/**
 * 
 */
package object_models.dk_grid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class DkGridContentReader <T extends KeyStrategyRow> {
	private WebDriver driver;
	private WebElement gridElement;
	private WebElement contentElement;	
	private Logger logger = LogManager.getLogger();
	private DkGridContent<?> gridContent;	
	private String[] containerNames = new String[3];
	private KeyStrategyRow keyStrategyRows;
	private String currentContainerName;
	private int currentLastRow = -1;
	
//	private Map<Integer, List<Cell>> rows = new HashMap<>();
	private List<Cell> currentCellList; 
	private Row<T> currentRow;
	
	public DkGridContentReader(WebDriver driver, DkGridContent<?> gridContent, KeyStrategyRow keyStrategyRows) {
		this.driver = driver;
		this.gridContent = gridContent;
		this.keyStrategyRows = keyStrategyRows;		
	}
		
	private void intialise() {
		setContainerNames();
		setGridElement();
		setContentElement();
	}
	
	private void setContainerNames() {
		containerNames[0] = "ag-pinned-left-cols-container";
		containerNames[1] = "ag-center-cols";
		containerNames[2] = "ag-pinned-right-cols-container ag-hidden"; 
	}
	
	private void setGridElement() {
		By contentLocator = By.id("dkrGrid");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(contentLocator)); 	
		gridElement = driver.findElement(contentLocator);		
	}
	
	private void setContentElement() {
		contentElement = gridElement.findElement(By.cssSelector("div[class='ag-body-viewport ag-layout-normal ag-row-animation']"));
	}
	
	public void read() {
		intialise();
		loopContainers();
		System.out.println("->"); // TODO - remove or log 	
	}
	
	private void loopContainers(){
		mapLeftPinned();
		mapCentre();
//		mapRightPinned();		
	}
	
	private void mapLeftPinned() {
		WebElement container = contentElement.findElement(By.cssSelector("div[class='" + containerNames[0] + "']"));
		currentContainerName = containerNames[0];		
		getRowsInContainer(container);
	}
	
	private void mapCentre() {
		WebElement container = contentElement.findElement(By.cssSelector("div[class^='" + containerNames[1] + "']"));
//		WebElement container = contentElement.findElement(By.cssSelector("div[class='" + containerNames[1] + "']"));
		currentContainerName = containerNames[1];
		getRowsInContainer(container);
	}
	
//	private void mapRightPinned() {
//		WebElement container = contentElement.findElement(By.cssSelector("div[class='" + containerNames[2] + "']"));
//		currentContainerName = containerNames[2];
//		getRowsInContainer(container);
//	}
	
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
		
//		gridContent.getGridData().addRow(
//				containerName,
//				mapCellsInContainersRow(rowElement.findElements(By.cssSelector("div[role='gridcell']")), rowIdx, containerName));
	}

	private void mapCellsInContainersRow(WebElement rowElement, Integer rowIdx, String containerName) {
		List<WebElement> cells = rowElement.findElements(By.cssSelector("div[role='gridcell']"));
		currentRow  = getNewRowWithKey(cells, rowIdx);
		String colId = null;
		String value = null;
	
		// check if row exists in map
		gridContent.getRowForRowIndex(rowIdx).ifPresentOrElse(
				r -> currentCellList = r.getCells(), 
				new Runnable() {					
					@Override
					public void run() {
						currentCellList = new ArrayList<>();	
					}
				});
		
//		if(rows.containsKey(rowIdx)) {
//			currentCellList = rows.get(rowIdx);
//		}else {
//			currentCellList = new ArrayList<>();
//		}
		
//		if(rows.containsKey(rowIdx)) {
//			currentCellList = rows.get(rowIdx);
//		}else {
//			currentCellList = new ArrayList<>();
//		}
	
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
			setAsRowKeyIfTheCellIsUsedAsKey(currentRow, colId, newCell);
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
	
	private void setAsRowKeyIfTheCellIsUsedAsKey(Row<T> newRow, String colId, Cell keyCell) {
		if(colId.equalsIgnoreCase(newRow.getKeyColumnName())) {
			newRow.setKeyForRow(keyCell);
		}
	}
					
	private void updateLastRowIfNecessary(int rowIdx) {
		if(rowIdx > currentLastRow) {
			currentLastRow = rowIdx;
			gridContent.setLastRowNum(currentLastRow);
		}
	}


//private void addCellsToRow(Row<T> newRow, Map<String, Cell> cells) {
//	newRow.addCells(cells);
//}
//private void addCellToList(Map<String, Cell> cells, Cell newCell) {
//	currentCellList.add(newCell);
////	cells.putIfAbsent(newCell.getColumnId(), newCell);
//}
//private void setAsRowKeyIfTheCellIsUsedAsKey(Row<T> newRow, String colId, String value) {
//	if(colId.equalsIgnoreCase(newRow.getKeyColumnName())) {
//		newRow.setKeyForRow(value);
//	}
//}

	
//private Row<T> mapCellsInContainersRow(List<WebElement> cellElements, Integer rowIdx, String containerName) {		
////	Map<String, Cell> cells = new HashMap<>();
//	Row<T> newRow  = getNewRowWithKey(cellElements, rowIdx);
//	String colId = null;
//	String value = null;
//
//
//	// check if row exists in map
//	if(rows.containsKey(rowIdx)) {
//		currentCellList = rows.get(rowIdx);
////		List<Cell> cellsInRow = rows.get(rowIdx);
////		cellsInRow.addAll(currentCellList);
//	}else {
//		currentCellList = new ArrayList<>();
//	}
//	
//	for (WebElement cellElement : cellElements) {
//		colId = cellElement.getAttribute("col-id");			
//		value = cellElement.getText(); 	
//		Cell newCell = new Cell(
//				containerName,
//				colId, 
//				value, 
//				cellElement.getAttribute("comp-id"), 
//				cellElement.getAttribute("unselectable"), 
//				rowIdx);		
//		
//		addCellToCurrentCellList(newCell);
//		setAsRowKeyIfTheCellIsUsedAsKey(newRow, colId, newCell);
//		updateLastRowIfNecessary(rowIdx);
//	}		
//	
////	addCellsToRow(newRow, cells);
//	return newRow;
//}	
}

