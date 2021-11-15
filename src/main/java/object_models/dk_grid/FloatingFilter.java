/**
 * 
 */
package object_models.dk_grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 */
public class FloatingFilter {
	private WebElement myContainer; //"div[class='ag-header-cell ag-focus-managed']"

	public FloatingFilter(WebElement myContainer) {
		this.myContainer = myContainer;
	}
	
	public void filterColumn(String filterTxt) {
		WebElement filterInput = myContainer.findElement(By.cssSelector("input[ref='eInput']"));
		filterInput.sendKeys(filterTxt);
	}
	
}
