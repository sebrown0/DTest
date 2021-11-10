/**
 * 
 */
package object_models.dk_grid;

import java.time.Duration;
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
		containerNames[1] = "ag-center-cols-container";
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
		WebElement container = contentElement.findElement(By.cssSelector("div[class='" + containerNames[1] + "']"));
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
					mapRowToContainer(row, containerName); 
				});	
		}else {
			logger.info("No rows in container [" + currentContainerName + "]");
		}
		
	}
	
	private void mapRowToContainer(WebElement rowElement, String containerName) {				
		String rowIdx = rowElement.getAttribute("row-index");
				
		gridContent.getGridData().addRow(
				containerName,
				mapCellsInRow(rowElement.findElements(By.cssSelector("div[role='gridcell']")), rowIdx));
	}
	
	private Row<T> mapCellsInRow(List<WebElement> cellElements, String rowIdx) {		
		Map<String, Cell> cells = new HashMap<>();
		Row<T> newRow  = getNewRowWithKey(cellElements, rowIdx);
		String colId = null;
		String value = null;

		for (WebElement cellElement : cellElements) {
			colId = cellElement.getAttribute("col-id");			
			value = cellElement.getText();
			System.out.println("col id ->" + colId + " val ->" + value); // TODO - remove or log
 	
			Cell newCell = new Cell(
					colId, 
					value, 
					cellElement.getAttribute("comp-id"), 
					cellElement.getAttribute("unselectable"), 
					rowIdx);		
			
			addCellToList(cells, newCell);
			setAsRowKeyIfTheCellIsUsedAsKey(newRow, colId, value);
			updateLastRowIfNecessary(Integer.parseInt(rowIdx));
		}		
		addCellsToRow(newRow, cells);
		return newRow;
	}

	private void setAsRowKeyIfTheCellIsUsedAsKey(Row<T> newRow, String colId, String value) {
		if(colId.equalsIgnoreCase(newRow.getKeyColumnName())) {
			newRow.setKeyForRow(value);
		}
	}

	private Row<T> getNewRowWithKey(List<WebElement> cellElements, String rowNum){
		Row<T> newRow = keyStrategyRows.getNewRow();
		newRow.setRowIdx(rowNum);
		newRow.setKeyColumnName(cellElements);
		return newRow;
	}
	
	private void addCellToList(Map<String, Cell> cells, Cell newCell) {
		cells.putIfAbsent(newCell.getColumnId(), newCell);
	}
		
	private void addCellsToRow(Row<T> newRow, Map<String, Cell> cells) {
		newRow.addCells(cells);
	}
		
	private void updateLastRowIfNecessary(int rowIdx) {
		if(rowIdx > currentLastRow) {
			currentLastRow = rowIdx;
			gridContent.setLastRowNum(currentLastRow);
		}
	}
	
}

