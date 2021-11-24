/**
 * 
 */
package object_models.dk_grid;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
import controls.Control;
import enums.GridButtonNames;
import object_models.dialog.DialogOkCancel;
import object_models.dk_grid.buttons.GridButton;
import object_models.dk_grid.buttons.SaveChanges;

/**
 * @author Steve Brown
 * @since 1.0
 * @version 1.0 
 */
public class DkGrid <T extends KeyStrategyRow> implements Control {
	private DkGridToolBarReader toolBarReader;
	private DkGridContentReader<?> contentReader;
	
	private DkGridToolBar toolBar;
	private DkGridHeader gridHeader;	
	private DkGridContent<T> gridContent;

	private WebElement myContainer;
	private WebDriver driver;
	private KeyStrategyRow keyStrategyRows;
	private WebDriverWait wait;		 
	private ContextManager contextManager;
	private boolean gridLoaded;
	private boolean toolBarLoaded;
	private boolean contentLoaded;
	
	public DkGrid(WebDriver driver, KeyStrategyRow keyStrategyRows, ContextManager cm) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.setGridElement(driver);
		this.setColumnHeaders();
		this.keyStrategyRows = keyStrategyRows;
		this.toolBar = new DkGridToolBar();
		this.gridContent = new DkGridContent<>();
		this.toolBarReader = new DkGridToolBarReader(driver, toolBar);
		this.contentReader = new DkGridContentReader<>(myContainer, gridContent, keyStrategyRows, gridHeader);
		this.contextManager = cm;
	}
	
	private void setGridElement(WebDriver driver) {
		By contentLocator = By.id("dkrGrid");		
		wait.until(ExpectedConditions.visibilityOfElementLocated(contentLocator)); 	
		myContainer = driver.findElement(contentLocator);			
	}
	
	private void setColumnHeaders() {
		gridHeader = new DkGridHeader();		
		gridHeader.setColumnHeaders(myContainer);
	}
	
	public DkGridHeader getGridHeader() {
		return gridHeader;
	}
	
	public DkGrid<T> loadGridIfNecessary() {
		if(!gridLoaded) {
			loadToolBar();
			loadContent();	
		}		
		return this;
	}
	
	public DkGridToolBar getToolBar() {
		if(!toolBarLoaded) {
			loadToolBar();
		}
		return toolBar;
	}
	private void loadToolBar() {
		toolBarReader.read();
		toolBarLoaded = true;
	}	
		
	public DkGridContent<T> getContent() {
		if(!contentLoaded) {
			loadContent();
		}
		return gridContent;
	}
	private void loadContent() {
		contentReader.read();
		contentLoaded = true;
	}
	public void reloadContent() {
		this.gridContent = new DkGridContent<>();
		this.contentReader = new DkGridContentReader<>(myContainer, gridContent, keyStrategyRows, gridHeader);
		loadContent();
	}
	
	public Cell getCell(Row<?> row, String colName) {
		return gridContent.getCell(row.getRowIdx(), colName);
	}
	public Cell getCell(Integer rowIdx, String colName) {
		return gridContent.getCell(rowIdx, colName);
	}
	
	public DialogOkCancel saveRecord() {
		Optional<GridButton> addRec = getToolBar().getButton(GridButtonNames.BTN_SAVE);
		DialogOkCancel frm = null;
		
		if(addRec.isPresent()) {
			SaveChanges save = (SaveChanges) addRec.get(); 
			frm = save.clickButton(driver, contextManager);
		}
		return frm;
	}
	
	public void addRecord() {
		Optional<GridButton> addRec = getToolBar().getButton(GridButtonNames.BTN_ADD);
		addRec.ifPresent(a -> { 
			a.clickButton();
//			waitForContent();
			reloadContent();
		});		
	}
	
	/*
	 * Was having problems with reloading content.
	 * Changing the selector for the content seems 
	 * to have solved it.
	 * However, if problems continue reinstate this.
	 */
//	private void waitForContent() {
//		int findRowNum = gridContent.getLastRowNum() + 1;
//		By contentLocator = By.xpath("//div[@role='row' and @row-index='" + findRowNum + "']");		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(contentLocator));
//	}
}
