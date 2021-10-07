/**
 * 
 */
package controls;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.TextOut;

/**
 * @author Steve Brown
 *
 * Add a control to the map with the control name as the key.
 * This is then passed to page control as [BuildControls].
 * Where the control is 'built'.
 */
public class ControlBuilder implements BuildControls {
	private Map<String, Control> controls = new HashMap<>();
	private WebDriver driver;
	
	public ControlBuilder(WebDriver driver) {
		this.driver = driver;
	}
	
	public ControlBuilder addTextOut(ControlName cntrlName, WebElement e) {
		controls.put(cntrlName.getName(), new TextOut(e));
		return this;
	}
	
	public ControlBuilder addTextOut(ControlName cntrlName, By findBy) {
		controls.put(cntrlName.getName(), new TextOut(driver, findBy));
		return this;
	}
		
	@Override
	public Map<String, Control> build(){
		return controls;
	}
}
