/**
 * 
 */
package object_models.dk_grid;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class DkGrid {
	private DkGridToolBarReader toolBarReader;
	private DkGridToolBar toolBar = new DkGridToolBar();
	private DkGridContentReader contentReader;
	private DkGridContent gridContent = new DkGridContent();
	
	public DkGrid(WebDriver driver) {
		this.toolBarReader = new DkGridToolBarReader(driver, toolBar);
		this.contentReader = new DkGridContentReader(driver, gridContent);
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
	public DkGridContent getContent() {
		return gridContent;
	}
}
