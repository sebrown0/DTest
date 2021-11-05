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
 * @author Steve Brown
 *
 * Can only select an item from an existing list.
 * 
 */
public class ComboSelectOnly extends Combo {
	private By resultsBy;
	
	public ComboSelectOnly(WebDriver driver, By findBy, By resultsBy) {
		super(driver, findBy);
		
		this.resultsBy = resultsBy;
	}
		
	public ComboSelectOnly(WebDriver driver, WebElement combo) {
		super(driver, combo);	
	}

	public void selectText(String txt) {
		ListBox listBox = new ListBox(driver, resultsBy);
		Optional<WebElement> item = Optional.ofNullable(listBox.getListItem(txt));
		item.ifPresent(i -> i.click());
	}
}
