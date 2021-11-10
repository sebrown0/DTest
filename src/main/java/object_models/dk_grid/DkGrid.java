/**
 * 
 */
package object_models.dk_grid;

import org.openqa.selenium.WebDriver;

import controls.Control;
import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class DkGrid <T extends KeyStrategyRow> implements Control {
	private DkGridToolBarReader toolBarReader;
	private DkGridToolBar toolBar;
	private DkGridContentReader<?> contentReader;
	private DkGridContent<T> gridContent;
	private boolean toolBarLoaded;
	private boolean contentLoaded;
	
	public DkGrid(WebDriver driver, KeyStrategyRow keyStrategyRows) {
		this.toolBar = new DkGridToolBar();
		this.gridContent = new DkGridContent<>();
		this.toolBarReader = new DkGridToolBarReader(driver, toolBar);
		this.contentReader = new DkGridContentReader<>(driver, gridContent, keyStrategyRows);
	}

	public void loadGrid() {
		loadToolBar();
		loadContent();
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
