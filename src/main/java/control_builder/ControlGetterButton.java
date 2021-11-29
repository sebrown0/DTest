/**
 * 
 */
package control_builder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.Button;
import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ControlGetterButton extends ControlGetter {
	private Control btn;
	
	public ControlGetterButton(WebDriver driver, By findBy) {
		super(driver, findBy);
		
		this.btn = new Button(driver, findBy);
	}

	@Override
	public Control getControl() {
		return btn;
	}

}
