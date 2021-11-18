/**
 * 
 */
package controls;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ListBox;

/**
 *
 * Can only select an item from an existing list.
 * 
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public class ComboSelectFromList extends Combo {
	private By resultsBy;
	private ListBox listBox;
	
	public ComboSelectFromList(WebDriver driver, By findBy, By resultsBy) {
		super(driver, findBy);
		
		this.resultsBy = resultsBy;
	}
		
	public void selectFullText(String txt) {
		listBox = new ListBox(driver, resultsBy);
		Optional<WebElement> item = Optional.ofNullable(listBox.getListItem(txt));
		item.ifPresent(i -> i.click());
	}
	
	public Optional<String> getAlert(){
		if(listBox == null) {
			listBox = new ListBox(driver, resultsBy);
		}
		return listBox.getAlert();
	}
}
