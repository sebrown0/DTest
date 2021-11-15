/**
 * 
 */
package object_models.dk_grid;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controls.Control;

/**
 * @author Steve Brown
 *
 */
public class DkGrid <T extends KeyStrategyRow> implements Control {
	private DkGridToolBarReader toolBarReader;
	private DkGridContentReader<?> contentReader;
	
	private DkGridToolBar toolBar;
	private DkGridHeader gridHeader;	
	private DkGridContent<T> gridContent;

	private WebElement myContainer;
	
	private boolean gridLoaded;
	private boolean toolBarLoaded;
	private boolean contentLoaded;
	
	public DkGrid(WebDriver driver, KeyStrategyRow keyStrategyRows) {
		this.setGridElement(driver);
		this.setColumnHeaders();
		this.toolBar = new DkGridToolBar();
		this.gridContent = new DkGridContent<>();
		this.toolBarReader = new DkGridToolBarReader(driver, toolBar);
		this.contentReader = new DkGridContentReader<>(myContainer, gridContent, keyStrategyRows);
	}

	private void setGridElement(WebDriver driver) {
		By contentLocator = By.id("dkrGrid");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		 
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
		
}
