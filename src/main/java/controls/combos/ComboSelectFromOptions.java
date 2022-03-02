/**
 * 
 */
package controls.combos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 * Can only select an item from an existing list.
 * 
 */
public class ComboSelectFromOptions extends Combo {

	public ComboSelectFromOptions(CoreData coreData, WebElement combo, By locator) {
		super(coreData, combo, locator);	
	}

}
