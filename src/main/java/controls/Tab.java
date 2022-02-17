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

import control_builder.ControlGetter;
import control_builder.control_data.ControlData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class Tab implements Control, DisplayedText {
	private WebDriver driver;
	private By locator;
	private WebElement tab;
	private List<ControlData> controlData = new ArrayList<>();
	private Control currentControl;
	private String name;
	
	public Tab(String name, WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
		this.tab = driver.findElement(locator);
		this.name = name;
	}
	
	public Optional<Control> getControlByTitle(String title) {
		currentControl = null;
		getElementByTitle(title).ifPresent(e -> {
			currentControl = e.getControl();
		});
		return Optional.ofNullable(currentControl);
	}

	private Optional<ControlGetter> getElementByTitle(String title) {
		return 
			controlData
				.stream()
				.filter(e -> e.getCntrlName().equals(title))
				.map(c -> c.getControlGetter())
				.findFirst();
	}
	
	public Tab addElement(String name, ControlGetter controlGetter) {
//		WebElement el = tab.findElement(findBy);
		controlData.add(
				new ControlData(
						name,	
						controlGetter
								.setParent(By.cssSelector("div[class='tab-content']"))));
		return this;
	}
	
	@Override //DisplayedText
	public String getText() {
		if(isAvailable()) {
			return tab.getText().trim();	
		}else {
			return null;
		}		
	}
	
	@Override //Control
	public boolean isAvailable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			if(tab == null && locator != null) {
				tab = wait.until(ExpectedConditions.elementToBeClickable(locator));	
			}else if(tab != null){
				tab = wait.until(ExpectedConditions.elementToBeClickable(tab));
			}else {
				return false;
			}			
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to find tab [" + e + "]");
			return false;
		}		
	}

	public boolean click() {		
		if(isAvailable()) {
			tab.click();
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}
}
