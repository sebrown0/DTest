/**
 * 
 */
package controls;

import org.openqa.selenium.WebElement;

import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 * Can only select an item from an existing list.
 * 
 */
public class ComboSelectFromOptions extends Combo {

	public ComboSelectFromOptions(CoreData coreData, WebElement combo) {
		super(coreData, combo);	
	}

}
