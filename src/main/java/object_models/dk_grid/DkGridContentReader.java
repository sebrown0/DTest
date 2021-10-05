/**
 * 
 */
package object_models.dk_grid;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class DkGridContentReader <T extends KeyStrategyRow>{
	private WebDriver driver;
	private WebElement gridElement;
	private WebElement contentElement;	
	private Logger logger = LogManager.getLogger();	
	private DkGridContent<?> gridContent;	
	private String[] containerNames = new String[3];
	private KeyStrategyRow keyStrategyRows;

	public DkGridContentReader(WebDriver driver, DkGridContent<?> gridContent, KeyStrategyRow keyStrategyRows) {
		this.driver = driver;
		this.gridContent = gridContent;
		this.keyStrategyRows = keyStrategyRows;
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
		gridElement = driver.findElement(By.id("dkrGrid"));
	}
	
	private void setContentElement() {
		contentElement = gridElement.findElement(By.cssSelector("div[class='ag-body-viewport ag-layout-normal ag-row-animation']"));
	}
	
	public void read() {
		loopContainers();
	}
	
	private void loopContainers(){
		WebElement container = null;		
		for (String className : containerNames) {			
			container = contentElement.findElement(By.cssSelector("div[class='" + className + "']"));
			getRowsInContainer(container);
		}
	}
	
	private void getRowsInContainer(WebElement container) {
		String containerName = container.getAttribute("ref");
		container.findElements(By.cssSelector("div[role='row']")).forEach(r -> mapRowToContainer(r, containerName));
	}
	
	private void mapRowToContainer(WebElement rowElement, String containerName) {
		Row<T> newRow = keyStrategyRows.getNewRow();		
		mapCellsInRow(rowElement.findElements(By.cssSelector("div[role='gridcell']")), newRow);
		gridContent.getGridData().addRow(containerName, newRow);
	}

	private void mapCellsInRow(List<WebElement> cells, Row<T> row) {
		for (WebElement cell : cells) {
			Cell newCell = new Cell(
					cell.getAttribute("aria-colindex"), 
					cell.getText(), 
					cell.getAttribute("comp-id"), 
					cell.getAttribute("unselectable"));
			
//			Optional<String> key = Optional.ofNullable(cell.getAttribute("col-id"));
			
			/*
			 * MAPPING KEY BY
			 */
			Optional<String> key = Optional.ofNullable(row.getKeyStrategy().getStrategyName());
			
			key.ifPresentOrElse(
					k -> { 
//						System.out.println("mapCellsInRow->" + findKey(k));  
						row.addCell(findKey(k), newCell); }, 
//					k -> row.addCell(findKey(k), newCell),
					new Runnable() {						
						@Override
						public void run() {
							System.out.println("->could not get key for cell" + cell.getAttribute("comp-id"));	
							logger.error("Could not get key for cell" + cell.getAttribute("comp-id"));
						}
					});			
		}
	}
	
	private String findKey(String colId) {
		String key = null;
//		System.out.println("------>trying to find key for col id: " + colId);
		for (ColumnName e : ColumnName.values()) {
			if(e.value.equalsIgnoreCase(colId)) {
//				System.out.println("------>" + e.name() + " ->" + e.value);
				key = e.name();
				break;
			}
			
		}
		return key;
	}
}