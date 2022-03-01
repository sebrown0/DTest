/**
 * 
 */
package object_models.modal_forms.emp_selection;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class RowSelector {
	private boolean selectOk;
	
	public boolean selectRow(WebElement table, String rowNum) {
		clickRow(table, rowNum);		
		return selectOk;
	}
		
	private void clickRow(WebElement table, String rowNum) {
		try {
			WebElement rw = table.findElement(By.id("RIZZ" + rowNum));
			rw.click();	
			selectOk = true;
		}catch(NoSuchElementException e) {
			LogManager
				.getLogger(this.getClass())
				.info("No results found.");
			selectOk = false;
		}
	}
}
