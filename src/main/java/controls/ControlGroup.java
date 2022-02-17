/**
 * 
 */
package controls;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;
import controls.finder.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlGroup implements Control, DisplayedText{
	private WebDriver driver;
	private By locator;
	private WebElement cntrl;
	private ControlFinder controlFinder;
	private List<ControlData> controlData = new ArrayList<>();
	
	public ControlGroup(String name, WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
		this.cntrl = driver.findElement(locator);
	}
	
	public ControlGroup addElements(List<ControlGetter> elements, ControlAdder adder) {
		if(elements != null) {
			elements.forEach(v -> {
				adder.addElement(v, controlData);
			});
		}
		return this;
	}

	public Optional<Control> getControlByTitle(String title) {
		controlFinder = new ControlFinder(controlData);
		return controlFinder.getControlByTitle(title);
	}
	
	@Override //Control
	public boolean isAvailable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			if(cntrl == null && locator != null) {
				cntrl = wait.until(ExpectedConditions.elementToBeClickable(locator));	
			}else if(cntrl != null){
				cntrl = wait.until(ExpectedConditions.elementToBeClickable(cntrl));
			}else {
				return false;
			}			
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to find control [" + e + "]");
			return false;
		}		
	}
	
	@Override //DisplayedText
	public String getText() {
		if(isAvailable()) {
			return cntrl.getText().trim();	
		}else {
			return null;
		}		
	}
	
}
