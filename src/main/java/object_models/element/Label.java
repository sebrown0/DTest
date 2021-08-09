/**
 * 
 */
package object_models.element;

import org.openqa.selenium.By;

/**
 * @author SteveBrown
 *
 */
public class Label {
	private By locator;
	private String labelText;
	
	@SuppressWarnings("unused")
	private Label() {}
	
	public Label(By locator, String labelText) {
		this.locator = locator;
		this.labelText = labelText;
	}
	
	public By getLocator() {
		return locator;
	}
	public String getLabelText() {
		return labelText;
	}
}
