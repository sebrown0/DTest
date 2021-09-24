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
//	private WebDriver driver;
	private DkGridToolBarReader toolBarReader;
	private DkGridToolBar toolBar = new DkGridToolBar();
	
	public DkGrid(WebDriver driver) {
//		this.driver = driver;
		this.toolBarReader = new DkGridToolBarReader(driver, toolBar);
	}

	public void loadToolBar() {
		toolBarReader.read();
	}	
	public DkGridToolBar getToolBar() {
		return toolBar;
	}
	
	
}
