/**
 * 
 */
package object_models.dk_grid;

import org.openqa.selenium.WebDriver;

import object_models.dk_grid.Row.KeyStrategyRow;

/**
 * @author Steve Brown
 *
 */
public class DkGrid <T extends KeyStrategyRow> {
	private DkGridToolBarReader toolBarReader;
	private DkGridToolBar toolBar = new DkGridToolBar();
	private DkGridContentReader<?> contentReader;
	private DkGridContent<T> gridContent = new DkGridContent<>();;
	
	public DkGrid(WebDriver driver, KeyStrategyRow keyStrategyRows) { 	
		this.toolBarReader = new DkGridToolBarReader(driver, toolBar);
		this.contentReader = new DkGridContentReader<>(driver, gridContent, keyStrategyRows);
	}

	public void loadToolBar() {
		toolBarReader.read();
	}	
	public DkGridToolBar getToolBar() {
		return toolBar;
	}
	
	public void loadContent() {
		contentReader.read();
	}	
	public DkGridContent<T> getContent() {
		return gridContent;
	}
	
}
