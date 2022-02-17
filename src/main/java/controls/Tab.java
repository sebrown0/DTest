/**
 * 
 */
package controls;

import java.util.List;

import org.openqa.selenium.By;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class Tab implements ControlAdder {

	@Override
	public void addElement(ControlGetter controlGetter, List<ControlData> controlData) {
		controlData.add(
				new ControlData(
						controlGetter
								.setParent(By.cssSelector("div[class='tab-content']"))));
	}
	
}

//public class Tab implements Control, ZZZ_ControlGroup, DisplayedText {
//	private WebDriver driver;
//	private By locator;
//	private WebElement tab;
//	private List<ControlData> controlData = new ArrayList<>();
//	private String name;
//	private ControlFinder controlFinder;
//	
//	public Tab(String name, WebDriver driver, By locator) {
//		this.driver = driver;
//		this.locator = locator;
//		this.tab = driver.findElement(locator);
//		this.name = name;
//	}
//		
//	public Tab addElements(List<ControlGetter> elements) {
//		if(elements != null) {
//			elements.forEach(v -> {
//				addElement(v.getName(), v);
//			});
//		}
//		return this;
//	}
//	
//	@Override //ControlGroup
//	public Optional<Control> getControlByTitle(String title) {
//		controlFinder = new ControlFinder(controlData);
//		return controlFinder.getControlByTitle(title);
//	}
//	
//	private Tab addElement(String name, ControlGetter controlGetter) {
//		controlData.add(
//				new ControlData(
//						controlGetter
//								.setParent(By.cssSelector("div[class='tab-content']"))));
//		return this;
//	}
//	
//	@Override //DisplayedText
//	public String getText() {
//		if(isAvailable()) {
//			return tab.getText().trim();	
//		}else {
//			return null;
//		}		
//	}
//	
//	@Override //Control
//	public boolean isAvailable() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//		try {
//			if(tab == null && locator != null) {
//				tab = wait.until(ExpectedConditions.elementToBeClickable(locator));	
//			}else if(tab != null){
//				tab = wait.until(ExpectedConditions.elementToBeClickable(tab));
//			}else {
//				return false;
//			}			
//			return true;
//		} catch (Exception e) {
//			LogManager.getLogger().error("Unable to find tab [" + e + "]");
//			return false;
//		}		
//	}
//
//	public boolean click() {		
//		if(isAvailable()) {
//			tab.click();
//			return true;
//		}
//		return false;
//	}
//
//	public String getName() {
//		return name;
//	}
//}
